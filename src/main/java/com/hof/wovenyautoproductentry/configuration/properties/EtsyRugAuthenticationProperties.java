package com.hof.wovenyautoproductentry.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "woveny.etsy.rug")
public class EtsyRugAuthenticationProperties extends BaseAuthenticationProperties {
}
