package com.naver.n1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client { // 클라이언트는 하나의 서버에게 보낸다. 

	public static void main(String[] args) {
		//1단계
		Socket sc=null; //소켓을 만들면 1번 일어남

		OutputStream os=null;
		OutputStreamWriter ow=null;
		BufferedWriter bw=null;

		InputStream is=null;
		InputStreamReader ir=null;
		BufferedReader br=null;

		Scanner scc = new Scanner(System.in);

		try {
			sc = new Socket("211.238.142.44", 8282);
			//String host : ip주소나 도메인주소를 적는다. 
			//int port : 포트번호를 적는다.

			boolean check =true;

			while(check) {
				//보내기
				os = sc.getOutputStream(); //내보내기
				ow = new OutputStreamWriter(os); // char를 내보낸다.
				bw = new BufferedWriter(ow); // 문자열String을 내보낸다.

				System.out.println("서버로 전송할 메시지를 입력하세요");
				String str = scc.next();

				bw.write(str+"\r\n");
				bw.flush(); //버퍼를 강제로 내보내기
				if(str.toUpperCase().equals("Q")) {
					System.out.println("종료합니다.");
					break;
				} //q를 일단 보내고 받은 서버에서 종료한다.
				System.out.println("서버로 전송완료");


				//받기
				//연결되어 있으므로 다시 연결할 필요가 없다.
				is = sc.getInputStream(); 
				ir = new InputStreamReader(is);
				br = new BufferedReader(ir); 

				str = br.readLine();
				if(str.toUpperCase().equals("Q")) {
					System.out.println("종료합니다.");
					break;
				}
				System.out.println(str);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				ir.close();
				is.close();
				bw.close();
				ow.close();
				os.close();
				scc.close();
				sc.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
