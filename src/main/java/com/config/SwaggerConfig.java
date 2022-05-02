package com.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.TreeMap;


@Configuration
public class SwaggerConfig {


    private final String title = "Petshop Service API";
    private final String version = "1.0";
    private final String globalHeader = "ConfigToken";
    private final String group = "v1";


    //Customizing:
    //- API Info
    //- Adding Header 'BotConfigToken' globally
    //- Set group name '/api-docs/v1'
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
		.group(group)
                .packagesToScan("com.controller")
                .addOpenApiCustomiser(setInfo())
                .addOperationCustomizer(globalHeaderAdder())
                .build();
    }

    private final String description = "This ia a PET SHOP";


    private OperationCustomizer globalHeaderAdder() {
        return (operation, handlerMethod) -> {
            Parameter customHeader = new Parameter().in(ParameterIn.HEADER.toString())
                    .name(globalHeader).schema(new StringSchema()).required(true);
            operation.addParametersItem(customHeader);
            return operation;
        };
    }

    private OpenApiCustomiser setInfo() {
        return openApi -> {
            Info info = new Info();
            info.setDescription(description);
            info.setTitle(title);
            info.setVersion(version);
            //add info
            openApi.info(info);
            //sort content
            Map<String, Schema> schemas = openApi.getComponents().getSchemas();
            Assert.notNull(schemas, "Schemas not found");
            openApi.getComponents().setSchemas(new TreeMap<>(schemas));
        };
    }
}
