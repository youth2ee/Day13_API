package com.naver.n2.member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MemberService {
	

	public ArrayList<Member> init() throws Exception {
		//static메서드 안에서는 멤버메서드는 객체 생성해서 this 써야한다.
		
		//파일불러서 자르기
		File file = new File("c:\\test", "member.txt");
		FileReader fr = new FileReader(file); //char로 읽는다.
		BufferedReader br = new BufferedReader(fr); //보조스트림 . string으로 읽는다.
		ArrayList<Member> ar = new ArrayList<>();

		boolean check = true;
		while(check) {
			String str = br.readLine(); //한줄씩 읽는다.
			if(str==null) {
				break;
			}
			StringTokenizer st = new StringTokenizer (str,",");

			while(st.hasMoreTokens()) {
				Member member = new Member(); //멤버객체를 계속 새로 만들어줘야 새로 넣는다.
				
				member.setId(st.nextToken().trim());
				member.setPw(st.nextToken().trim());

				System.out.println(member.getId());
				System.out.println(member.getPw());

				ar.add(member);
			}//while1
		}//while2

		return ar;
	}//init


	public String memberLogin(String str, ArrayList<Member> ar) {
		//id와 pw를 매개변수로 받아서 로그인유무 검증

		Member member = new Member();

		String [] s = str.split(",");
		String id = s[0];
		String pw = s[1];

		String check=null;

		for(int i=0;i<ar.size();i++) {
			if(id.equals(ar.get(i).getId()) && pw.equals(ar.get(i).getPw())) {		
				check = "로그인성공";
				System.out.println(check);
				break;
			} else {
				check = "로그인실패";
				System.out.println(check);
			}
		}
		return check;

	} //memberlogin
	
	public String makeMember(String str,  ArrayList<Member> ar) throws Exception {
		
		File file = new File("c:\\test", "member.txt");
		FileWriter fw = new FileWriter(file,true);
		fw.write(str+"\r\n");
		fw.flush();
		
		String check = "1";
		System.out.println("회원가입이 완료되었습니다.");
	
	return check;
	}

}
