package com.jxnu.cic.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


/**
 * Google的GSON相关操作工具类
 * 数据转换
 * @author Teacher
 *
 */
public class GsonUtils {
	
	private static Gson gson = null;
    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtils() {
    }
    
	public static String createGsonString(Object object){
		Gson gson=new Gson();
		return gson.toJson(object);
	}
	
	public static <T> T getStringGsonToObject(String jsonStr,Class<T> clazz){
		//JSON字符串的判断
		if(StringUtils.isEmpty(jsonStr)||StringUtils.isBlank(jsonStr)) return null;
		Gson gson=new Gson();
		return (T) gson.fromJson(jsonStr, clazz);
	}	
	
	/**
	 * 转换为List
	 * 泛型在编译期类型被擦除导致报错
	 * @param jsonStr
	 * @param clazz
	 * @param T
	 * @return
	 */
	public static <T> List<T> getStringGsonToList(String jsonStr,Class<T> clazz){
		//JSON字符串的判断
		if(StringUtils.isEmpty(jsonStr)||StringUtils.isBlank(jsonStr)) return null;

		List<T> listObj=null;
				
		Gson gson=new Gson();
		Type typeTT=new TypeToken<List<T>>(){}.getType();
		listObj=gson.fromJson(jsonStr, typeTT);
		return listObj;
	}
	
	/**
	 * 转换为List
	 * 解决泛型问题
	 * @param jsonStr
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> fromJsonArrayToList(String jsonStr,Class<T> clazz){
		//JSON字符串的判断
		if(StringUtils.isEmpty(jsonStr)||StringUtils.isBlank(jsonStr)) return null;
		
		List<T> list=new ArrayList<T>();
		JsonArray array=new JsonParser().parse(jsonStr).getAsJsonArray();
		for(final JsonElement elem:array){
			list.add(new Gson().fromJson(elem, clazz));
		}
		return list;
	}
	
	public static <T> List<Map<String,T>> changeGsonToListMaps(String jsonStr){
		//JSON字符串的判断
		if(StringUtils.isEmpty(jsonStr)||StringUtils.isBlank(jsonStr)) return null;
		
		List<Map<String,T>> list=null;
		Gson gson=new Gson();
		list=gson.fromJson(jsonStr,new TypeToken<List<Map<String,T>>>(){}.getType());
		return list;	
	}
	
	/**
     * 转为Map
     * @param jsonStr
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String jsonStr) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(jsonStr, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
	
	public static <T> String ListObjectToGson(List<T> list){
		Gson gson=new Gson();
		return gson.toJson(list);
	}
			
	public static void main(String[] args){
		//手动测试
	}
}
