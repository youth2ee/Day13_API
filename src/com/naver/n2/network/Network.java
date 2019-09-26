package com.naver.n2.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Network {
	
	public void send(Socket sc, String msg) throws Exception { //보내기
		
		OutputStream os = sc.getOutputStream(); //바이트처리
		OutputStreamWriter ow = new OutputStreamWriter(os); //char처리
		BufferedWriter bw = new BufferedWriter(ow); //string처리 (보조스트림)

		bw.write(msg+"\r\n"); //실제로 보낸다.
		bw.flush(); //버퍼를 강제로 비운다.

	}

	public String receive(Socket sc) throws Exception { //받기
		InputStream is = sc.getInputStream();
		InputStreamReader ir = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(ir);
		
		String msg = br.readLine(); //실제로 받는다.

		return msg;
		
	}
	
}
