package com.bestsellers.assignment.gamersdirectory.util;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageHelper {

    private final MessageSource messageSource;

    public String getMessage(final String key, final String... variables) {
        if (ArrayUtils.isEmpty(variables)) {
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } else {
            return messageSource.getMessage(key, variables, LocaleContextHolder.getLocale());
        }
    }
}
