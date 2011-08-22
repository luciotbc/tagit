package com.luciotbc.tagit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

	public static boolean isEmail(String email){
	    Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");  
	    Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
	
	public static boolean isYoutubeLink(String link){
		if(link == null) return false;
		Pattern pattern = Pattern.compile("http.*\\?v=([a-zA-Z0-9_\\-]+)(?:&.)*");
		Matcher matcher = pattern.matcher(link);
		if(getYouTubeId(link).length() != 11) return false;
		return matcher.find();
	}
	
	public static String getYouTubeId(String link){
//		Pattern p = Pattern.compile("http.*\\?v=([a-zA-Z0-9_\\-]+)(?:&.)*");
//		Matcher m = p.matcher(link);
//		if (m.matches()) {
//			link = m.group(1);
//		}
		link = link.substring(link.indexOf("v=") + 2,link.indexOf("v=")+13);
		return link;
	}
}
