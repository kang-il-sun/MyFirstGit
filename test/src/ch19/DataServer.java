package ch19;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

//������ ���α׷�
public class DataServer 
{
	public static void main(String[] args) throws Exception 
	{
		ServerSocket ss = new ServerSocket(8000);
		System.out.println("���񽺰� ���۵Ǿ����ϴ�..");
		while(true)
		{
			//accept() : Ŭ���̾�Ʈ�� �����Ҷ����� ��� ����
			//�����ϸ� ���� ���� (������ ȸ�� ����)
			Socket socket = ss.accept();
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� mm�� dd�� hh�� mm�� ss��");
			
			String str = sdf.format(new Date());	//Date() : �ý��� ��¥
			out.println(str);	//Ŭ���̾�Ʈ���� ������ ����
			socket.close();//���� ����
		}
	}
}
