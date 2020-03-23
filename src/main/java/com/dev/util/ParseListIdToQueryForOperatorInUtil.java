package com.dev.util;

import java.util.List;

public class ParseListIdToQueryForOperatorInUtil {
    static public String parse(List<Long> idCities) {
        String idString = "";
        long size = idCities.size();
        for (int i = 0; i < size; i++) {
            idString += String.format("'%s'", idCities.get(i));
            if (i < size - 1) {
                idString += ",";
            }
        }
        return idString;
    }
}
