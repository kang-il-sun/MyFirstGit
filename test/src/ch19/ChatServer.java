package ch19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer 
{
	public static void main(String[] args) throws Exception 
	{	//서버용 소켓 준비
		ServerSocket serversocket = null;
		
		try 
		{
			//서비스를 위한 포트 개방
			serversocket = new ServerSocket(5555); //포트번호 5555번 개방
			System.out.println("서비스가 시작되었습니다...");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("서비스를 시작할 수 없습니다...");
			System.exit(1); //비정상적 종료
		}
		
		//일반 (클라이언트)소켓 준비
		Socket clientSocket = null;
		try
		{	//클라이언트의 접속을 기다렸다가 접속하면 소켓 연결
			clientSocket = serversocket.accept();
			System.out.println("클라이언트의 ip : " + clientSocket.getInetAddress().getHostAddress());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		//발신용 스트림
		PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true);
		
		//수신용 스트림
		BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		String receive = "";
		String send = "Welcome !!!";
		
		writer.println(send); //메세지보내기
		
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			receive = reader.readLine();	//한 라인을 읽음
			if(receive == null || receive.equals("quit"))
			{
				break;
			}
		
		System.out.println("[client] " + receive );
		System.out.println("서버님 입력하세요(종료 : quit) : ");
		send = scan.nextLine();
		writer.println(send);	//메시지 보내기
		if(send.equals("quit")) {break;}
		}
		
		//리소스 정리
		scan.close();
		writer.close();
		reader.close();
		clientSocket.close();
		serversocket.close();
		
	}
	
}
