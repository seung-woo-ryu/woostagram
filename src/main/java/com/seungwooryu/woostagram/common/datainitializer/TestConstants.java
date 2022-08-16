package com.seungwooryu.woostagram.common.datainitializer;

import org.springframework.context.annotation.Profile;

@Profile("dev")
public class TestConstants {
    public static final String DEFAULT_PASSWORD = "qwerasdf";
    public static final String DEFAULT_COMMENT = "default comment";
    public static final String DEFAULT_URL = "default_url";
    public static final String DEFAULT_CONTENTS = "default contents";
    public static final String DEFAULT_NAME = "seungwoo";
    public static final String DEFAULT_HASHTAG = "#hashtag";
    public static final String UNCORRECT_EMAIL = "tmddn312";
    public static final String NEW_EMAIL = "new@naver.com";
    public static final String NEW_NICKNAME = "new_nickname";

}
