package site.daipeng.boot.demo.reflect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qq.jcommon.logging.LogUtil;
import com.qq.taf.holder.JceHashMapHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import site.daipeng.boot.demo.dto.*;
import site.daipeng.boot.demo.utli.NumberUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

/**
 * @author daipeng
 * @date 2019/10/14 17:13
 * @description
 */
@RestController
@RequestMapping("ad")
public class ForwarderController {
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("magic")
    public Map<String,Object> magic(HttpServletRequest request, @RequestBody MagicParam param) {
        Map<String, Object> response = new HashMap<>();
        Long id = add();
        System.err.println(id);
        return response;
    }
    public Long add() {
        Long id = -1L;
        String targetUrl = "http://tag.inner.yuewen.local/tag/group/add";
        TagDTO dto = parseTagToDTO();
        try {
            ResponseEntity<String> obj = restTemplate.postForEntity(targetUrl, dto,String.class);
            System.err.println(JSON.toJSON(dto));
            if (obj.getStatusCode() == HttpStatus.OK) {
               String ret = obj.getBody();
                JSONObject jsonObject = JSONObject.parseObject(ret);
                System.err.println(jsonObject);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return id;
    }


    private TagDTO parseTagToDTO() {
        TagDTO dto = new TagDTO();
        dto.setCid(11);
        dto.setPlatform(110000);
        dto.setStatus(4);
        dto.setIdtype(1);
        dto.setLabel_group("1");
        dto.setPurpose("1");
        dto.setData_source(1);
        dto.setExpire_time("2019-09-09 00:00:00");
        dto.setUpdate_frequency(1);

        LabelUpdateTimeDTO labelUpdateTimeDTO = new LabelUpdateTimeDTO();
        labelUpdateTimeDTO.setType(4);
        dto.setLabel_update_time(labelUpdateTimeDTO);


        CompositionValueDTO compositionValueDTO = new CompositionValueDTO();
        List<ConditionsDTO> list = new ArrayList<>();

        ConditionsDTO one = new ConditionsDTO();
        one.setTagid("1231321321313213");
        one.setData_type("bigint");
        ConditionDTO conditionDTO = new ConditionDTO();
        conditionDTO.setGe("10");
        one.setCondition(conditionDTO);

        list.add(one);
        compositionValueDTO.setConditions(list);

        compositionValueDTO.setJudge("c1");
        dto.setComposition_value(compositionValueDTO);

        dto.setLabel_name("label_name");
        dto.setReviser("daipeng@yuewen.com");

        return dto;
    }

    private Object parseParam(Object callParams, String type) {
        if (type.contains("int")) {
            return NumberUtil.parseStringOrIntegerOrLongToInteger(callParams);
        } else if (type.contains("long")) {
            return NumberUtil.parseStringOrIntegerOrLongToLong(callParams);
        } else if (type.contains("String")) {
            return String.valueOf(callParams);
        }
        else if (type.endsWith("JceHashMapHolder") || callParams == null) {
            JceHashMapHolder holder = new JceHashMapHolder();
            return holder;
        }
        return null;
    }





    public boolean correctMethod(Method method, MagicParam param) {
        if (method == null || param == null) {
            return false;
        }
        if (!method.getName().equals(param.getMethodName())) {
            return false;
        }
        Parameter[] parameters = method.getParameters();
        List<Object> params = param.getParameters();

        int methodParamCount = parameters.length;
        int callParamCount = params == null ? 0 : params.size();

        if (methodParamCount == 0 && callParamCount != 0) {
            return false;
        }else if(methodParamCount == 0 && callParamCount == 0 ){
            return true;
        }

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            AnnotatedType annotatedType = parameter.getAnnotatedType();
            Type type = annotatedType.getType();
            String typeName = type.getTypeName();
            if (typeName.contains("Jce") && typeName.contains("Holder")) {
                methodParamCount--;
            }
        }
        return methodParamCount == callParamCount;
    }








}
