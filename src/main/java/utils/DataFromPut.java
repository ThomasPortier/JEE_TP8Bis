package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataFromPut {
	public static Map<String, String> getParameterMap(BufferedReader br) throws IOException {

		Map<String, String> dataMap = new HashMap<>();

		String data = br.readLine();

		for (String couple1Index : data.split("&")) {
			dataMap.put(couple1Index.split("=")[0], couple1Index.split("=")[1]);
		}

	//	for (String num : dataMap.keySet()) {
	//		System.out.println("Key : " + num + " Values:" + dataMap.get(num));
	//	}

		return dataMap;

	}

}
/*
 * Splitter.on('&').trimResults().withKeyValueSeparator(Splitter.on('=').limit(3
 * ).trimResults()) .split(data);
 */