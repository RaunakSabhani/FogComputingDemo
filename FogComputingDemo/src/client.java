import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 9998);
			new creceiver(socket.getInputStream()).start();
			new csender(socket.getOutputStream()).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class csender extends Thread
{
	OutputStreamWriter server;
	csender(OutputStream server)
	{
		this.server = new OutputStreamWriter(server);
	}
	@Override
	public void run() {
		System.out.println("Sender run started!");
		// TODO Auto-generated method stub
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(server);
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
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class creceiver extends Thread
{
	InputStreamReader server;
	creceiver(InputStream server)
	{
		this.server = new InputStreamReader(server);
	}
	@Override
	public void run() {
		System.out.println("Receiver run started!");
		// TODO Auto-generated method stub
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(server);
			while(true)
			{
				String message = reader.readLine();
				System.out.println("Server: "+message);
			}
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
