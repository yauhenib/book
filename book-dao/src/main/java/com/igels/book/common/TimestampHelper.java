package com.igels.book.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This helper to:
 * Get current timestamp as String.
 */
public class TimestampHelper {

    /**
     * Get timestamp.
     *
     * @return String
     */
    public static String getTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSX");
        return dateFormat.format(new Date());
    }
}
