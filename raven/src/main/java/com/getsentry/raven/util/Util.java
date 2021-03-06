package com.getsentry.raven.util;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Raven static Utility class.
 */
public final class Util {

    // Hide the constructor.
    private Util() {

    }

    /**
     * Returns {@code true} if the given string is null or is the empty string.
     *
     * @param string a string reference to check
     * @return {@code true} if the string is null or is the empty string
     */
    public static boolean isNullOrEmpty(@Nullable String string) {
        return string == null || string.length() == 0; // string.isEmpty() in Java 6
    }

    /**
     * Parses the provided tags string into a Map of String -&gt; String.
     *
     * @param tagsString comma-delimited key-value pairs, e.g. "tag1:value1,tag2:value2".
     * @return Map of tags e.g. (tag1 -&gt; value1, tag2 -&gt; value2)
     */
    public static Map<String, String> parseTags(String tagsString) {
        if (isNullOrEmpty(tagsString)) {
            return Collections.emptyMap();
        }

        String[] entries = tagsString.split(",");
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (String entry : entries) {
            String[] split = entry.split(":");
            if (split.length != 2) {
                throw new IllegalArgumentException("Invalid tags entry: " + entry);
            }
            map.put(split[0], split[1]);
        }
        return map;
    }

    /**
     * Parses the provided extraTags string into a Set of Strings.
     *
     * @param extraTagsString comma-delimited tags
     * @return Set of Strings representing extra tags
     */
    public static Set<String> parseExtraTags(String extraTagsString) {
        if (isNullOrEmpty(extraTagsString)) {
            return Collections.emptySet();
        }

        return new HashSet<>(Arrays.asList(extraTagsString.split(",")));
    }

}
