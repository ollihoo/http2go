package de.ollihoo.httptogo.hello

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

@RestController
class HelloRestController {

  @RequestMapping(value="/hello", method = RequestMethod.GET)
  HelloData index(HttpServletRequest request) {

    new HelloData(
        name: "Test",
        isSecure: request.isSecure()
    )

  }

}

class HelloData {
  String name
  boolean isSecure
}
