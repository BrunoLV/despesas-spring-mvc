package br.com.valhala.despesas.interfaces.web.format;

import org.springframework.format.Formatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter implements Formatter<LocalDate> {

    private DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DTF);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DTF.format(object);
    }
}
