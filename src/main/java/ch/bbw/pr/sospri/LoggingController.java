package ch.bbw.pr.sospri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//bbwpr @RestController
@Controller
public class LoggingController {

    Logger logger = LoggerFactory.getLogger(LoggingController.class);
/* bbwpr
    @RequestMapping("/")
    public String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }
*/
    @GetMapping("/login")
    String login() {
        System.out.println("LoginController:login()");
        return "login";
    }

}
