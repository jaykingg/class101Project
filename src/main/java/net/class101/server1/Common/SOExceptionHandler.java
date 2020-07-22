package net.class101.server1.Common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SOExceptionHandler {

    @ExceptionHandler(SoldOutException.class)
    public AppError sampleError(SoldOutException e){
        AppError appError = new AppError();
        appError.setMessage("SoldOutException");
        appError.setReason("재고가 모두 소진되었습니다.");
        return appError;
    }
}
