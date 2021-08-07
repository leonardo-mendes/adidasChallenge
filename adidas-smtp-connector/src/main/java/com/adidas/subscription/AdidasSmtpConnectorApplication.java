package com.adidas.subscription;

import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class AdidasSmtpConnectorApplication {

    private static final String ACCESS_URLS_MESSAGE_LOG =
            "\n\n Access URLs:\n----------------------------------------------------------\n\t External: \thttp://{}:{}/swagger-ui.html Profiles: {}\n----------------------------------------------------------\n";

    public static void main(final String[] args) {
        try {
            System.setProperty("spring.devtools.restart.enabled", "false");
            final SpringApplication app =
                    new SpringApplication(AdidasSmtpConnectorApplication.class);
            final Environment env = app.run().getEnvironment();
            log.info(
                    ACCESS_URLS_MESSAGE_LOG,
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"),
                    env.getActiveProfiles());
        } catch (Exception e) {
            log.error("Start Error.", e);
        }
    }
}
