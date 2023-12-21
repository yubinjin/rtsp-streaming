package ontechsystem.hbcha.spring.project.sample.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {
    @RequestMapping(value = "/{viewName}")
    public String returnJsp(HttpServletRequest request, @PathVariable String viewName){
        return viewName;
    }
}
