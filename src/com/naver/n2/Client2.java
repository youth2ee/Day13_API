package com.naver.n2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.naver.n2.network.Network;

public class Client2 {

	public static void main(String[] args) {
		// 1. 점심메뉴
		// 2. 저녁메뉴

		Scanner scc = new Scanner(System.in);
		Network network = new Network();

		try {
			//연결해서 점심인지 저녁인지 고르기
			Socket sc = new Socket("211.238.142.44", 8282);
			//"127.0.0.1" : 자기아이피번호
			//"localhost" : 자기컴퓨터
			//직접적인 아이피번호를 쓰는게 낫다.

			System.out.println("무엇을 골라드릴까요");
			System.out.println("1. 점심");
			System.out.println("2. 저녁");
			String str = scc.next();

			//서버에게 보내기
			network.send(sc, str);			
			System.out.println("서버로 전송완료");

			//서버가 골라준것 받기	
			str = network.receive(sc);
			System.out.println(str);
			System.out.println("서버가 보낸 것을 받았습니다.");


		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
