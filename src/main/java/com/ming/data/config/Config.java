/**
 * Config.java
 * <p>
 */

package com.ming.data.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 *
 *
 */
public class Config {
    private static final Logger LOG = Logger.getLogger(Config.class);

    public static final Properties configProperties = new Properties();
    public static String path=Thread.currentThread().getContextClassLoader().getResource("").getPath() + "config.properties" ;

    public static void main(String[] args) throws ConfigurationException {
        init();
    }

	public static String freezeResult;
    
    public static void init() {
        try {
            configProperties.load(new FileInputStream(new File("src/main/resources/config.properties")));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            System.exit(1);
        }

		freezeResult = configProperties.getProperty("test", "creditline_wedefend_freeze_rule_resp");
        //命令行参数
        System.out.println();
        System.out.println(freezeResult);

    }



    public static void print() {
        Field[] fields = Config.class.getFields();
        for (Field field : fields) {
            if (field.getType().isPrimitive() || field.getType().isArray() || String.class.isAssignableFrom(field.getType()) || Number.class.isAssignableFrom(field.getType())) {
                try {
                    LOG.info(field.getName() + " = " + field.get(Config.class));
                } catch (Exception e) {
                    LOG.error("print field error", e);
                }
            }
        }
    }
}
