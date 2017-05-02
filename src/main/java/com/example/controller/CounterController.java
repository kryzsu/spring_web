package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CounterController {

	@RequestMapping(method = RequestMethod.GET, path = "counter")
	public ModelAndView operation(
			@RequestParam(required = false) Integer counter,
			@RequestParam(required = false) String operation) {
		if (counter == null) {
			counter = 0;
		}

		ModelAndView mav = new ModelAndView();

		counter = applyOperation(counter, operation);

		mav.setViewName("counter");
		mav.addObject("counter", counter);
		return mav;
	}

	private Integer applyOperation(Integer counter, String operation) {

		Integer rv = counter;
		if (operation != null) {
			if (operation.equals("inc")) {
				rv = counter + 1;
			} else if (operation.equals("dec")) {
				rv = counter - 1;
			}
			if (operation.equals("zero")) {
				rv = 0;
			}
		}

		return rv;
	}


}
