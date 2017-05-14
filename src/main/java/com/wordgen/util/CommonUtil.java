/*
 * Copyright (c) 2012-2014, EpicSaaS Yuan Xin technology Co., Ltd.
 * 
 * All rights reserved.
 */
package com.wordgen.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author RayWang
 * @date 2016年2月23日 下午1:48:29
 */
@Component
public class CommonUtil {

    private Logger logger = Logger.getLogger(this.getClass());

    //////////////////////////////////////////////////////// 系统相关////////////////////////////



    //////////////////////////////////////////////////// 参数转换  ///////////////////////////////

    public Map<String, Object> convertQueryRangeParam(Map<String, Object> paramMap) {
        String queryRange = paramMap.get("type").toString();
        if ("companyData".equals(queryRange)) {
            paramMap.put("type", "all");
        } else if ("teamData".equals(queryRange)) {
            paramMap.put("type", "teamemployee");
        } else if ("myData".equals(queryRange)) {
            paramMap.put("type", "myown");
        } else {
            throw new RuntimeException("参数出错！");
        }
        return paramMap;
    }

    public String convertType(String type) {
        switch (type) {
            case "all": {
                type = "01";
                break;
            }
            case "team": {
                type = "02";
                break;
            }
            case "charge": {
                type = "03";
                break;
            }
            case "common": {
                type = "04";
                break;
            }
            case "relevant": {
                type = "05";
                break;
            }
            case "concerned": {
                type = "06";
                break;
            }
            default:
                break;
        }
        return type;
    }

    //////////////////////////////////////////////////// 附件相关//////////////////////////////

    public String convertFizeSize(long bytes) {
        try {
            if (bytes == 0)
                return "0 B";
            int k = 1024;
            String sizes[] = { "B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
            double i = Math.floor(Math.log(bytes) / Math.log(k));
            BigDecimal d = new BigDecimal(bytes / Math.pow(k, i));
            // java.text.DecimalFormat df = new
            // java.text.DecimalFormat("#.000");
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
            String str = df.format(d);
            str = str.trim();
            if (!str.equals("0")) {
                str = str.replaceAll("(\\.00$)|(0$)|(\\.$)", "");
            }
            return str + "" + sizes[(int) i];
        } catch (Exception e) {
            RuntimeException ex = new RuntimeException(e.getMessage() + "-->" + "文件大小单位转换失败！");
            ex.setStackTrace(e.getStackTrace());
            throw ex;
        }
    }

    //////////////////////////////////////////// 文本内容相关//////////////////////////////////////

    public String getSafeContent(String content) {
        String safeContent = StringEscapeUtils.escapeHtml(content);
        safeContent = safeContent.replaceAll("\r\n", "<br/>");
        safeContent = safeContent.replaceAll("\n", "<br/>");
        return safeContent;
    }

    public String getSafeContent0(String content) {
        String safeContent = StringEscapeUtils.escapeHtml(content);
        return safeContent;
    }

    public String getLinedContent(String nonLinedContent) {
        return nonLinedContent.replaceAll("<br/>", "\n");
    }

    /**
     * 从html中提取没格式的前lenOfParaphs行
     * 
     * @param styledContent
     * @param lenOfParaphs
     * @return
     */
    public String getUnstyledContent(String styledContent, int lenOfParaphs) {
        StringBuilder sb = new StringBuilder();
        if (styledContent != null && !styledContent.equals("")) {
            String[] contents = styledContent.split("<br/>");
            int count = 0;
            for (int i = 0; i < contents.length; i++) {
                if (StringUtils.isBlank(contents[i])) {
                    continue;
                }
                if (count < lenOfParaphs) {
                    sb.append("<p>" + contents[i] + "<p/>");
                }
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * html内容转换为纯文本
     * 
     * @param html
     * @return
     */
    public String html2PlainText(String html) {
        return html.replaceAll("\\<.*?\\>", "");
    }

    public Map<String, String> parse2StringMap(String arg) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            map = new ObjectMapper().readValue(arg, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            throw new RuntimeException("参数解析出错！");
        }
        return map;
    }

    public Map<String, Object> parse2Map(String arg) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = new ObjectMapper().readValue(arg, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw new RuntimeException("参数解析出错！");
        }
        return map;
    }

    public List<Map<String, String>> parse2MapList(String arg) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        try {
            mapList = new ObjectMapper().readValue(arg, new TypeReference<List<Map<String, String>>>() {});
        } catch (Exception e) {
            throw new RuntimeException("参数解析出错！");
        }
        return mapList;
    }

    public List<Map<String, Object>> parse2MapObjList(String arg) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        try {
            mapList = new ObjectMapper().readValue(arg, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            throw new RuntimeException("参数解析出错！");
        }
        return mapList;
    }
    
    public <T> Object string2Bean(String params, Class<T> clazz)  {
        try {
            return new ObjectMapper().readValue(params, clazz);
        } catch (Exception e) {
            throw new RuntimeException("参数解析出错！");
        }
    }

    ///////////////////////////////////////////////// 日期时间/////////////////////////////////////

    public String getExpandedYMDHMSStart(String ymdDate) {
        String ymdHMS = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ymdHMS = sdf.format(sdf.parse(ymdDate)) + " 00:00:00";
        } catch (Exception e) {
            logger.info("时间解析出错！");
        }
        return ymdHMS;
    }

    public String getExpandedYMDHMSEnd(String ymdDate) {
        String ymdHMS = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ymdHMS = sdf.format(sdf.parse(ymdDate)) + " 23:59:59";
        } catch (Exception e) {
            logger.info("时间解析出错！");
        }
        return ymdHMS;
    }

    public Map<String, String> getStartEndTime(String rangeType) {
        Map<String, String> ret = new HashMap<String, String>();
        String startTime = "";
        String endTime = "";

        if ("day".equals(rangeType)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            String monthStr = String.valueOf((month < 10 ? "0" + month : month));
            String dayStr = String.valueOf((day < 10 ? "0" + day : day));

            startTime = year + "-" + monthStr + "-" + dayStr + " 00:00:00";
            endTime = year + "-" + monthStr + "-" + dayStr + " 23:59:59";
        } else if ("month".equals(rangeType)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            String monthStr = String.valueOf((month < 10 ? "0" + month : month));
            String maxDayStr = String.valueOf((maxDay < 10 ? "0" + maxDay : maxDay));

            startTime = year + "-" + monthStr + "-01" + " 00:00:00";
            endTime = year + "-" + monthStr + "-" + maxDayStr + " 23:59:59";
        } else if ("quarter".equals(rangeType)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;

            int minMonthOfQuarter = 0;
            int maxMonthOfQuarter = 0;

            if (month >= 1 && month <= 3) {
                minMonthOfQuarter = 1;
                maxMonthOfQuarter = 3;
            }
            if (month >= 4 && month <= 6) {
                minMonthOfQuarter = 4;
                maxMonthOfQuarter = 6;
            }
            if (month >= 7 && month <= 9) {
                minMonthOfQuarter = 7;
                maxMonthOfQuarter = 9;
            }
            if (month >= 10 && month <= 12) {
                minMonthOfQuarter = 10;
                maxMonthOfQuarter = 12;
            }

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, maxMonthOfQuarter - 1);
            // 随便设置一天， 但此天要在有设置的月份里存在，不然Calendar会自动
            // 处理。比如，设置的月份为9，并且今天为31号，那么月份会自动调整到
            // 10月份。
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            int maxDayOfMaxMonthOfQuarter = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            String minMonthOfQuarterStr = String.valueOf((minMonthOfQuarter < 10 ? "0" + minMonthOfQuarter
                    : minMonthOfQuarter));
            String maxMonthOfQuarterStr = String.valueOf((maxMonthOfQuarter < 10 ? "0" + maxMonthOfQuarter
                    : maxMonthOfQuarter));

            startTime = year + "-" + minMonthOfQuarterStr + "-01 00:00:00";
            endTime = year + "-" + maxMonthOfQuarterStr + "-" + maxDayOfMaxMonthOfQuarter + " 23:59:59";
        } else if ("annual".equals(rangeType)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int year = calendar.get(Calendar.YEAR);
            calendar.set(Calendar.MONTH, 12 - 1);
            int lastDayOfDecember = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            startTime = year + "-01-01 00:00:00";
            endTime = year + "-12" + "-" + lastDayOfDecember + " 23:59:59";
        } else {
            throw new RuntimeException("参数错误！");
        }

        ret.put("startTime", startTime);
        ret.put("endTime", endTime);

        return ret;
    }

    public Map<String, String> getStartEndTime(String year, String rangeType) {
        Map<String, String> ret = new HashMap<String, String>();
        String startTime = "";
        String endTime = "";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(year));

        int startMonth = 1;
        int endMonth = 1;

        switch (rangeType) {
            case "year":
                startMonth = 1;
                endMonth = 12;
                break;
            case "firstHalf":
                startMonth = 1;
                endMonth = 6;
                break;
            case "secondHalf":
                startMonth = 7;
                endMonth = 12;
                break;
            case "q1":
                startMonth = 1;
                endMonth = 3;
                break;
            case "q2":
                startMonth = 4;
                endMonth = 6;
                break;
            case "q3":
                startMonth = 7;
                endMonth = 9;
                break;
            case "q4":
                startMonth = 10;
                endMonth = 12;
                break;
            case "1":
                startMonth = endMonth = 1;
                break;
            case "2":
                startMonth = endMonth = 2;
                break;
            case "3":
                startMonth = endMonth = 3;
                break;
            case "4":
                startMonth = endMonth = 4;
                break;
            case "5":
                startMonth = endMonth = 5;
                break;
            case "6":
                startMonth = endMonth = 6;
                break;
            case "7":
                startMonth = endMonth = 7;
                break;
            case "8":
                startMonth = endMonth = 8;
                break;
            case "9":
                startMonth = endMonth = 9;
                break;
            case "10":
                startMonth = endMonth = 10;
                break;
            case "11":
                startMonth = endMonth = 11;
                break;
            case "12":
                startMonth = endMonth = 12;
                break;
            default:
                break;
        }

        calendar.set(Calendar.MONTH, endMonth - 1);
        // 随便设置一天， 但此天要在有设置的月份里存在，不然Calendar会自动
        // 处理。比如，设置的月份为9，并且今天为31号，那么月份会自动调整到
        // 10月份。
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int maxDayOfEndMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        startTime = year + "-" + (startMonth < 10 ? "0" + startMonth : startMonth) + "-01 00:00:00";
        endTime = year + "-" + (endMonth < 10 ? "0" + endMonth : endMonth) + "-"
                + (maxDayOfEndMonth < 10 ? "0" + maxDayOfEndMonth : maxDayOfEndMonth) + " 23:59:59";

        ret.put("startTime", startTime);
        ret.put("endTime", endTime);

        return ret;
    }

    public Map<String, String> getStartEndTime(int year, int minMonth, int maxMonth) {
        Map<String, String> startEndTime = new HashMap<String, String>();
        String startTime = year + "-" + (minMonth < 10 ? "0" + minMonth : minMonth) + "-01 00:00:00";
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, maxMonth, 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String endTime = year + "-" + (maxMonth < 10 ? "0" + maxMonth : maxMonth) + "-" + maxDay + " 23:59:59";

        startEndTime.put("startTime", startTime);
        startEndTime.put("endTime", endTime);

        return startEndTime;
    }

    public Map<String, String> getMinMaxTimeOfCurrentMonth(Date date) {
        Map<String, String> map = new HashMap<String, String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String monthStr = (String) ((calendar.get(Calendar.MONTH) + 1) < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1)
                : (calendar.get(Calendar.MONTH) + 1 + ""));
        String dayStr = (String) ((calendar.getActualMinimum(Calendar.DAY_OF_MONTH)) < 10 ? "0"
                + (calendar.getActualMinimum(Calendar.DAY_OF_MONTH)) : (calendar
                .getActualMinimum(Calendar.DAY_OF_MONTH) + ""));
        String minTime = calendar.get(Calendar.YEAR) + "-" + monthStr + "-" + dayStr + " 00:00:00";
        String maxTime = calendar.get(Calendar.YEAR) + "-" + monthStr + "-"
                + (calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) + " 23:59:59";

        map.put("minTime", minTime);
        map.put("maxTime", maxTime);

        return map;
    }

    public String getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);

        return String.valueOf(year);
    }

    public int getQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int quarter = 1;

        if (month >= 1 && month <= 3) {
            quarter = 1;
        }
        if (month >= 4 && month <= 6) {
            quarter = 2;
        }
        if (month >= 7 && month <= 9) {
            quarter = 3;
        }
        if (month >= 10 && month <= 12) {
            quarter = 4;
        }

        return quarter;
    }

    public String getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;

        String monthStr = String.valueOf((month < 10 ? "0" + month : month));

        return monthStr;
    }

    public String[] generateMin2MaxMonths(int minMonth, int maxMonth) {
        String[] monthsArr = new String[maxMonth - minMonth + 1];
        for (int i = minMonth; i < maxMonth + 1; i++) {
            monthsArr[i - minMonth] = i + "月";
        }

        return monthsArr;
    }

    public String convertMonthStrToRangeType(String monthStr) {
        String rangeType = "";
        switch (monthStr) {
            case "1月":
                rangeType = "1";
                break;
            case "2月":
                rangeType = "2";
                break;
            case "3月":
                rangeType = "3";
                break;
            case "4月":
                rangeType = "4";
                break;
            case "5月":
                rangeType = "5";
                break;
            case "6月":
                rangeType = "6";
                break;
            case "7月":
                rangeType = "7";
                break;
            case "8月":
                rangeType = "8";
                break;
            case "9月":
                rangeType = "9";
                break;
            case "10月":
                rangeType = "10";
                break;
            case "11月":
                rangeType = "11";
                break;
            case "12月":
                rangeType = "12";
                break;
            default:
                break;
        }

        return rangeType;
    }

    ////////////////////////////////////////////////// 排序/中文相关
    ////////////////////////////////////////////////// //////////////////////////////

    // GENERAL_PUNCTUATION 判断中文的“号
    // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
    // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
    public static boolean isChineseChar(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public static boolean isChinese(String str) {
        boolean temp = false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }

    public class SimplePinyinComparator implements Comparator<String> {

        public int compare(String source, String target) {
            return Collator.getInstance(Locale.CHINESE).compare(source, target);
        }
    }

    // 中文按拼音排序
    // public <T> List<T> doPinyinSort(List<T> list, String field, String mode)
    // {
    // Collections.sort(list, new PinyinComparator<T>(mode, field));
    // return list;
    // }
    //
    // class PinyinComparator<T> implements Comparator<T> {
    // public static final String DESC_MODE = "desc";
    // public static final String ASC_MODE = "asc";
    // private String mode;
    // private String field;
    // public PinyinComparator(String mode, String field) {
    // if (mode.equals(DESC_MODE)) {
    // this.mode = DESC_MODE;
    // }
    // if (mode.equals(ASC_MODE)) {
    // this.mode = ASC_MODE;
    // }
    // this.field = field;
    // }
    // @Override
    // public int compare(T o1, T o2) {
    // try {
    // Object obj0 = getObjVal(o1, this.field);
    // Object obj1 = getObjVal(o2, this.field);
    // String s1 = PinyinUtil.hanziToPinyin((obj0 == null ? "" :
    // obj0.toString()));
    // String s2 = PinyinUtil.hanziToPinyin((obj1 == null ? "" :
    // obj1.toString()));
    // if (this.mode.equals(DESC_MODE)) {
    // if (s1.compareTo(s2) > 0) {
    // return 1;
    // } else {
    // return -1;
    // }
    // }
    // if (this.mode.equals(ASC_MODE)) {
    // if (s1.compareTo(s2) > 0) {
    // return -1;
    // } else {
    // return 1;
    // }
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // return 0;
    // }
    // return 0;
    // }
    // }
    //
    private Object getObjVal(Object obj, String field) throws Exception {
        if (obj == null)
            return obj;
        Object val = null;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field0 : fields) {
            field0.setAccessible(true);
            if (field0.getName().equals(field)) {
                val = field0.get(obj);
            }
        }
        return val;
    }

    // 字符串排序
    public <T> List<T> sortByString(List<T> list, final String field) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        try {
            Collections.sort(list, new Comparator<T>() {
                public int compare(T o1, T o2) {
                    String o1Val = "";
                    String o2Val = "";
                    try {
                        o1Val = getObjVal(o1, field).toString();
                        o2Val = getObjVal(o2, field).toString();
                    } catch (Exception ex) {
                        logger.error("值解析出错！");
                    }
                    return o1Val.compareTo(o2Val);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("排序出错！");
        }
        return list;
    }

    ///////////////////////////////////////// 内部类 /////////////////////////////

    //
    // class Msg {
    // private String msg;
    // private int age;
    // /**
    // * @return the msg
    // */
    // public String getMsg() {
    // return msg;
    // }
    // /**
    // * @param msg the msg to set
    // */
    // public void setMsg(String msg) {
    // this.msg = msg;
    // }
    // /**
    // * @return the age
    // */
    // public int getAge() {
    // return age;
    // }
    // /**
    // * @param age the age to set
    // */
    // public void setAge(int age) {
    // this.age = age;
    // }
    //
    // }
    //
    // public void sort() {
    // List<Msg> msgList = new ArrayList<Msg>();
    // Msg msg = new Msg();
    // msg.setMsg("我是凉开水房接收到");
    //
    // Msg msg0 = new Msg();
    // msg0.setMsg("凉开水房接收到");
    //
    // Msg msg1 = new Msg();
    // msg1.setMsg("是凉开水房接收到");
    //
    // Msg msg2 = new Msg();
    // msg2.setMsg("开水房接收到");
    //
    // msgList.add(msg);
    // msgList.add(msg0);
    // msgList.add(msg1);
    // msgList.add(msg2);
    //
    // this.doPinyinSort(msgList, "msg", "desc");
    //
    // for (Msg o : msgList) {
    // System.out.println(o.getMsg());
    // }
    // }
    //


    // public static void main(String[] args) {
    // SuperCommonUtil scu = new SuperCommonUtil();
    // scu.sort();
    // }

}
