package com.naver.n1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server { //서버는 다수에게 보낸다.

	public static void main(String[] args) { 
		//서버는 다수에게 보내기 때문에 소켓을 만들지 않는다. 소켓 만들면 1번만 만들어지므로
		//서버는 서버소켓이 필요하다
		//ServerSocket
		ServerSocket ss =null;
		Socket sc = null;

		InputStream is=null;
		InputStreamReader ir=null;
		BufferedReader br=null;

		OutputStream os=null;
		OutputStreamWriter ow=null;
		BufferedWriter bw=null;

		Scanner scc = new Scanner(System.in);

		try {
			ss = new ServerSocket(8282);
			//누군가가 서버에 접속하기를 기다리는 애, 포트번호로 들어오는 신호를 기다리는 애
			//클라이언트가 포트번호 8282를 보냄

			System.out.println("클라이언트 접속 받기를 기다리는 중");
			sc = ss.accept();
			//클라이언트가 보낸 걸로  소켓을 만들어준다.

			boolean check = true;

			while(check) {
				//받기
				is = sc.getInputStream(); //byte 처리
				ir = new InputStreamReader(is); //char 처리
				br = new BufferedReader(ir); //string으로 처리

				String str = br.readLine(); //읽어들이기 
				if(str.toUpperCase().equals("Q")) {
					System.out.println("종료합니다.");
					break;
				}
				System.out.println(str);


				//보내기
				//연결되어 있으므로 다시 연결할 필요가 없다.
				os = sc.getOutputStream(); 
				ow = new OutputStreamWriter(os); 
				bw = new BufferedWriter(ow);

				System.out.println("클라이언트에게 전송할 메시지를 입력하세요");
				str = scc.next();


				bw.write(str+"\r\n");
				bw.flush(); 
				if(str.toUpperCase().equals("Q")) {
					System.out.println("종료합니다.");
					break;
				} //q를 일단 보내고 받은 클라이언트가 종료한다.
				System.out.println("클라이언트에게 전송완료");

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				ow.close();
				os.close();
				scc.close();
				br.close();
				ir.close();
				is.close();
				sc.close();
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}
