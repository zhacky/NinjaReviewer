package com.zhacky.app.examreviewer.utils;

public class UriHelper {

	public static String getUrl(int groupPos,int childPos, int selection){
		String url = "file:///android_asset/lessons/404.html";
		String urlx = "file:///android_asset/lessons/group" + String.valueOf(groupPos + 1) +
				      "/child" + String.valueOf(childPos + 1) +
				      "/";
		switch (selection) {
		case 1:
			url = urlx + "a.html";
			break;

		case 2:
			url = urlx + "b.html";
			
			break;
			
		case 3:
			url = urlx + "c.html";
			
			break;
		default:
			url = "file:///android_asset/lessons/404.html";
			break;
		}
		
		
		return url;
		
	}
}
