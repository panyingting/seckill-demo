package reinty.study.seckill.core.common.exception;


import reinty.study.seckill.core.common.WebResult;

import javax.servlet.http.HttpServletResponse;

public class SecKillBusinessException extends RuntimeException{

    private ExceptionCodeEnum exceptionCode = null;
    private Object data = null;
    private int code = ExceptionCodeEnum.SYS_EXCEPTION.code;

    public SecKillBusinessException(String msg){
        super(msg);
    }

    public SecKillBusinessException(String msg, ExceptionCodeEnum exceptionCode){
        super(msg);
        this.exceptionCode = exceptionCode;
    }
    public SecKillBusinessException(int code, String msg, Object data){
        super(msg);
        this.code = code;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }


    public WebResult<Object> getExceptionServiceResponse(){
        if(exceptionCode == null){
            return WebResult.failResult( code,  getMessage(),data);
        }
        return  WebResult.failResult(exceptionCode.code, exceptionCode.message, data);
    }

}
