/**   
* @Title: UserService.java 
* @Package com.lixiaodai.bookDinner.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  lixiaodai
* @date 2014-3-14 上午11:14:11 
* @version V1.0   
*/
package com.lixiaodai.bookDinner.service;

import java.util.List;

import com.lixiaodai.bookDinner.entity.User;
import com.lixiaodai.bookDinner.entity.extend.jtablePage.JTablePage;

/** 
 * @ClassName: UserService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lijie
 * @date 2014-3-14 上午11:14:11 
 *  
 */
public interface UserService {
	public List<User> userAllList(JTablePage<User> jTablePage);

	public User addUser(User user) ;

	public boolean delUser(User user) ;

	public boolean editUser(User user) ;

	public List<User> findUsersByPage(JTablePage<User> jTablePage) ;

	/** 
	* @Title: findUserByUserName 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userName
	* @param @return    设定文件 
	* @return User    返回类型 
	* @throws 
	*/
	public User findUserByUserName(String userName);

}
