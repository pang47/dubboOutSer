package com.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class CommonPageAction {
	@RequestMapping("currPage")
	public String toPage(String str){
		return str;
	}
}
