package com.skitskurr.datamanager.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

	public static List<String> stringToList(final String string){
		return Arrays.stream(string.split("\\;;\\")).map(element -> element.replace("\\;", ";")).collect(Collectors.toList());
	}
	
	public static String listToString(final List<String> list) {
		return list.stream().map(string -> string.replace(";", "\\;")).collect(Collectors.joining("\\;;\\"));
	}
}
