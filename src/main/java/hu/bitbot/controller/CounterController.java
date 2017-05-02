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

	Counter counterObj;

	@Autowired
	public CounterController(Counter counterObj) {
		this.counterObj = counterObj;
	}

	@RequestMapping(method = RequestMethod.GET, path = "counter")
	public ModelAndView operation(@RequestParam(required = false) String operation) {

		// get from model
		Integer counter = counterObj.getValue();

		ModelAndView mav = new ModelAndView();

		counter = applyOperation(counter, operation);

		//save to model
		counterObj.setValue(counter);

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
