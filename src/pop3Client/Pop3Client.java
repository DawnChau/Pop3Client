package pop3Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Pop3Client {
	private static String POP3Server = "pop.163.com";
	private static String USERNAME = "13407195518@163.com";// ʵ��Ӧ���иĳ���ʵ���û���
	private static String PASSWORD = "123456abc";// ʵ��Ӧ���иĳ���ʵ������

	public static void main(String[] args) {
		int POP3Port = 110;
		Socket client = null;
		try {
			// ��POP3���������һ���׽������ӡ�
			client = new Socket(Pop3Client.POP3Server, POP3Port);
			//���׽����л�ȡ������
			InputStream is = client.getInputStream();
			//����������װ��reader-->��ȡ
			BufferedReader sockin = new BufferedReader(new InputStreamReader(is));
			//���׽����л�ȡ�����
			OutputStream os = client.getOutputStream();
			//���������װ��writer-->д��
			PrintWriter sockout = new PrintWriter(os, true);
			// ��ʾͬSMTP�����������ֹ��̡�
			System.out.println("S:" + sockin.readLine());
			
			//��֤user
			sockout.println("user " + Pop3Client.USERNAME);
			System.out.println("S:" + sockin.readLine());
			//��֤pass
			sockout.println("pass " + Pop3Client.PASSWORD);
			System.out.println("S:" + sockin.readLine());
			//��֤list
			sockout.println("list");
			while (true) {
				String reply = sockin.readLine();
				System.out.println(reply);
				if (reply.toLowerCase().equals(".")) {
					break;
				}
			}
			//��֤retr
			sockout.println("retr 1");
			while (true) {
				String reply = sockin.readLine();
				System.out.println(reply);
				if (reply.toLowerCase().equals(".")) {
					break;
				}
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
			}
		}
	}

}
