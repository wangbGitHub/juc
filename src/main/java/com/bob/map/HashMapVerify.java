package com.bob.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangbo
 * @Date 2022/11/22 09:55
 * @Description: hashmap验证
 * @Version 1.0
 */
public class HashMapVerify {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>(10);
		map.put("1", "1");
		map.get("1");
		System.out.println("23 & 8 = " + (23 & 8));
		System.out.println("7 & 8 = " + (7 & 8));
		System.out.println("15 & 8 =" + (15 & 8));
		System.out.println("31 & 8 = "+(31 & 8));


		System.out.println("20 & 7 =" + (20 & 7 ));
		System.out.println("6 & 7 =" + (6 & 7 ));
		System.out.println("13 & 7 =" + (13 & 7 ));
		System.out.println("27 & 7 =" + (27 & 7 ));
	}
}
