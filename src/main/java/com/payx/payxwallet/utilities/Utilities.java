package com.payx.payxwallet.utilities;

public class Utilities {

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String str) {
            return str.trim().isEmpty();
        }

        if (obj instanceof java.util.Collection<?> collection) {
            return collection.isEmpty();
        }

        if (obj instanceof java.util.Map<?, ?> map) {
            return map.isEmpty();
        }

        if (obj.getClass().isArray()) {
            return java.lang.reflect.Array.getLength(obj) == 0;
        }

        if (obj instanceof java.util.Optional<?> optional) {
            return optional.isEmpty();
        }

        return obj.toString().trim().isEmpty();
    }

    public static boolean isValidAmount(double amount, double minLimit, double maxLimit) {
        return amount > 0 && amount >= minLimit && amount <= maxLimit;
    }
}