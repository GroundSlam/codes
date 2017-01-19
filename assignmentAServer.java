package assignment1AServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

public class assignmentAServer {
	public static void main(String[] args) {
		int port = 12345;

		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("server is ready to recivie command");
			while (true) {
				// accept connection request
				Socket socket = ss.accept();
				// get the input stream to read data
				InputStream is = socket.getInputStream();
				// read data as character
				InputStreamReader isr = new InputStreamReader(is);
				// read data as lines
				BufferedReader br = new BufferedReader(isr);
				// read the string command from the user aka the request send
				// from the client side
				String command = br.readLine();
				int result;
				int num1;
				int num2;
				String respond = "";
				boolean flag = false;
				// store the split and store arr
				String arr[] = command.split(" ");

				if (arr.length != 3) {
					respond = "Invaild number of argument";
				} else {
					// check for first item number or not
					try {
						num1 = Integer.parseInt(arr[1]);
						num2 = Integer.parseInt(arr[2]);

					} catch (NumberFormatException e) {
						// TODO: handle exception
						flag = true;
						respond = arr[1] + " is not a number";

					}
					// check for the second item number or not
					try {
						num2 = Integer.parseInt(arr[2]);

					} catch (NumberFormatException e) {
						// TODO: handle exception
						flag = true;
						respond = arr[2] + " is not a number";

					}

					if (!flag) {

						switch (arr[0]) {
						case "add":
							result = Integer.parseInt(arr[1]) + Integer.parseInt(arr[2]);
							respond = "the add result is: " + result;
							break;

						case "divide":
							if (Integer.parseInt(arr[2]) == 0) {
								respond = "cannot divide by zero";
							} else {
								result = Integer.parseInt(arr[1]) / Integer.parseInt(arr[2]);
								respond = "the div result is: " + result;
							}
							break;

						case "substract":
							result = Integer.parseInt(arr[1]) - Integer.parseInt(arr[2]);
							respond = "the sub result is: " + result;
							break;
						case "mulitply":
							result = Integer.parseInt(arr[1]) * Integer.parseInt(arr[2]);
							respond = "the mul result is: " + result;
							break;
						default:
							respond = "Nothing";
							break;
						}
					}

				}

				OutputStream os = socket.getOutputStream();

				PrintWriter pw = new PrintWriter(os);
				pw.println(respond);
				// clear the stream
				pw.flush();
				// close the connections
				pw.close();
				socket.close();
				
			}

		} catch (IOException ex) {
			ex.printStackTrace();
			// TODO: handle exception
		}

	}
}