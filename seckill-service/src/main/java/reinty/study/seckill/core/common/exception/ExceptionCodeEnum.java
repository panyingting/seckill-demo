package reinty.study.seckill.core.common.exception;

import javax.servlet.http.HttpServletResponse;

public enum ExceptionCodeEnum {


    SYS_EXCEPTION( ErrorCode.FAIL, "请求失败，稍后再试"),

    PARAM_ERROR( ErrorCode.PARAM_ERROR, "参数错误"),

    SC_UNAUTHORIZED( HttpServletResponse.SC_UNAUTHORIZED, "无效的请求,请先登录"),

    STOCK_NULL( ErrorCode.STOCK_NULL, "已售罄"),

    STOCK_NOT_ENOUGH( 90, "库存不足"),

    NONSUPPORT_DELIVERY( 610, "该地区不支持配送"),

    NOT_LOGIN( ErrorCode.INTERNAL_ERROR, "请先登录"),

    GOODS_INFO_ERROR( ErrorCode.FAIL, "错误的商品"),

    GOODS_DETAIL_REQUEST_FAIL( ErrorCode.FAIL, "商品详情请求失败");

    public final int code;
    public final String message;

    ExceptionCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

}
