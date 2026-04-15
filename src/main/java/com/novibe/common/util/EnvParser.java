package com.novibe.common.util;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

public class EnvParser {
    public static List<String> parse(String envValue) {
        if (isNull(envValue)) return List.of();
        envValue = envValue.strip();
        if (envValue.isEmpty()) return List.of();
        return Arrays.asList(envValue.strip().split(","));
    }
}
