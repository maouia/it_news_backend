package tn.projetdemo.demo.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@RequestMapping(method= RequestMethod.GET, value="/testController")
	public String testController() {
		return "Bonjour";
	}

}
