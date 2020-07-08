package reinty.study.seckill.core.common;

/**
 * 结果枚举
 *
 */

public enum ResultEnum {

    SUCCESS(1, "成功"),
    FAIL(-1, "失败");

    public final int code;

    public final String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
