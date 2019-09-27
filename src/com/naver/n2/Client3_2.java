package com.naver.n2;

import java.net.Socket;
import java.util.Scanner;

import com.naver.n2.network.Network;

public class Client3_2 {

	//아이디 입력, 비밀번호 입력 받기
	//서버로 전송하여
	//서버에서 리턴받기
	//로그인 성공 또는 실패를 받기

	public static void main(String[] args){

		Scanner ssc = new Scanner(System.in);
		Network network = new Network();

		System.out.println("아이디를 입력");
		String id = ssc.next();
		System.out.println("비밀번호를 입력");
		String pw = ssc.next();
		String msg = id+","+pw;

		Socket sc =null;

		//보내기
		try {
			sc = new Socket("211.238.142.44", 8282);
			network.send(sc, msg);

			//받기
			msg = network.receive(sc);
			if(msg.equals("1")) {
				System.out.println("로그인성공");
				System.out.println(id+"님 환영합니다.");
			}else {
				System.out.println("로그인실패");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}


}
