package com.project.shophandmade.components;

import com.project.shophandmade.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@RequiredArgsConstructor
@Component
public class LocalizationUtils {

    //bien duong dan
    private final MessageSource messageSource;
    // danh sach cac thuoc tinh
    private final LocaleResolver localeResolver;

    //lay
    public String getLocalizedMessage(String messageKey, Object... params) {//spread operator
        HttpServletRequest request = WebUtils.getCurrentRequest();
        Locale locale = localeResolver.resolveLocale(request);

        return messageSource.getMessage(messageKey, params, locale);
    }
}
