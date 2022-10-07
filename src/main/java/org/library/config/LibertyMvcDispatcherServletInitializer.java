package org.library.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class LibertyMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // подключения Спринг конфиг
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    // Подключение перехвата сервлет (Все http запросы от пользователя мы посылаем на диспетчер сервлет
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
