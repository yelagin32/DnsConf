package com.novibe.common.data_sources;

import com.novibe.common.util.Log;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class HostsOverrideListsLoader extends ListLoader<HostsOverrideListsLoader.BypassRoute> {

    private static final Pattern IP_PATTERN = Pattern.compile(
            "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$"
    );

    public record BypassRoute(String ip, String website) {
    }

    @Override
    protected Stream<BypassRoute> lineParser(String urlList) {
        return Pattern.compile("\\r?\\n").splitAsStream(urlList)
                .parallel()
                .map(String::strip)
                .filter(str -> !str.isBlank())
                .filter(line -> !line.startsWith("#"))
                .filter(line -> !HostsBlockListsLoader.isBlock(line))
                .map(this::mapLine)
                .filter(Objects::nonNull);
    }

    @Override
    protected String listType() {
        return "Override";
    }

    private BypassRoute mapLine(String line) {
        // Разделяем по любым пробельным символам (пробелы, табы)
        String[] parts = line.split("\\s+", 2);

        if (parts.length != 2) {
            Log.fail("Invalid hosts format (expected 'IP domain'): " + line);
            return null;
        }

        String ip = parts[0];
        String website = parts[1];

        // Валидация IP адреса
        if (!isValidIP(ip)) {
            Log.fail("Invalid IP address: " + ip + " in line: " + line);
            return null;
        }

        // Валидация домена (базовая проверка)
        if (website.isEmpty() || website.contains(" ")) {
            Log.fail("Invalid domain: " + website + " in line: " + line);
            return null;
        }

        return new BypassRoute(ip, website);
    }

    private boolean isValidIP(String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }

}
