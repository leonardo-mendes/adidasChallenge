package com.adidas.bff.graphql.scalar;

import graphql.language.StringValue;
import graphql.schema.*;

import java.time.LocalDate;

public class GraphQLLocalDate extends GraphQLScalarType {

    public GraphQLLocalDate() {
        super("LocalDate", "Local Date Type", new Coercing<LocalDate, String>() {
            @Override
            public String serialize(Object input) throws CoercingSerializeException {
                if(input instanceof LocalDate) return ((LocalDate) input).toString();
                return null;
            }

            @Override
            public LocalDate parseValue(Object input) throws CoercingParseValueException {
                if(input instanceof String) return LocalDate.parse((String) input);
                return null;
            }

            @Override
            public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
                if(!(input instanceof StringValue)) return null;
                return LocalDate.parse(((StringValue) input).getValue());
            }
        });
    }

}
