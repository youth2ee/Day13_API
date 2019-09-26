package com.naver.n2;

import java.net.Socket;
import java.util.Scanner;

import com.naver.n2.network.Network;

public class Client3 {

	//아이디 입력, 비밀번호 입력 받기
	//서버로 전송하여
	//서버에서 리턴받기
	//로그인 성공 또는 실패를 받기
	
	public static void main(String[] args) throws Exception {
		
		Scanner ssc = new Scanner(System.in);
		Network network = new Network();
		
		Socket sc = new Socket("211.238.142.44", 8282);
		
		System.out.println("아이디를 입력");
		String str1 = ssc.next();
		System.out.println("비밀번호를 입력");
		String str2 = ssc.next();
		
		String str3 = str1+","+str2;
		
		//보내기
		network.send(sc, str3);
		
		//받기
		String check = network.receive(sc);
		System.out.println(check);
		
		//성공이라면
		//실패라면
		
		
		if(check.equals("로그인실패")) {
		
			System.out.println("회원가입");
			System.out.println("아이디를 입력");
			str1 = ssc.next();
			System.out.println("비밀번호를 입력");
			str2 = ssc.next();
			
			str3 = str1+","+str2;
			
			//보내기
			network.send(sc, str3);
			
			//받기
			check = network.receive(sc);
			if(check.equals("1")) {
				System.out.println("회원가입이 완료되었습니다.");				
			}
			
		}
		
		
		
	}
}
