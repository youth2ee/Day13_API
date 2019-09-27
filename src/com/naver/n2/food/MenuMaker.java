package com.naver.n2.food;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class MenuMaker {

	private ArrayList<String> lunchs;
	private ArrayList<String> dinners;

	public String selectMenu(String select) {
		Random random = new Random();
		String menu=null;

		if(select.equals("1")) {
			menu=lunchs.get(random.nextInt(lunchs.size()));
		} else if (select.equals("2")) {
			menu=dinners.get(random.nextInt(dinners.size()));
		}

		return menu;
	}

	
	public void init() {
		this.lunchs =	this.makeMenu("lunch.txt", "-");
		this.dinners = this.makeMenu("dinner.txt", ",");
	}

	
	private ArrayList<String> makeMenu(String fileName, String delim) {
		File file = new File("c:\\test", fileName);
		ArrayList<String> ar = new ArrayList<String>();

		boolean check = true;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while(check) {
				String str = br.readLine();
				if(str==null) {
					break;
				}

				StringTokenizer st = new StringTokenizer(str, delim);
				while(st.hasMoreTokens()) {
					ar.add(st.nextToken());		
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		} 

		return ar;
	}

}
