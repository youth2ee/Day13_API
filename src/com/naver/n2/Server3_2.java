package com.naver.n2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.naver.n2.member.Member;
import com.naver.n2.member.MemberService;
import com.naver.n2.member.MemberService2;
import com.naver.n2.network.Network;

public class Server3_2 {

	public static void main(String[] args) {
		//받아서 분리해서 로그인 판가름
		//member파일을 받아서 비교하기

		//파싱해서 멤버변수에 넣어라.
		//멤버를 모으는 애로 바뀐다.
		//어레이리스트 제네릭 멤버

		//파싱하는 애를 하나의 클래스로 만든다.

		System.out.println("클라이언트의 응답을 기다리는 중");
		ServerSocket ss=null;
		Socket sc=null;

		MemberService2 ms = new MemberService2();
		Network network = new Network();


		try {
			ms.init();
			ss = new ServerSocket(8282);
			sc = ss.accept();
			String msg = network.receive(sc); //id,pw

			String [] info = msg.split(",");
			Member member = new Member();
			member.setId(info[0]);
			member.setPw(info[1]);

			member = ms.memberLogin(member);

			msg = "0"; //로그인실패
			if(member!=null) { //로그인성공
				msg="1";
			}

			//보내기
			network.send(sc, msg);
		} catch (Exception e) {

			e.printStackTrace();
		}




	}
}
