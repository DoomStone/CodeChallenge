package com.tradeshfit.controllers;

import com.tradeshift.controllers.HomeController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.mock;

public class HomeControllerTest {

	@Test
	public void testRightViewIsCalled(){
		ModelAndView viewModel = new HomeController().index();
		Assert.assertEquals("home/index", viewModel.getViewName());
	}
}
