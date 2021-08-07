package com.adidas.bff;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.util.Optional;

@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "com.adidas.bff.integration")
public class AdidasBffApplication {

    private static final String ACCESS_URLS_MESSAGE_LOG =
            "\n\n Access URLs:\n----------------------------------------------------------\n\tExternal:\thttp://{}:{}{}/graphql \n\tProfiles:\t{}\n----------------------------------------------------------\n";

    public static void main(final String[] args) {
        try {
            System.setProperty("spring.devtools.restart.enabled", "false");
            final SpringApplication app = new SpringApplication(AdidasBffApplication.class);
            final Environment env = app.run().getEnvironment();
            log.info(
                    ACCESS_URLS_MESSAGE_LOG,
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"),
                    Optional.ofNullable(env.getProperty("server.servlet.context-path")).orElse(""),
                    env.getActiveProfiles());
        } catch (Exception e) {
            log.error("Start Error.", e);
        }
    }
}
