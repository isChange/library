package com.igeek.library.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesUtil {


    private PropertiesUtil() {
    }

    private static final Properties PROPERTIES = readProperties("alipay.properties");

    private static Properties readProperties(String... confFile) {
        final Properties properties = new Properties();
        try {
            for (String path : confFile) {
                final ClassPathResource resource = new ClassPathResource(path);
                properties.load(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
        return properties;
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
