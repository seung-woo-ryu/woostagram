package com.seungwooryu.woostagram.common.utils;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.Null;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ApiUtilsTest {

    @Test
    @DisplayName("ApiResult 성공")
    void success() {
        //given
        String json = "json";

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
        var apiResult = ApiUtils.error(throwable, status);

        //then
        log.info("ApiResult(error): {}", apiResult);

        assertThat(apiResult.isSuccess()).isEqualTo(false);
        assertThat(apiResult.getResponse()).isNull();
        assertThat(apiResult.getError().getMessage()).isEqualTo(String.valueOf(NullPointerException.class));
        assertThat(apiResult.getError().getStatus()).isEqualTo(status.value());


    }
}