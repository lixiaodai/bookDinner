package com.lixiaodai.bookDinner.util;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ReflectUtil {

	/**
	 * 
	* @Title: getFieldValue 
	* @Description: 从某个对象中，得到该对象的某个属性值
	* @param object 对象
	* @param fieldName 属性名称
	* @return
	* @auther lijie
	 */
	public static Object getFieldValue(Object object,String fieldName){
		Object result = null;
		Field field = ReflectUtil.getField(object,fieldName);
		if(field!=null){
			field.setAccessible(true);
			try {
				result = field.get(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	* @Title: getField 
	* @Description: 根据某个对象得到属性
	* @param object 对象
	* @param fieldName 属性名
	* @return
	* @auther lijie
	 */
	public static Field getField(Object object, String fieldName) {
		Field field = null;
		for(Class<?> class1 = object.getClass();class1!=Object.class;class1=class1.getSuperclass()){
			try {
				field = class1.getDeclaredField(fieldName);
				break;
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				
			}
		}
		return field;
	}
	
	public static void setFieldValue(Object object,String fieldName,String fieldValue){
		   Field field = ReflectUtil.getField(object, fieldName);  
           if (field != null) {  
              try {  
                  field.setAccessible(true);  
                  field.set(object, fieldValue);  
              } catch (IllegalArgumentException e) {  
                  e.printStackTrace();  
              } catch (IllegalAccessException e) {  
                  e.printStackTrace();  
              }  
           }  
	}
}
