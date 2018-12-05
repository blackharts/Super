/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supertec.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alfon
 */
@Controller	
public class IndexController {
    @RequestMapping("/")
    public String showIndex() {
    	return "index";
    }
	
}
