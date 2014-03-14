package com.lixiaodai.bookDinner.dao;

import java.util.List;

import com.lixiaodai.bookDinner.entity.User;
import com.lixiaodai.bookDinner.entity.extend.jtablePage.JTablePage;

public interface UserMapper {
	User getUser(int id);
	List<User> getAllUsers();
	Integer userAdd(User user);
	boolean userEdit(User user);
	boolean userDel(Integer id);
	List<User> findUsersByPage(JTablePage<User> jTablePage);
	/** 
	* @Title: findUserByUserName 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userName
	* @param @return    设定文件 
	* @return User    返回类型 
	* @throws 
	*/
	User findUserByUserName(String userName);
}
