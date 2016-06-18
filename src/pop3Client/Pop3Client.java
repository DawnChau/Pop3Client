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
	private static String USERNAME = "13407195518@163.com";// 实际应用中改成真实的用户名
	private static String PASSWORD = "123456abc";// 实际应用中改成真实的密码

	public static void main(String[] args) {
		int POP3Port = 110;
		Socket client = null;
		try {
			// 向POP3服务程序建立一个套接字连接。
			client = new Socket(Pop3Client.POP3Server, POP3Port);
			//从套接字中获取输入流
			InputStream is = client.getInputStream();
			//将输入流包装成reader-->读取
			BufferedReader sockin = new BufferedReader(new InputStreamReader(is));
			//从套接字中获取输出流
			OutputStream os = client.getOutputStream();
			//将输出流包装成writer-->写入
			PrintWriter sockout = new PrintWriter(os, true);
			// 显示同SMTP服务程序的握手过程。
			System.out.println("S:" + sockin.readLine());
			
			//验证user
			sockout.println("user " + Pop3Client.USERNAME);
			System.out.println("S:" + sockin.readLine());
			//验证pass
			sockout.println("pass " + Pop3Client.PASSWORD);
			System.out.println("S:" + sockin.readLine());
			//验证list
			sockout.println("list");
			while (true) {
				String reply = sockin.readLine();
				System.out.println(reply);
				if (reply.toLowerCase().equals(".")) {
					break;
				}
			}
			//验证retr
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
