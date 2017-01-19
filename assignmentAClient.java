package assignment1AClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class assignmentAClient {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Name of the host that we are going to connect to
		String hostName = "127.0.0.1";
		// Make sure that this port is the same as //the server listening port
		int port = 12345;
		try {
			// Use socket to connect to the server
			// Socket socket = new Socket(hostName, port);

			String request = Helper.readString("Please input command: ");
			while (!request.equals("quit")) {
				Socket socket = new Socket(hostName, port);
				// Access to the output stream
				OutputStream os = socket.getOutputStream();
				// Write line
				PrintWriter pw = new PrintWriter(os);
				pw.println(request);
				pw.flush();
				// Read response.
				InputStream is = socket.getInputStream();
				// Read characters
				InputStreamReader isr = new InputStreamReader(is);
				// Read lines
				BufferedReader br = new BufferedReader(isr);
				String header = br.readLine();
				System.out.println("" + header);

				request = Helper.readString("Please input command: ");
				socket.close();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
