package com.accenture.flowershop.util;


import com.accenture.flowershop.exception.NotFoundException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, long id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, String id) {
        return checkNotFound(object, id);
    }

    public static void checkNotFoundWithId(boolean found, long id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }
}