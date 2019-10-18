package site.daipeng.boot.demo.reflect;

import com.qq.taf.holder.JceHashMapHolder;

import java.util.HashMap;

/**
 * @author daipeng
 * @date 2019/10/15 10:09
 * @description
 */
public class AdService {
    public static int queryAd(int adId, String name, JceHashMapHolder holder) {
        System.err.println("adId" + adId);
        System.err.println("name" + name);

        HashMap<String, Object> value = new HashMap<>();
        value.put("adname", "qqreader");
        value.put("number", 1);
        holder.value = value;
        return 100;
    }
}
