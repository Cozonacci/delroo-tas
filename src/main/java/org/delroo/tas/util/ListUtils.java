package org.delroo.tas.util;

import java.util.List;

public final class ListUtils {

    private ListUtils() {
    }

    public static boolean isNullOrEmpty(final List list) {
        return list == null || list.isEmpty();
    }
}
