package org.nnn4eu.nfische.springtest.config;

import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class BypassCommaDelimiterConfiguration  {
    public static Converter<String, List<String>> commaDelimiterBypassedParsingConverter() {
        return new Converter<String, List<String>>() {
            @Override
            public List<String> convert(final String source) {
                final List<String> classes = new ArrayList<String>();
                classes.add(source);
                return classes;
            }
        };
    }
}