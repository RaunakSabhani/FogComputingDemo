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

public class server {

	public static void main(String[] args) {
		ServerSocket socket;
		try {
			socket = new ServerSocket(9998);
			while(true)
			{
				Socket clientSocket = socket.accept();
				new receiver(clientSocket.getInputStream()).start();
				new sender(clientSocket.getOutputStream()).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
class sender extends Thread
{
	OutputStreamWriter client;
	sender(OutputStream client)
	{
		this.client = new OutputStreamWriter(client);
	}
	@Override
	public void run() {
		System.out.println("Sender run started!");
		// TODO Auto-generated method stub
		BufferedWriter writer = null;
		try 
		{
			writer = new BufferedWriter(client);
			Scanner in  = new Scanner(System.in);
			while(true)
			{
				String message = in.nextLine();
				writer.write(message+" \n");
				writer.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				client.close();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
class receiver extends Thread
{
	InputStreamReader client;
	receiver(InputStream client)
	{
		this.client = new InputStreamReader(client);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader reader = null;
		System.out.println("Receiver run started!");
		try {
			reader = new BufferedReader(client);
			while(true)
			{
				String message = reader.readLine();
				System.out.println("Client: "+message);
			}
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}