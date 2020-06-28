package com.enzenith.common.config;

import com.enzenith.utils.util.StringUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * 日期自动转换（前后端交互）
 * @author jikunle
 * @date 2019/5/22 11:38
 */
@ControllerAdvice
public class DateConveterAdvice {

    private static String[] pattern =
            new String[]{"yyyy-MM", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S",
                    "yyyy.MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss.S",
                    "yyyy/MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss.S"};

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if(StringUtil.isEmpty(text)){
                    return;
                }
                try {
                    Date date = DateUtils.parseDate(text.trim(), DateConveterAdvice.pattern);
                    setValue(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
