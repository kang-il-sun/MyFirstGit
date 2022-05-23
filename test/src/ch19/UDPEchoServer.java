package ch19;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//UDP : �񿬰Ἲ(�ܹ��� ����)
//TCP : ���Ἲ(������ Ŭ���̾�Ʈ�� ���� ����� ���¿��� ������ �ۼ���)

public class UDPEchoServer {
	public UDPEchoServer(int port)
	{
		try {
			DatagramSocket ds = new DatagramSocket(port);
			while(true)
			{	//udp ����� �����͸� ����Ʈ�迭�� ������ String�� �ٷ� ������ ���� 
				//(���� : ����Ʈ�迭�� �ִ�ũ��� byte 65508)
				byte buffer[] = new byte[512];
				//udp����� ����� �����͸� ��Ŷ(����������)���� ����� ����
				DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
				System.out.println("ready");
				//Ŭ���̾�Ʈ�� ���� �޽����� ����
				//������ ���� ���ŵ� Ŭ���̾�Ʈ �޽����� DatagramPacket�� ����
				ds.receive(dp);
				//Ŭ���̾�Ʈ���� ���� �޽��� (����Ʈ�迭) �� ��Ʈ���迭�� ��ȯ
				//new String (����Ʈ�迭) ==> ���ڿ��� ��ȯ
				String str = new String (dp.getData());
				System.out.println("Ŭ���̾�Ʈ���� ���� �޽��� : " + str);
				
				InetAddress ia = dp.getAddress(); //Ŭ���̾�Ʈ �� ip�ּ�
				port = dp.getPort(); //port �� ȣ
				System.out.println("client ip : " + ia + ", client port : " + port);
				//Ŭ���̾�Ʈ���� ������ ��Ŷ�� ����
				//(����Ʈ�迭, ����Ʈ�迭ũ��, ip�ּ�, port��ȣ)
				dp = new DatagramPacket(dp.getData(), dp.getData().length, ia, port);
				ds.send(dp);  //Ŭ���̾�Ʈ���� �ڷ�����
				ds.close();// ���� �ݱ�
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	} //������
	public static void main(String[] args) throws Exception
	{
		new UDPEchoServer(3000); //������ ��Ʈ��ȣ ����
	}
}
