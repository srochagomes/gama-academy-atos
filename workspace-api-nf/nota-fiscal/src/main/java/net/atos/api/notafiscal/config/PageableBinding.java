package net.atos.api.notafiscal.config;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Parameters({
	@Parameter(name = "page", required = false, example = "123"),
	@Parameter(name = "size",  required = false, example = "123"),
	@Parameter(name = "dataInicio", required = false, example = "29-11-2021"),
	@Parameter(name = "dataFim", required = false, example = "02-12-2021")
        })
public @interface PageableBinding {

}
