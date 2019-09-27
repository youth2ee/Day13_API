package com.naver.n2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.naver.n2.food.MenuMaker;
import com.naver.n2.member.Member;
import com.naver.n2.member.MemberService;
import com.naver.n2.network.Network;

public class Server4 {

	public static void main(String[] args) throws Exception {
		//받아서 분리해서 로그인 판가름
		//member파일을 받아서 비교하기

		//파싱해서 멤버변수에 넣어라.
		//멤버를 모으는 애로 바뀐다.
		//어레이리스트 제네릭 멤버

		//파싱하는 애를 하나의 클래스로 만든다.

		Network network = new Network();
		MemberService ms = new MemberService();
		MenuMaker mm = new MenuMaker();
		mm.init(); //파싱작업해서 모으기

		System.out.println("클라이언트의 응답을 기다리는 중");
		ServerSocket ss = new ServerSocket(8282);
		Socket sc = ss.accept();

		//받기 
		String str = network.receive(sc);

		//파일
		ArrayList<Member> ar = new ArrayList<Member>();
		ar = ms.init();

		//비교하기
		String check = ms.memberLogin(str,ar);

		//보내기
		network.send(sc, check);
		
		//받기
		check = network.receive(sc);


		if(check.equals("로그인성공")) {
			//성공시 점심인지 저녁인지 받기
			str = network.receive(sc);

			//메뉴메이커클래스에서 해결해서 다시 받기
			check = mm.selectMenu(str);

			//보내기
			network.send(sc, check);
			
		} else if(check.equals("로그인실패")) {
			//실패시 회원가입 정보받기
			str = network.receive(sc);

			//파일에 추가하기
			check = ms.makeMember(str, ar);

			//보내기
			network.send(sc, check);
		}
	}
}
