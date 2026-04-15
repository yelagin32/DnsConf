package com.novibe.common.data_sources;

import com.novibe.common.util.Log;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class HostsBlockListsLoader extends ListLoader<String> {

    private static final String[] BLOCK_PREFIXES = { "0.0.0.0", "127.0.0.1" };

    public static boolean isBlock(String line) {
        for (String blockPrefix : BLOCK_PREFIXES) {
            if (line.startsWith(blockPrefix)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected Stream<String> lineParser(String urlList) {
        return Pattern.compile("\\r?\\n").splitAsStream(urlList)
                .parallel()
                .map(String::strip)
                .filter(str -> !str.isBlank())
                .filter(line -> !line.startsWith("#"))
                .filter(HostsBlockListsLoader::isBlock)
                .map(this::extractDomain)
                .filter(Objects::nonNull)
                .map(String::toLowerCase);
    }

    @Override
    protected String listType() {
        return "Block";
    }

    private String extractDomain(String line) {
        // Разделяем по любым пробельным символам (пробелы, табы)
        String[] parts = line.split("\\s+", 2);

        if (parts.length != 2) {
            Log.fail("Invalid hosts format (expected 'IP domain'): " + line);
            return null;
        }

        String domain = parts[1];

        // Базовая валидация домена
        if (domain.isEmpty() || domain.contains(" ")) {
            Log.fail("Invalid domain: " + domain + " in line: " + line);
            return null;
        }

        return domain;
    }

}
