package com.seungwooryu.woostagram.common.utils;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        assertThat(apiResult.getResponse()).isEqualTo(json);
        assertThat(apiResult.getError()).isNull();
    }

    @Test
    @DisplayName("ApiResult 에러2")
    void error2() {
        //given
        var status = HttpStatus.BAD_REQUEST;

        //when
        var apiResult = ApiUtils.error(status.getReasonPhrase(), status);

        //then
        log.info("ApiResult(error): {}", apiResult);

        assertThat(apiResult.isSuccess()).isEqualTo(false);
        assertThat(apiResult.getResponse()).isEmpty();
        assertThat(apiResult.getError().getMessage()).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
        assertThat(apiResult.getError().getStatus()).isEqualTo(status.value());
    }
}
