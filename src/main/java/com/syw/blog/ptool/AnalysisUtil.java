package com.syw.blog.ptool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AnalysisUtil {

    private static Logger logger = LoggerFactory.getLogger(AnalysisUtil.class);

    public static List<Integer> AnalysisList(String analysisStr) {

        List<Integer> list = new ArrayList<>();

        analysisStr = analysisStr.substring(1, analysisStr.length() - 1);
        logger.info("string截取后的值:{}", analysisStr);
        String[] list1 = analysisStr.split(",");
        for (String str : list1) {
            Integer i = Integer.valueOf(str);
            list.add(i);
            logger.info("str信息:{}", str.trim());
        }

        return list;

    }

}
