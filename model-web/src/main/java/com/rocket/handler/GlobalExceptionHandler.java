package com.rocket.handler;

import com.rocket.domain.BasicResult;
import com.rocket.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public BasicResult bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("发生业务异常e:{}", e.getMessage());
        return BasicResult.fail(e.getErrorCode(), e.getErrorMsg());
    }

}
