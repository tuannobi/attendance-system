package com.ty.attendancesystem.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeConvertUtil {
    public static String convertLocalDateToString(LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = localDate.format(formatter);
        return formattedString;
    }
}
