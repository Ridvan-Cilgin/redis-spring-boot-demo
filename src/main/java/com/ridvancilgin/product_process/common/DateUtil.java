package com.ridvancilgin.product_process.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    public static Date convertDateFromStringWithFormat(String value, String dateFormat) {
        Date dateValue = null;
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        if (value != null) {
            try {
                dateValue = formatter.parse(value);
            } catch (Exception e) {
                return null;
            }
        }
        return dateValue;
    }

}