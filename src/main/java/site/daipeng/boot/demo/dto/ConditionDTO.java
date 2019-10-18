package site.daipeng.boot.demo.dto;

import lombok.Data;

/**
 * @author daipeng
 * @date 2019/10/15 20:23
 * @description
 */
@Data
public class ConditionDTO {
    private String ge;
    private String le;
    private String in;
    private String ne;
}
