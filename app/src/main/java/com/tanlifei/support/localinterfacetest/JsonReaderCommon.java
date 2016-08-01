package com.tanlifei.support.localinterfacetest;

import com.tanlifei.framework.R;
import com.tanlifei.support.constants.fixed.UrlConstants;

import java.util.Map;

/**
 * Created by tanlifei on 16/1/28.
 */
public class JsonReaderCommon {
    /**
     * 公用模块,上线时注释下面的代码即可
     */
    public static void addTestUrl(Map<String, JsonReaderBean> jsonReader) {
        jsonReader.put(UrlConstants.APP_VERSION_UPDATE, new JsonReaderBean(R.raw.version_udpate, false));
    }


}
