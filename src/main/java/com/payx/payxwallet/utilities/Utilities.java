package com.payx.payxwallet.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilities {

    private static final Logger logger = LoggerFactory.getLogger(Utilities.class);

    public static boolean isEmpty(Object obj) {
        logger.debug("Checking if object is empty: {}", obj);

        if (obj == null) {
            logger.debug("Object is null.");
            return true;
        }

        if (obj instanceof String str) {
            boolean result = str.trim().isEmpty();
            logger.debug("Object is a String. Is empty: {}", result);
            return result;
        }

        if (obj instanceof java.util.Collection<?> collection) {
            boolean result = collection.isEmpty();
            logger.debug("Object is a Collection. Is empty: {}", result);
            return result;
        }

        if (obj instanceof java.util.Map<?, ?> map) {
            boolean result = map.isEmpty();
            logger.debug("Object is a Map. Is empty: {}", result);
            return result;
        }

        if (obj.getClass().isArray()) {
            boolean result = java.lang.reflect.Array.getLength(obj) == 0;
            logger.debug("Object is an Array. Is empty: {}", result);
            return result;
        }

        if (obj instanceof java.util.Optional<?> optional) {
            boolean result = optional.isEmpty();
            logger.debug("Object is an Optional. Is empty: {}", result);
            return result;
        }

        boolean result = obj.toString().trim().isEmpty();
        logger.debug("Object is of unknown type. Is empty: {}", result);
        return result;
    }
}