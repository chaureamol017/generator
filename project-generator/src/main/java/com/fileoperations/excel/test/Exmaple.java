package com.fileoperations.excel.test;

import java.io.*;
import java.net.*;
import java.util.*;

public class Exmaple {

	public static void main(String[] args) {
		grtArticleTitlesMain();
//		commonSubstringMain();
//		mergeArraysMain();
	}

	
	private static void grtArticleTitlesMain() {

		List<String> titles = grtArticleTitles("Akbarnama");
	}

	private static List<String> grtArticleTitles(String author) {
		List<String> titles = new ArrayList<>();
		try {
	        String spec = "https://jsonmock.hackerrank.com/api/articles?autor=" + author +"&page=1";
			URL url = new URL(spec );
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");

//	        if (conn.getResponseCode() != 200) {
//	            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//	        }

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	            (conn.getInputStream())));

	        String output = "";
	        String line;
	        System.out.println("Output from Server .... \n");
			while ((line = br.readLine()) != null) {
	            output += line;
	        }
	        conn.disconnect();
	        System.out.println(output);
	        while (output.indexOf("title") != -1) {
	        	String a = output.substring(output.indexOf("title") + 8, output.indexOf("url") - 3);
	        	
	        	output = output.substring(output.indexOf("created_at") + 12);
		        System.out.println(a);
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }

		return titles;
	}


	private static void commonSubstringMain() {
		List<String> a1 = new ArrayList<>(Arrays.asList(new String[] {"hello", "hi"}));
		List<String> b1 = new ArrayList<>(Arrays.asList(new String[] {"world", "bye"}));
		commonSubstring(a1, b1);
		System.out.println("------------------------" );
		a1 = new ArrayList<>(Arrays.asList(new String[] {"ab", "cd", "ef"}));
		b1 = new ArrayList<>(Arrays.asList(new String[]{"af", "ee", "ef"}));
		commonSubstring(a1, b1);
		
	}

	private static void commonSubstring(List<String> a, List<String> b) {
		Boolean[] outputs = new Boolean[a.size()];
		for (int i = 0; i < a.size(); i++) {
			for (char c : b.get(i).toCharArray()) {
				if (a.get(i).indexOf(c) != -1) {
					outputs[i] = true;
				}
			}
		}
		for (Boolean output : outputs) {
			System.out.println((output != null && output) ? "YES" : "NO" );
		}
	}


	private static void mergeArraysMain() {
		int[] arr1 = {-1, 1, 3, 5, 7, 9};
		int[] arr2 = {-2, 2, 3, 4, 5, 6};

		int[] response = mergeArrays(arr1, arr2);
		for (int num: response) {
			System.out.println(num);
		}
	}

	public static int[] mergeArrays(int[] arr1, int[] arr2) {
		int length = arr1.length + arr2.length;
		
		int[] response = new int[length];
		int index = 0;
		for (int num: arr1) {
			response[index] = num;
			index++;
		}
		for (int num: arr2) {
			response[index] = num;
			index++;
		}
		
		Arrays.sort(response);
		return response;
	}
	
//{"page":1,"per_page":10,"total":41,"total_pages":5,"data":[{"title":"A Message to Our Customers","url":"http://www.apple.com/customer-letter/","author":"epaga","num_comments":967,"story_id":null,"story_title":null,"story_url":null,"parent_id":null,"created_at":1455698317},{"title":"“Was isolated from 1999 to 2006 with a 486. Built my own late 80s OS”","url":"http://imgur.com/gallery/hRf2trV","author":"epaga","num_comments":265,"story_id":null,"story_title":null,"story_url":null,"parent_id":null,"created_at":1418517626},{"title":"Apple’s declining software quality","url":"http://sudophilosophical.com/2016/02/04/apples-declining-software-quality/","author":"epaga","num_comments":705,"story_id":null,"story_title":null,"story_url":null,"parent_id":null,"created_at":1454596037},{"title":null,"url":null,"author":"patricktomas","num_comments":376,"story_id":null,"story_title":"Steve Jobs has passed away.","story_url":"http://www.apple.com/stevejobs/","parent_id":null,"created_at":1317858143},{"title":"Google Is Eating Our Mail","url":"https://www.tablix.org/~avian/blog/archives/2019/04/google_is_eating_our_mail/","author":"saintamh","num_comments":685,"story_id":null,"story_title":null,"story_url":null,"parent_id":null,"created_at":1556274921},{"title":"Why I’m Suing the US Government","url":"https://www.bunniestudios.com/blog/?p=4782","author":"saintamh","num_comments":305,"story_id":null,"story_title":null,"story_url":null,"parent_id":null,"created_at":1469106658},{"title":"F.C.C. Repeals Net Neutrality Rules","url":"https://www.nytimes.com/2017/12/14/technology/net-neutrality-repeal-vote.html","author":"panny","num_comments":1397,"story_id":null,"story_title":null,"story_url":null,"parent_id":null,"created_at":1513275215},{"title":"Show HN: This up votes itself","url":"http://news.ycombinator.com/vote?for=3742902&dir=up&whence=%6e%65%77%65%73%74","author":"olalonde","num_comments":83,"story_id":null,"story_title":null,"story_url":null,"parent_id":null,"created_at":1332463239},{"title":null,"url":null,"author":"olalonde","num_comments":null,"story_id":null,"story_title":"Guacamole – A clientless remote desktop gateway","story_url":"https://guacamole.incubator.apache.org/","parent_id":6547669,"created_at":1381763543},{"title":null,"url":null,"author":"WisNorCan","num_comments":981,"story_id":null,"story_title":"Switch from Chrome to Firefox","story_url":"https://www.mozilla.org/en-US/firefox/switch/","parent_id":null,"created_at":1559232559}]}
}
