package com.naver.n2.member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;


public class MemberService2 {

	private HashMap<String, Member> map; 
	//선언만하면 null값일 뿐이다. 모든 레퍼런스타입의 기본값은 null.
	//값을 넣으려면 new해서 객체생성해야한다. 
	//값을 안넣으면 NullPointException
	//넣기 : put 
	//꺼내기 : get


	public HashMap<String, Member> init() throws Exception {
		//static메서드 안에서는 멤버메서드는 객체 생성해서 this 써야한다.

		//파일불러서 자르기
		File file = new File("c:\\test", "member.txt");
		FileReader fr = new FileReader(file); //char로 읽는다.
		BufferedReader br = new BufferedReader(fr); //보조스트림 . string으로 읽는다.
		this.map = new HashMap<String, Member>(); //객체생성

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

				map.put(member.getId(), member); //map에 값을 넣기
				map.get(member.getId()); //키값으로 map의 값을 꺼내기
			}//while1

			//map의 키들을 모으는 컬렉션
			Iterator<String> it = map.keySet().iterator();

			while(it.hasNext()) {
				String k = it.next();
				Member m = map.get(k);

				System.out.println(m.getId());
				System.out.println(m.getPw());
			}
		}//while2
		return map;
	}//init메서드


	public Member memberLogin(Member member) {
		//id와 pw를 매개변수로 받아서 로그인유무 검증
		//로그인 실패시 null값을 리턴한다.
		Member m = map.get(member.getId()); //파일값
		if(m!=null) { //키값이 잘못들어와서 null이 아닌경우에만, 패스워드까지 확인한다.
			if(m.getPw().equals(member.getPw())) {  //클라이언트에게 받은값과 비교
				//로그인성공
				//after 추가정보 넣기 (데이터베이스)
			} else {
				//아이디는 맞지만, 패스워드 틀려서 로그인실패
				m=null;
			}
		}
		return m;
	} //memberlogin메서드


}
