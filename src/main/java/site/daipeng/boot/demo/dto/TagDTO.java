package site.daipeng.boot.demo.dto;

import lombok.Data;

/**
 * @author daipeng
 * @date 2019/10/15 20:05
 * @description
 */
@Data
public class TagDTO {
    private int cid;
    private int status;
    private int platform;
    private int idtype;
    private String label_group;
    private String purpose;
    private int data_source;
    private String expire_time;
    private int update_frequency;
    private LabelUpdateTimeDTO label_update_time;
    private CompositionValueDTO composition_value;
    private String label_name;
    //email
    private String reviser;
}
