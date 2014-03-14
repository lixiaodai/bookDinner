/**   
* @Title: UserServiceImpl.java 
* @Package com.lixiaodai.bookDinner.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  lixiaodai
* @date 2014-3-14 上午11:15:54 
* @version V1.0   
*/
package com.lixiaodai.bookDinner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lixiaodai.bookDinner.dao.UserMapper;
import com.lixiaodai.bookDinner.entity.User;
import com.lixiaodai.bookDinner.entity.extend.jtablePage.JTablePage;
import com.lixiaodai.bookDinner.service.UserService;

/** 
 * @ClassName: UserServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lijie
 * @date 2014-3-14 上午11:15:54 
 *  
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	public List<User> userAllList(JTablePage<User> jTablePage) {
		return userMapper.getAllUsers();
	}

	public User addUser(User user) {
		Integer id = userMapper.userAdd(user);
		user.setId(id);
		return user;
	}

	public boolean delUser(User user) {
		return userMapper.userDel(user.getId());
	}

	public boolean editUser(User user) {
		return userMapper.userEdit(user);
	}

	public List<User> findUsersByPage(JTablePage<User> jTablePage) {
		return userMapper.findUsersByPage(jTablePage);
	}

	/* (non-Javadoc)
	 * @see com.lixiaodai.bookDinner.service.UserService#findUserByUserName(java.lang.String)
	 */
	@Override
	public User findUserByUserName(String userName) {
		return userMapper.findUserByUserName(userName);
	}

}
