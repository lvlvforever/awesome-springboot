package site.daipeng.boot.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author daipeng
 * @date 2019/10/15 20:20
 * @description
 */
@Data
public class CompositionValueDTO {
    private String judge;
    private List<ConditionsDTO> conditions;


}
