package hu.bitbot.controller;

import hu.bitbot.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CounterController {

	@Autowired
	Counter counterObj;

	@RequestMapping(method = RequestMethod.GET,
	path = "szamlalo")
	public ModelAndView operation(
			@RequestParam(required = false) String operation) {
		if (operation == null) {
			operation = "zero";
		}
		int counter = counterObj.getValue();

		ModelAndView mav = new ModelAndView();

		if (operation.equals("inc")) {
			counter++;
		} else if (operation.equals("dec")) {
			counter--;
		} if (operation.equals("zero")) {
			counter = 0;
		}

		counterObj.setValue(counter);
		mav.setViewName("counter");
		mav.addObject("counter", counterObj);
		return mav;
	}
}
