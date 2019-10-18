package site.daipeng.boot.demo.reflect;

import lombok.Data;

import java.util.List;

/**
 * @author daipeng
 * @date 2019/10/14 17:20
 * @description
 */
@Data
public class MagicParam {
    private String methodName;
    private List<Object> parameters;
}
