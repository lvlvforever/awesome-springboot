package site.daipeng.boot.demo.dto;

import lombok.Data;

/**
 * @author daipeng
 * @date 2019/10/15 20:17
 * @description
 */
@Data
public class LabelUpdateTimeDTO {
    private String day;
    private String week;
    private String hour;
    private String minute;
    private String date;
    private int type;
}
