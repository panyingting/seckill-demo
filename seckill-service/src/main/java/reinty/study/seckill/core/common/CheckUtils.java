package reinty.study.seckill.core.common;



import reinty.study.seckill.core.common.exception.ExceptionCodeEnum;
import reinty.study.seckill.core.common.exception.SecKillBusinessException;

import java.util.Collection;

/**
 * 判断并抛出异常：
 * 一般第一个参数是判断的对象，第二个（ msg ）是抛出异常时要抛出的信息；
 */
public class CheckUtils {

    public static void checkIfNull(Object object, String msg){
        checkIfNull(object, msg, null);
    }

    public static void checkIfEmpty(Collection obj, String msg){
        checkIfEmpty(obj, msg, null);
    }

    public static void checkIfEmpty(String obj, String msg){
        checkIfEmpty(obj, msg, null);
    }

    public static void checkIfNonPositive(Long num, String msg){
        if(num == null || num < 1){
            throw new SecKillBusinessException(msg);
        }
    }
    /**
     * 集合有多个值时返回错误信息，一般配合 checkIfEmpty 一起使用来判断集合只有一个值；
     */
    public static void checkMultiValue(Collection obj, String msg){
        checkMultiValue(obj, msg, null);
    }

    public static void checkIfTrue(boolean expression, String msg){
        checkIfTrue(expression, msg, null);
    }


    /****** 重载 ********/

    public static void checkIfNull(Object object, String msg, ExceptionCodeEnum exceptionCode){
        if(object == null){
            throw  new SecKillBusinessException(msg, exceptionCode);
        }
    }

    public static void checkIfEmpty(Collection obj, String msg, ExceptionCodeEnum exceptionCode){
        if(obj == null || obj.size() == 0){
            throw  new SecKillBusinessException(msg, exceptionCode);
        }
    }

    public static void checkIfEmpty(String obj, String msg, ExceptionCodeEnum exceptionCode){
        if(obj == null || obj.length() == 0){
            throw  new SecKillBusinessException(msg, exceptionCode);
        }
    }

    /**
     * 集合有多个值时返回错误信息，一般配合 checkIfEmpty 一起使用来判断集合只有一个值；
     */
    public static void checkMultiValue(Collection obj, String msg, ExceptionCodeEnum exceptionCode){
        if(obj != null && obj.size() > 1){
            throw new SecKillBusinessException(msg, exceptionCode);
        }
    }

    public static void checkIfTrue(boolean expression, String msg, ExceptionCodeEnum exceptionCode){
        if(expression){
            throw new SecKillBusinessException(msg, exceptionCode);
        }
    }

    public static void checkIfTrue(boolean expression, int code, String msg){
        if(expression){
            throw new SecKillBusinessException(code, msg, null);
        }
    }

    public static void checkIfTrue(boolean expression, int code, String msg, Object data){
        if(expression){
            throw new SecKillBusinessException(code, msg, data);
        }
    }
}
