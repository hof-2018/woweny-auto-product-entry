package com.hof.wovenyautoproductentry.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "woveny.chairish.pillow")
public class ChairishPillowAuthenticationProperties extends BaseAuthenticationProperties {
}
