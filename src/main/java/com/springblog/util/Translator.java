package com.springblog.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {

    private static ResourceBundleMessageSource resourceBundleMessageSource;

    Translator(ResourceBundleMessageSource resourceBundleMessageSource) {
        Translator.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    public static String getMessage(String code, Object ...args){
        Locale locale = LocaleContextHolder.getLocale();
        return resourceBundleMessageSource.getMessage(code, args, locale);
    }
}
