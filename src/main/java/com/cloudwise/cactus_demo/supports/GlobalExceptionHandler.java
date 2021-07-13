package com.cloudwise.cactus_demo.supports;

import com.cloudwise.cactus_demo.exception.BizException;
import com.cloudwise.cactus_demo.pojo.Result;
import com.cloudwise.cactus_demo.pojo.ResultInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest req, BizException e){
        return Result.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e){
        return Result.error(ResultInfo.BODY_NOT_MATCH);
    }


    /**
     * 处理404的异常
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Result noHandlerFoundException(Exception ex){
        return Result.error(ResultInfo.NOT_FOUND);
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception e){
        return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
    }
}
