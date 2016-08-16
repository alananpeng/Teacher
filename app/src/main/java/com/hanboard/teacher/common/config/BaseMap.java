package com.hanboard.teacher.common.config;

import com.hanboard.teacherhd.lib.common.utils.DESCoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/4.
 */
public class BaseMap extends HashMap<String,String>{

    public BaseMap() {
    }
    public Map<String,String> initMap() throws Exception {
        Map<String,String> map=new HashMap<>();

        map.put("access_token",new DESCoder(CoderConfig.CODER_CODE).encrypt(CoderConfig.TOKEN));
        map.put("app_key",new DESCoder(CoderConfig.CODER_CODE).encrypt(CoderConfig.APP_KEY));
        return map;
    }


}
