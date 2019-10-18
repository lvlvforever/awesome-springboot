package site.daipeng.boot.demo.utli;

/**
 * @author daipeng
 * @date 2019/9/24 14:28
 * @description
 */
public class NumberUtil {

    public static Long parseStringOrIntegerOrLongToLong(Object value) {
        Long result = -1L;
        if (value == null) {
            return result;
        }
        if (value instanceof Long) {
            return (Long) value;
        }
        if (value instanceof Integer) {
            return ((Integer) value).longValue();
        }
        if (value instanceof String) {
            return Long.valueOf((String) value);
        }
        return result;
    }
    public static Integer parseStringOrIntegerOrLongToInteger(Object value) {
        Integer result = -1;
        if (value == null) {
            return result;
        }
        if (value instanceof Long) {
            return ((Long) value).intValue();
        }
        if (value instanceof Integer) {
            return ((Integer) value);
        }
        if (value instanceof String) {
            return Integer.valueOf((String) value);
        }
        return result;
    }


}
