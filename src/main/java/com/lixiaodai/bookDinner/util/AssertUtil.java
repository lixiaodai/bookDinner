/**   
* @Title: AssertUtil.java 
* @Package com.lixiaodai.bookDinner.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  lixiaodai
* @date 2014-3-14 上午11:37:06 
* @version V1.0   
*/
package com.lixiaodai.bookDinner.util;

import com.lixiaodai.bookDinner.entity.User;

/** 
 * @ClassName: AssertUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author lijie
 * @date 2014-3-14 上午11:37:06 
 *  
 */
public class AssertUtil {

	/** 
	* @Title: isNull 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userName
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	*/
	public static boolean isNull(Object obj) {
		if(obj instanceof String){
			String assertStr = (String)obj;
			if(assertStr==null||assertStr==""){
				return true;
			}
		}else{
			if(obj == null){
				return true;
			}
		}
		return false;
	}


}
