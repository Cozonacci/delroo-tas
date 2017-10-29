package org.delroo.tas.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StringUtils {

    private StringUtils() {
    }

    public static boolean containsIgnoreCases(final String input, final String content) {
        if (input == null || content == null) return false;
        return Predicates.containsIgnoreCases(input).test(content);
    }

    public static Set<String> getUniqueWords(final String input) {
        if (isNullOrEmpty(input)) return new HashSet<>();
        return Stream.of(input.split("[\\W]+")).collect(Collectors.toSet());
    }

    public static String getHtmlText(final String input) {
        if (isNullOrEmpty(input)) return input;
        Document doc = Jsoup.parse(input);
        return doc.body().text();
    }

    public static String innerValue(final String input) {
        if (input == null || input.length() <= 1) return input;
        return input.substring(1, input.length() - 1);
    }

    public static boolean isNullOrEmpty(final String input) {
        return (input == null) || input.isEmpty();
    }

    public static class Predicates {
        public static Predicate<String> containsIgnoreCases(String content) {
            return (input) -> Pattern.compile(Pattern.quote(input), Pattern.CASE_INSENSITIVE).matcher(content).find();
        }
    }
}
