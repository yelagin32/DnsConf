# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

DNS Block & Redirect Configurer - Java-based tool for managing DNS blocking and redirect rules on Cloudflare Zero Trust and NextDNS platforms. Parses hosts files from remote sources and applies them as DNS policies via provider APIs.

**Key Purpose**: Automate DNS-level ad blocking and domain redirection by syncing hosts file sources to cloud DNS providers.

## Build & Run Commands

### Local Development
```bash
# Build the project
mvn clean package

# Run the application (requires environment variables)
java --enable-preview -jar target/dns-block-and-redirect-configurer-1.0-SNAPSHOT.jar
```

### Required Environment Variables
- `DNS` - Provider name: "CLOUDFLARE" or "NEXTDNS" (case-insensitive)
- `CLIENT_ID` - Cloudflare Account ID or NextDNS Profile ID
- `AUTH_SECRET` - API token/key for authentication
- `BLOCK` - Comma-separated URLs to hosts files for blocking (optional)
- `REDIRECT` - Comma-separated URLs to hosts files for redirects (optional)

### Testing Locally
```bash
# Example for NextDNS
export DNS=NEXTDNS
export CLIENT_ID=your_profile_id
export AUTH_SECRET=your_api_key
export BLOCK=https://example.com/blocklist.txt
export REDIRECT=https://example.com/redirects.txt

mvn clean package && java --enable-preview -jar target/*.jar
```

## Architecture

### Provider Strategy Pattern
The application uses Spring's dependency injection to dynamically load provider-specific implementations:

1. **Entry Point** (`App.java`): Reads `DNS` env var and selects package to scan
2. **Common Interface** (`DnsTaskRunner`): Contract for all DNS providers
3. **Provider Implementations**:
   - `CloudflareTaskRunner` - Cloudflare Zero Trust Gateway
   - `NextDnsTaskRunner` - NextDNS API

### Package Structure
```
com.novibe
├── common/                    # Shared utilities and interfaces
│   ├── config/               # Environment variable loading
│   ├── data_sources/         # Hosts file parsers
│   │   ├── ListLoader        # Abstract base for HTTP fetching
│   │   ├── HostsBlockListsLoader      # Filters 0.0.0.0/127.0.0.1 entries
│   │   └── HostsOverrideListsLoader   # Filters IP redirect entries
│   ├── util/                 # Logging, parsing helpers
│   └── DnsTaskRunner         # Provider interface
├── dns/
│   ├── cloudflare/           # Cloudflare implementation
│   │   ├── http/            # API clients and DTOs
│   │   ├── service/         # Business logic (ListService, RuleService)
│   │   └── CloudflareTaskRunner
│   └── next_dns/            # NextDNS implementation
│       ├── http/            # API clients and DTOs
│       ├── service/         # Business logic (DenyService, RewriteService)
│       └── NextDnsTaskRunner
```

### Data Flow
1. **Fetch**: `ListLoader` downloads hosts files from URLs via HTTP
2. **Parse**: Provider-specific loaders filter entries:
   - `HostsBlockListsLoader`: Keeps only `0.0.0.0` and `127.0.0.1` prefixes
   - `HostsOverrideListsLoader`: Keeps only non-localhost IP redirects
3. **Transform**: Services convert parsed data to provider API DTOs
4. **Sync**: HTTP clients send batched requests to provider APIs

### Provider Behavior Differences

**Cloudflare** (destructive sync):
- Removes ALL previously generated lists/rules (identified by name prefix and session ID)
- Creates new lists and rules from scratch
- Empty env vars = full cleanup

**NextDNS** (incremental sync):
- Updates existing entries if changed
- Adds new entries
- Only removes ALL data if both `BLOCK` and `REDIRECT` are empty
- Rate limited to 60 requests/minute (handled by `NextDnsRateLimitedApiProcessor`)

## Key Technical Details

### Java Version & Features
- **Java 25** with preview features enabled (`--enable-preview`)
- Uses modern Java features: records, pattern matching, switch expressions
- Lombok for boilerplate reduction

### Dependencies
- **Spring Context 7.0.2**: Dependency injection only (no Spring Boot)
- **Gson 2.10.1**: JSON serialization
- **Lombok 1.18.42**: Code generation

### HTTP Communication
- Uses `java.net.http.HttpClient` (native Java 11+ HTTP client)
- All HTTP logic in `HttpRequestSender` and provider-specific clients
- Cloudflare: Standard REST API
- NextDNS: Rate-limited API with retry logic

### Hosts File Format
Expected format for source URLs:
```
# Comments are ignored
0.0.0.0 blocked-domain.com        # Parsed as block
127.0.0.1 another-blocked.com     # Parsed as block
1.2.3.4 redirected-domain.com     # Parsed as redirect
```

## GitHub Actions Integration

The project is designed to run as a scheduled GitHub Action (daily at 01:30 UTC).

**Workflow file**: `.github/workflows/github_action.yml`

**Important**: The workflow includes a "keep alive" step that makes empty commits to prevent GitHub from disabling the scheduled workflow after 60 days of inactivity.

## Common Development Tasks

### Adding a New DNS Provider
1. Create package under `com.novibe.dns.<provider_name>`
2. Implement `DnsTaskRunner` interface
3. Add HTTP clients in `http/` subpackage
4. Add service layer in `service/` subpackage
5. Update `App.java` switch statement with new provider name
6. Annotate main runner class with `@Service`

### Modifying Hosts File Parsing
- Edit `HostsBlockListsLoader` or `HostsOverrideListsLoader`
- Override `lineParser()` method to change filtering logic
- The base `ListLoader` handles HTTP fetching and deduplication

### Debugging API Issues
- Check `Log` class output for step-by-step execution
- HTTP requests/responses logged in provider-specific clients
- For NextDNS rate limiting: check `NextDnsRateLimitedApiProcessor`

## Notes

- No unit tests currently in the project
- Maven Shade plugin creates fat JAR with all dependencies
- Spring context is manually created (no auto-configuration)
- Session IDs in Cloudflare descriptions prevent accidental deletion of manually created rules
