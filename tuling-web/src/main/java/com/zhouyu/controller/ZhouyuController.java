package com.zhouyu.controller;

import com.zhouyu.service.ZhouyuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZhouyuController {

	@Autowired
	private ZhouyuService zhouyuService;

	@GetMapping("/test")
	public String test() {
		return zhouyuService.test();
	}

}
