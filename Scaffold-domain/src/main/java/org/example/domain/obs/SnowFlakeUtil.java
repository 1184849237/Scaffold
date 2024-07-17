package org.example.domain.obs;

import cn.hutool.core.util.IdUtil;

/**
 * @ClassName: SnowFlakeUtil
 * @Description: TODO
 * @Create by: 周鹏程
 * @Date: 2024/7/15 15:52
 */
public class SnowFlakeUtil {

    private final static long WORKER_ID = 1;
    private final static long DATA_CENTER_ID = 1;

    public static long nextId() {
        return IdUtil.getSnowflake(WORKER_ID,DATA_CENTER_ID).nextId();
    }
}
