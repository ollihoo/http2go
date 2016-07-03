package de.ollihoo.httptogo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HelloController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  String index() {
    "welcome"
  }
}
