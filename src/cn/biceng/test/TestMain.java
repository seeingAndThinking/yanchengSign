package cn.biceng.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain {
	
	public static void main(String[] args) {
//		TestSign sign = new TestSign();
//		try {
//			sign.testStampWithKeyWord();
//		} catch (Exception e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
		String value="2022/11/09 16:34:20";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		try {//
			System.out.println(dateFormat1.parse(value));
			System.out.println(dateFormat.format(dateFormat1.parse(value)));
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println(dateFormat.format(new Date(System.currentTimeMillis())));
				
		
		
	}
}
