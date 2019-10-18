package site.daipeng.boot.demo.dto;

import lombok.Data;

/**
 * @author daipeng
 * @date 2019/10/15 20:21
 * @description
 */
@Data
public class ConditionsDTO {
    private String tagid;
    private String data_type;
    private ConditionDTO condition;
}
