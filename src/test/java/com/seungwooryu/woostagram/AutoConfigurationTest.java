package com.seungwooryu.woostagram;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AutoConfigurationTest {
    @Value("${test}")
    private String testValue;

    @Test
    void ymlValuePrint() {
        log.info("testValue: {}", testValue);

    }
}

