package com.hof.wovenyautoproductentry.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "woveny.etsy.pillow")
public class EtsyPillowAuthenticationProperties extends BaseAuthenticationProperties {
}
