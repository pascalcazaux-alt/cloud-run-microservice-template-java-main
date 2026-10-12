package com.cloudrun.microservicetemplate;

import java.util.Map;
import com.cloudrun.microservicetemplate.util.Response;
import com.cloudrun.microservicetemplate.util.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/** Example REST controller to demonstrate structured logging. */
@RestController
public class MicroserviceController {
  private static final Logger logger = LoggerFactory.getLogger(MicroserviceController.class);

  /** Example endpoint handler. */
  @GetMapping("/GetCustomAPI")
  public Map<String, String> index() {
    MDC.put("logField", "custom-entry");
    MDC.put("arbitraryField", "custom-entry");
    logger.info("Structured logging example.");

    return Map.of("message", "Hello World!");
  }

  @PostMapping("/PostCustomAPI")
  public ResponseEntity <Response> postCustomAPI(@RequestBody Parameter parameter) {
    String Data1 = parameter.getData1();
    String Data2 = parameter.getData2();

    Response response = new Response();
    response.message = Data1 + "_" + Data2;

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
  }
}
