package dev.thesarfo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"dev.thesarfo.service", "dev.thesarfo.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfig {

}
