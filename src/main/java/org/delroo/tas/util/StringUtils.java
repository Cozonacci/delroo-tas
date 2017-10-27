package org.delroo.tas.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StringUtils {

    private StringUtils() {
    }

    public static Set<String> getUniqueWords(final String input) {
        return Stream.of(input.split("[\\W]+")).collect(Collectors.toSet());
    }

    public static String getHtmlText(final String input) {
        Document doc = Jsoup.parse(input);
        return doc.body().text();
    }
}
