package com.versuchdrei.datamanager.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * a util class for methods regarding strings
 * @author VersuchDrei
 * @version 1.0
 */
public class StringUtils {

	public static List<String> stringToList(final String string){
		try {
			final JSONParser parser = new JSONParser();
			final JSONArray array = (JSONArray) parser.parse(string);
			return Arrays.stream(array.toArray()).map(object -> object.toString()).collect(Collectors.toList());
		} catch (final ParseException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public static String listToString(final List<String> list) {
		return JSONArray.toJSONString(list);
	}
}
