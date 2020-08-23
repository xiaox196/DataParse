/**
 * ConfigurationManager.java
 * <p>
 */
package com.ming.data.config;

import com.ming.data.utils.StringUtil;
import org.apache.commons.configuration.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigurationManager {

    public static final String CONFIG_FILE = "configFile";
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationManager.class);

    private static boolean throwExceptionOnMissing = true;
    private static ConfigurationManager configurationManager;
    private CompositeConfiguration config = new CompositeConfiguration();
    private List<String> propertiesFiles = new ArrayList<>();

    private ConfigurationManager(String... files) {
        LOG.info("Start to initialize configuration");
        addPropertyFiles(files);
        config();
        LOG.info("Initialize configuration success");

        config.getKeys().forEachRemaining(key -> {
            if (key.startsWith("cfg")) {
                System.setProperty(key, config.getString(key));
            }
            LOG.debug("{} = {}", key, config.getString(key));
        });

    }

    public static void init(String... files) {
        LOG.info("files:" + StringUtils.join(files, ','));
        configurationManager = new ConfigurationManager(files);
    }

    public static void init(CompositeConfiguration config) {
        if (configurationManager == null && config != null) {
            configurationManager = new ConfigurationManager();
            configurationManager.config = config;
            LOG.info("Initialize configuration success");
        }
    }

    public static CompositeConfiguration getConfig() {
        String defaultConfigFile = "config.properties";
        if (configurationManager == null) {
            try {
                PropertiesConfiguration springBootProfile = new PropertiesConfiguration(ConfigurationManager.class.getClassLoader().getResource("application.properties"));

                // 优先获取Java -jar -D参数的spring.profiles.active变量（用于Docker部署），没有就选application.properties变量值（用于本地调试）
                String envConfigFileSuffix = System.getProperty("spring.profiles.active");
                envConfigFileSuffix = StringUtils.isNotEmpty(envConfigFileSuffix) ? envConfigFileSuffix : springBootProfile.getString("spring.profiles.active");

                if (StringUtils.isNotEmpty(envConfigFileSuffix)) {
                    defaultConfigFile = String.format("config-%s.properties", envConfigFileSuffix);
                }
            } catch (ConfigurationException e) {
                // can be ignored
            }
            // 优先从环境变量里取路径
            String configFile = System.getProperty(CONFIG_FILE);
            if (StringUtil.isEmpty(configFile) || "null".equals(configFile)) {
                /**
                 * 由于 flink 指定配置文件路径的参数传值失败，在这里写死先。
                 * 如果启动参数里没有配置文件路径，找一下这个文件是否存在。
                 * 如果存在，就读这个文件。
                 */
                if (Files.exists(Paths.get("/mnt/wdf/pangu-app-antifraud-flink-job/config.properties"))) {
                    configFile = "/mnt/wdf/pangu-app-antifraud-flink-job/config.properties";
                } else {
                    configFile = defaultConfigFile;
                }

            }

            LOG.info("configFile: " + configFile);
            configurationManager = new ConfigurationManager(configFile);
        }
        return configurationManager.config;
    }

    private Configuration config() {
        try {
            AbstractConfiguration.setDefaultListDelimiter(';');
            config.addConfiguration(new SystemConfiguration());
            for (String file : propertiesFiles) {
                if (new File(file).exists()) {
                    config.addConfiguration(new PropertiesConfiguration(file));
                } else {
                    config.addConfiguration(new PropertiesConfiguration(this.getClass().getClassLoader().getResource(file)));
                }
            }
            config.setThrowExceptionOnMissing(throwExceptionOnMissing);
        } catch (ConfigurationException e) {
            LOG.error("Couldn't find config properties");
        }
        return config;
    }

    private void addPropertyFiles(String... files) {
        propertiesFiles.addAll(Arrays.asList(files));
    }
}
