package com.naver.n2;

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

import com.naver.n2.food.MenuMaker;
import com.naver.n2.network.Network;

public class Server2 {

	public static void main(String[] args) {
		// 1번 받으면 점심중 골라 주고
		// 2번 받으면 저녁중 골라준다.

		MenuMaker mm = new MenuMaker();
		mm.init(); //파싱작업 해서 모으기

		Network network = new Network();
		

		try {
			//연결
			ServerSocket ss = new ServerSocket(8282);
			Socket sc = ss.accept();

			//클라이언트에게 받기
			String select = network.receive(sc);

			//받은 것을  MenuMaker클래스에서 해결해서 다시받기
			String menu = mm.selectMenu(select);
			
			//클라이언트에게 보내기
			network.send(sc, menu);

		} catch (Exception e) {
			e.printStackTrace();
		}



	}

}
