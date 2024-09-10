package com.evcharger.architecture.config;


import org.modelmapper.Conditions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull())
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

//        modelMapper.typeMap(Location.class, LocationDTO.class)
//                .addMappings(mapper -> mapper.skip(LocationDTO::setOperatingHours));

        return modelMapper;
    }
}
