package br.com.valhala.despesas.interfaces.web.convert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

public class LocalDateConvert implements Converter<String, LocalDate> {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDate convert(String source) {
        if(source != null && !source.isEmpty()) {
            return LocalDate.parse(source, DTF);
        }
        return null;
    }
    
}