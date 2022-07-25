package com.seungwooryu.woostagram.common.utils;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ApiUtilsTest {
    @Test
    @DisplayName("ApiResult 성공")
    void success() {
        //given
        List<String> json = new ArrayList<>();
        json.add("json");

        //when
        ApiResult<String> apiResult = ApiUtils.success(json);

        //then
        log.info("ApiResult(success): {}", apiResult);

        assertThat(apiResult.isSuccess()).isEqualTo(true);
        assertThat(apiResult.getResponse()).isEqualTo("json");
        assertThat(apiResult.getError()).isNull();
    }

    @Test
    @DisplayName("ApiResult 에러")
    void error() {
        //given
        var status = HttpStatus.BAD_REQUEST;
        var throwable = new Throwable(String.valueOf(NullPointerException.class));

        //when
        var apiResult = ApiUtils.error(throwable.getMessage(),status);

        //then
        log.info("ApiResult(error): {}", apiResult);

        assertThat(apiResult.isSuccess()).isEqualTo(false);
        assertThat(apiResult.getResponse()).isNull();
        assertThat(apiResult.getError().getMessage()).isEqualTo(String.valueOf(NullPointerException.class));
        assertThat(apiResult.getError().getStatus()).isEqualTo(status.value());


    }
}