package com.intelligrated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * simple tcp server
 */
public class TCPServer {
	static ServerSocket serversocket;
	
	static BufferedReader input;
	static PrintWriter output;
	
	public static void main(String[] args) throws Exception {
		
		try {
			serversocket = new ServerSocket(4444);
			
			System.out.println("TCPServer started on port " + serversocket.getLocalPort());
			
			Socket socket = serversocket.accept(); // blocks until client connects
			
			//1st read and display incoming message
			input= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message = input.readLine();
			
			output = new PrintWriter(socket.getOutputStream(), true);
			
			do {
				System.out.println("--- server got from client: " + message);
				
				// write back to client
				String reply = message.toUpperCase();
				output.println(reply);
				
				// read from client
				message = input.readLine();
			} while(null != message && !message.equals("quit"));
			
			System.out.println("--- server got from client: " + message);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		} finally {
			try {
				serversocket.close();
			} catch (IOException e) {
				System.err.println("server FAILED to close connection");
			}
			
			input.close();
			output.close();
		}
	}

	
//	static ServerSocket serverSocket;
//	static Socket clientSocket;
//
//	static BufferedReader input;
//	static PrintWriter output;
//
//	public static void main(String[] args) throws Exception {
//		if (args.length != 1) {
//			System.err.println("Usage: java TCPServer <port number>");
//			System.exit(1);
//		}
//
//		int portNumber = Integer.parseInt(args[0]);
//
//		System.out.println("port number: " + portNumber);
//
//		System.out.println("creating server socket");
//		serverSocket = new ServerSocket(portNumber);
//
//		System.out.println("creating client socket");
//		clientSocket = serverSocket.accept();
//		// Listens for a connection to be made to this socket and accepts it.
//		// The method blocks until a connection is made.
//		System.out.println("creating input stream");		
//		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//		System.out.println("creating output stream");
//		output = new PrintWriter(clientSocket.getOutputStream(), true);
//
//		output.println("server saying HELLO");
//		
////		String sentence = input.readLine();
////		System.out.println("from client: " + sentence);
////		
////		String reply = sentence.toUpperCase();
////		
////		output.write(reply);	
//		
////		clientSocket.close();
//	}
}
