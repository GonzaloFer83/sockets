package sockets;

import org.json.JSONException;

public class MainServer {

	public static void main(String[] args) throws InterruptedException, JSONException {
		
		ServidorSocket server1 = new ServidorSocket();
		server1.startServer();
		
		

	}

}
