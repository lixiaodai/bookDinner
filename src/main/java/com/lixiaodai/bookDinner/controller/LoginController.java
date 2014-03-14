/**   
* @Title: LoginController.java 
* @Package com.lixiaodai.bookDinner.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  lixiaodai
* @date 2014-3-14 上午10:58:15 
* @version V1.0   
*/
package com.lixiaodai.bookDinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lixiaodai.bookDinner.entity.User;
import com.lixiaodai.bookDinner.service.UserService;
import com.lixiaodai.bookDinner.util.AssertUtil;
import com.lixiaodai.bookDinner.util.Encryption;

/** 
 * @ClassName: LoginController 
 * @Description: 用户登录类 
 * @author lijie
 * @date 2014-3-14 上午10:58:15 
 *  
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user")
	public ModelAndView login(@RequestParam("userName")String userName,@RequestParam("passWord")String passWord,ModelMap modelMap){
		String message = null;
		if(AssertUtil.isNull(userName) || AssertUtil.isNull(passWord)){
			message="登录失败,登录名或密码为空!";
			modelMap.put("flag", false);
			modelMap.put("message", message);
			return new ModelAndView("success", modelMap);
		}
		User user = userService.findUserByUserName(userName);
		if(AssertUtil.isNull(user)){
			message="登录失败,用户不存在!";
			modelMap.put("flag", false);
			modelMap.put("message", message);
			return new ModelAndView("success", modelMap);
		}
		if(!Encryption.encryptToMD5(passWord).equals(user.getPassWord())){
			message="登录失败,密码错误!";
			modelMap.put("flag", false);
			modelMap.put("message", message);
			return new ModelAndView("success", modelMap);
		}
		message="登录成功!";
		modelMap.put("message", message);
		modelMap.put("flag", true);
		modelMap.put("testValue", "testValue");
		return new ModelAndView("success", modelMap);
	}
}
