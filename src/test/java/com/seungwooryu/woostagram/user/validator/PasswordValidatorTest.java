package com.seungwooryu.woostagram.user.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    @Test
    @DisplayName("password 패턴 매칭")
    void stringTest() throws Exception {
        String pattern = "^[a-zA-Z0-9]+$";

        String p1 = "abc!1";
        String p2 = "1213";
        String p3 = "abcc12";
        String p4 = "1234";
        String p5 = "";

        assertFalse(Pattern.matches(pattern, p1));
        assertTrue(Pattern.matches(pattern, p2));
        assertTrue(Pattern.matches(pattern, p3));
        assertTrue(Pattern.matches(pattern, p4));
        assertFalse(Pattern.matches(pattern, p5));
    }
}
