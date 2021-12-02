package net.atos.api.notafiscal.config;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",defaultValue = "0", value = "Results page you want to retrieve (0..N)", required = false, example = "123"),
        @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",defaultValue = "0", value = "Number of records per page.", required = false, example = "123")})
public @interface PageableBinding {

}
