package br.com.valhala.despesas.infraestrutura.configuracao;

import br.com.valhala.despesas.interfaces.web.convert.LocalDateConvert;
import br.com.valhala.despesas.interfaces.web.format.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(dateFormatter());
        registry.addConverter(localDateConvert());
    }

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

    @Bean
    public LocalDateConvert localDateConvert() {
        return new LocalDateConvert();
    }

}
