package com.payx.payxwallet.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    private static final Logger logger = LoggerFactory.getLogger(Constants.class);

    public static final String USER_CREATION_SUCCESS_MESSAGE = "User created successfully.";
    public static final String USER_NOT_FOUND_ERROR_MESSAGE = "User with the given user id not found in the database.";
    public static final String NO_USER_FOUND = "No user found.";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error.";
    public static final String USER_UPDATE_SUCCESS_MESSAGE = "User details updated successfully!";
    public static final String USER_DELETE_SUCCESS_MESSAGE = "User with given user id deleted successfully.";
    public static final String MOBILE_INVALID = "Mobile number must be exactly 10 digits";
    public static final String EMAIL_INVALID = "Email must be a valid address";
    public static final String AMOUNT_INVALID = "Minimum amount must be 1.0";

    static {
        logger.info("Constants class loaded with predefined messages.");
    }
}