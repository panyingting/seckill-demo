package reinty.study.seckill.core.common.exception;

public class ErrorCode {
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int PARAM_ERROR = 400;
    public static final int NODATA_ERROR = 404;
    public static final int UNAUTHORIZED = 401;
    public static final int INTERNAL_ERROR = 500;
    public static final int EXISTED = 604;
    public static final int TIMEOUT = 408;
    /**
     * 库存不足
     */
    public static final int STOCK_NULL = 20001;
}
