package com.adidas.bff.config;

import graphql.kickstart.execution.GraphQLObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLObjectMapper createJacksonDecoders() {
        return GraphQLObjectMapper.newBuilder()
                .withObjectMapperConfigurer(mapper -> mapper.setSerializationInclusion(ALWAYS))
                .build();
    }
}
