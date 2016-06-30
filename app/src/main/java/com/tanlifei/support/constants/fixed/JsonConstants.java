package com.tanlifei.support.constants.fixed;

/**
 * 所有跟JSON 相关的常量，包括json字段，结果
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class JsonConstants {

    // 分页参数
    public static final String REQUEST_TASK_LIST_PARAM_PAGE_SIZE = "pageSize";//每页数的key值
    public static final String REQUEST_TASK_LIST_PARAM_PAGE_NUMBER = "pageNumber";//当前页数的key值
    public static final int PAGE_SIZE = 10; // 每页显示 的数据条数
    public static final int COMMUNITY_MEMBER_PAGE_SIZE = 40; // 每页显示 的数据条数


    public static final String JSON_LIST = "list";
    public static final String json_data = "data";
    public static String JSON_RESULT = "result";//请求接口返回结果
    public static String JSON_RESULT_SUCCEE = "1";//请求接口返回结果为成功
    public static String JSON_RESULT_FAILURE = "0";//请求接口返回结果为失败
    public static final String JSON_TASK_URL = "url";//请求接口url存储map的中的key值




}
