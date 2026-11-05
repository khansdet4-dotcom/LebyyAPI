package com.generic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileLib {

	    private static final Properties props = new Properties();

	    static {
	        try (InputStream is = FileLib.class
	                .getClassLoader()
	                .getResourceAsStream("config.properties")) {
	            if (is == null) {
	                throw new RuntimeException("config.properties file nahi mili classpath me");
	            }
	            props.load(is);
	        } catch (IOException e) {
	            throw new RuntimeException("config.properties load nahi hui", e);
	        }
	    }

	    public static String get(String key) {
	        return props.getProperty(key);
	    }
	}
