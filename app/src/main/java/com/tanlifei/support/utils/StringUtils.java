package com.tanlifei.support.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tanlifei
 * @ClassName: StringUtil
 * @Description: 字符串操作工具类
 * @date 2015-01-26 上午10:43:16
 */
public class StringUtils {

    private StringUtils() {

    }

    /**
     * @param str 源字符串
     * @return int 返回类型
     * @Title: convert2Int
     * @Description: 字符串转为整型，如转换出异常将返回0
     */
    public static int convert2Int(String str) {
        try {
            int i = Integer.parseInt(str);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param str 操作字符串
     * @return String 返回类型
     * @Title: trim
     * @Description: 去除字符串的前后空格
     */
    public static String trim(String str) {
        if (str == null)
            return null;
        return str.trim();
    }

    /**
     * 是否为空或者长度 为0
     *
     * @param str
     * @return boolean 返回类型
     * @Title: isBlank
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * @param str 需要处理的字符串
     * @return String 返回类型
     * @Title: trimorempty
     * @Description: 返回清空和去除空前后格的字符串
     */
    public static String trimorempty(String str) {
        String result = "";
        if (str == null)
            result = "";
        else
            result = trim(str);
        return result;

    }

    /**
     * @param str 源字符串
     * @return boolean 返回类型
     * @Title: isEmpty
     * @Description: 判断是否是空字符串
     */
    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        if ("".equals(str.trim()))
            return true;
        return false;
    }

    public static String getStr(String str) {
        if (str == null)
            return "";
        if ("".equals(str.trim()))
            return "";
        else
            return str;
    }

    /**
     * compare two string
     *
     * @param actual
     * @param expected
     * @return
     * @see ObjectUtils#isEquals(Object, Object)
     */

    /**
     * 两字符串是否相等
     *
     * @param actual
     * @param expected
     * @return boolean 返回类型
     * @Title: isEquals
     */
    public static boolean isEquals(String actual, String expected) {
        return actual == expected
                || (actual == null ? expected == null : actual.equals(expected));
    }

    /**
     * @param source 源字符串
     * @param regex  正则表达式
     * @return String 返回类型
     * @Title: getRegexString
     * @Description: 从字符串中得到所有匹配的字符串
     */
    public static String getRegexString(String source, String regex) {
        if ((null == source) || (null == regex))
            return "";
        StringBuffer result = new StringBuffer();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result.append(matcher.group());
        }
        return result.toString();
    }

    /**
     * @param str
     * @return String 返回类型
     * @Title: setNbsp
     * @Description: 滤除空格
     */
    public static String setNbsp(String str) {
        int j = str.length();
        StringBuffer stringbuffer = new StringBuffer(j + 500);
        for (int i = 0; i < j; i++) {
            if (str.charAt(i) == ' ') {
                stringbuffer.append(" ");
            } else {
                stringbuffer.append(str.charAt(i) + "");
            }
        }
        return stringbuffer.toString();
    }

    /**
     * @param input 输入的字符串
     * @return boolean 返回类型
     * @Title: isNumeric
     * @Description: 判断字符串是否全是数字字符
     */
    public static boolean isNumeric(String input) {
        if (isEmpty(input)) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (!Character.isDigit(charAt)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param input          输入的字符串
     * @param sourceEncoding 源字符集名称
     * @param targetEncoding 目标字符集名称
     * @return String 返回类型
     * @Title: changeEncoding
     * @Description: 用一句话描述该文件做什么
     */
    public static String changeEncoding(String input, String sourceEncoding,
                                        String targetEncoding) {
        if (input == null || input.equals("")) {
            return input;
        }
        try {
            byte[] bytes = input.getBytes(sourceEncoding);
            return new String(bytes, targetEncoding);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return input;
    }

    /**
     * 转化为UTF-8字符集
     *
     * @param str
     * @param defultReturn
     * @return String 返回类型
     * @Title: utf8Encode
     * @Description: 用一句话描述该文件做什么
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                if (!isEmpty(defultReturn))
                    return URLEncoder.encode(str, defultReturn);
                else
                    return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 在html中处理特殊字符
     *
     * @param source
     * @return String 返回类型
     * @Title: htmlEscapeCharsToString
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     * @throws:throws
     */
    public static String htmlEscapeCharsToString(String source) {
        return StringUtils.isEmpty(source) ? source : source
                .replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }


    /** 去除数据后面有.0或者.00的操作
     * 并且超过10000显示9999+
     * @param str
     * @return
     */
    public static String price(String str){
        String strRemove = str;
        try {
            double douStr = Double.parseDouble(str);
            if (douStr>9999) {
                strRemove = "9999+";
            }else{
                int intStr = (int) douStr;
                if (douStr == intStr) {
                    strRemove = intStr+"";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strRemove;
    }
    /** 公用的暂无简介 **/
    public static String isEmptyDesc(String desc) {
        if (isEmpty(desc) || "null".equals(desc)) {
            return "暂无简介";
        } else {
            return desc;
        }

    }
    /** 数字大于9999 则显示 9999+ **/
    @SuppressWarnings("finally")
    public static String displayNum(String num) {
        String result = "0";
        if (isEmpty(num) || "null".equals(num)) {
            return result;
        }
        try {
            if (Integer.parseInt(num) > 9999) {
                result = "9999+";
            } else {
                result = num;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    /**
     * 将英文双引号变成中文双引号
     *
     * @param strOri
     *            待处理字符串
     * @return
     */
    public static String specialSymbol(String strOri) {
        String regex = "\"\\w*?\"";// 正则表达式匹配串
        String regex1 = "\'\\w*?\'";// 正则表达式匹配串
        String str, str2;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strOri);

        Pattern pattern2 = Pattern.compile(regex1);
        Matcher matcher2 = pattern2.matcher(strOri);
        while (matcher.find()) {
            str = matcher.group();
            str2 = str;
            str2 = str2.replaceAll("\"", "");// 去掉前后双引号
            str2 = "“" + str2 + "”"; // 前后添上中文双引号
            strOri = strOri.replace(str, str2);
        }

        while (matcher2.find()) {
            str = matcher2.group();
            str2 = str;
            str2 = str2.replaceAll("'", "");// 去掉前后单引号
            str2 = "‘" + str2 + "’"; // 前后添上中文单引号
            strOri = strOri.replace(str, str2);
        }

        strOri = strOri.replace("'", "‘").replace("\"", "“");
        return strOri;// 输出结果
    }

    /** 是不是手机号 **/
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /** 隐藏手机号中间四位 ***/
    public static String hideMobileNo(String mobiles) {
        try {
            return mobiles.substring(0,
                    mobiles.length() - (mobiles.substring(3)).length())
                    + "****" + mobiles.substring(7);
        } catch (Exception e) {
            return mobiles;
        }
    }

    /** 是不是电子邮件 **/
    public static boolean isMailNo(String mail) {
        // 电子邮件
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(mail);
        return matcher.matches();
    }

    /** 隐藏电子邮件，从第二位开始隐藏3位 **/
    public static String hideMailNo(String mail) {
        if (null == mail || isEmpty(mail)) {
            return "";
        }
        String re = "邮箱不正确";
        if (isMailNo(mail)) {
            re = mail;
        } else {
            return re;
        }
        String prefix = re.substring(0, re.lastIndexOf("@"));
        String suffix = re.substring(re.lastIndexOf("@"));
        if (prefix.length() == 1) {
            return re + suffix;
        } else if (prefix.length() == 2) {
            return prefix.substring(0, 1) + "*" + suffix;
        } else if (prefix.length() == 3) {
            return prefix.substring(0, 1) + "**" + suffix;
        } else if (prefix.length() == 4) {
            return prefix.substring(0, 1) + "***" + suffix;
        } else {
            return prefix.substring(0, 1) + "***"
                    + prefix.substring(4, prefix.length()) + suffix;
        }

    }
    /** 去除数据后面有.0或者.00的操作
     * 并且超过10000显示9999+
     * @param str
     * @return
     */
    public static String removeZeroAnd4LenthString(String str){
        String strRemove = str;
        try {
            double douStr = Double.parseDouble(str);
            if (douStr>9999) {
                strRemove = "9999+";
            }else{
                int intStr = (int) douStr;
                if (douStr == intStr) {
                    strRemove = intStr+"";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strRemove;
    }

    /** 输入的密码要包含 数字 字母 符号 */
    public static boolean isPasswordRight(String psd){
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(psd);
        if (m.matches()) {
            ToastUtils.show("密码必须包含数字、字母、特殊符号中的两种！");
            return false;
        }
        p = Pattern.compile("[a-zA-Z]*");
        m = p.matcher(psd);
        if (m.matches()) {
            ToastUtils.show("密码必须包含数字、字母、特殊符号中的两种！");
            return false;
        }
        return true;
    }
}