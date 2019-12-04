package br.com.central.erros.impl.config;

import java.util.*;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /* SUCESSFULL 2xx
     * GET 200   OK
     * POST 201  CREATED
     * POST 202  ACCEPTED
     * POST 204 NO CONTENT
     * DEL  204 NO CONTENT
     *
     */
    private final ResponseMessage m200get = simpleMessage(200, "OK");
    private final ResponseMessage m201post = simpleMessage(201, "CREATED");
    private final ResponseMessage m202post = simpleMessage(202, "ACCEPTED");
    private final ResponseMessage m204post = simpleMessage(204, "NO CONTENT");
    private final ResponseMessage m204put = simpleMessage(204, "NO CONTENT");
    private final ResponseMessage m204del = simpleMessage(204, "NO CONTENT");

    /* REDIRECT 3xx
     * GET 301 MOVED PERMANENTLY
     * GET 302 FOUND
     *
     */
    private final ResponseMessage m301get = simpleMessage(301, "MOVED PERMANENTLY");
    private final ResponseMessage m302get = simpleMessage(302, "FOUND");

    /* CLIENT ERROR 4xx
     * POST 400 BAD REQUEST
     * GET  401 UNAUTHORIZED
     * GET  403 FORBIDEN
     * PUT  403 FORBIDEN
     * DELL 403 FORBIDEN
     * GET  404 NOT FOUND
     * DELL 404 NOT FOUND
     * PUT  415 UNSUPPORTED MEDIA TYPE
     * POST 422 BUSSINESS ERROR
     * PUT  422 BUSSINESS ERROR
     *
     */
    private final ResponseMessage m400post = simpleMessage(400, "BAD REQUEST");
    private final ResponseMessage m401get = simpleMessage(401, "UNAUTHORIZED");
    private final ResponseMessage m403get = simpleMessage(403, "FORBIDEN");
    private final ResponseMessage m403put = simpleMessage(403, "FORBIDEN");
    private final ResponseMessage m403del = simpleMessage(403, "FORBIDEN");
    private final ResponseMessage m404get = simpleMessage(404, "NOT FOUND");
    private final ResponseMessage m404del = simpleMessage(404, "NOT FOUND");
    private final ResponseMessage m415put = simpleMessage(415, "UNSUPPORTED MEDIA TYPE");
    private final ResponseMessage m422post = simpleMessage(422, "BUSSINESS ERROR");
    private final ResponseMessage m422put = simpleMessage(422, "BUSSINESS ERROR");

    /* SERVER ERROR 5xx
     * POST 500 INTERNAL SERVER ERROR
     * DELL 500 INTERNAL SERVER ERROR
     * POST 502 BAD REQUEST
     * GET  503 SERVICE UNAVAILLABLE
     * GET  504 GATEWAY TIMEOUT
     *
     */
    private final ResponseMessage m500post = simpleMessage(500, "INTERNAL SERVER ERROR");
    private final ResponseMessage m500del = simpleMessage(500, "INTERNAL SERVER ERROR");
    private final ResponseMessage m500put = simpleMessage(500, "INTERNAL SERVER ERROR");
    private final ResponseMessage m502post = simpleMessage(502, "BAD REQUEST");
    private final ResponseMessage m503get = simpleMessage(503, "SERVICE UNAVAILLABLE");
    private final ResponseMessage m505get = simpleMessage(504, "GATEWAY TIMEOUT");


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)

                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m200get, m301get, m302get, m401get, m403get, m404get, m503get, m505get))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m200get, m301get, m302get, m401get, m403get, m404get, m503get, m505get))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403put, m415put, m422put, m500put))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403del, m404del, m500del))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.central.erros.impl.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }



    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Central de Erros",
                "API utilizada para o gerenciamento e os registros de erros",
                "Versão 1.0.0-SNAPSHOT",
                "",
                new Contact("Squad 3", "", ""),
                "Permitido somente uso interno",
                "https://www.codenation.com.br",
                Collections.emptyList() // Vendor Extensions
        );
    }

    private ResponseMessage customMessage201() {
        Map<String, Header> map = new HashMap<>();
        map.put("version", new Header("version", "api-version: 2019-10-30", new ModelRef("string")));
        return new ResponseMessageBuilder()
                .code(201)
                .message("Created")
                .headersWithDescription(map)
                .build();
    }

    private ResponseMessage customMessage200() {
        Map<String, Header> map = new HashMap<>();
        map.put("version", new Header("version", "api-version: 2019-10-30", new ModelRef("string")));
        return new ResponseMessageBuilder()
                .code(200)
                .message("Created")
                .headersWithDescription(map)
                .build();
    }


    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }


}