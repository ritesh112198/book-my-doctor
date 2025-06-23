package com.spring.bookMyDoctorApp.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class ModelMapConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper=new  ModelMapper();
        modelMapper.addConverter(new AbstractConverter<String, LocalDate>(){
            @Override
            protected LocalDate convert(String source){
                return LocalDate.parse(source);
            }
        });
        return  new ModelMapper();
    }
}
