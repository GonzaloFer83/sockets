package sockets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONException;


public class ServidorSocket {
	public static final int PUERTO = 2017;
	public static final String IP_SERVER = "localhost";
	
	
	public void startServer() throws InterruptedException, JSONException {
		System.out.println("      APLICACION DE SERVIDOR      ");
		System.out.println("----------------------------------");
		
		//Este objeto es el que abrir· un puerto
		ServerSocket servidorSocket = null;
		PrintStream salida = null;
		InputStreamReader entrada = null;
		Socket socketConexion = null;
		
		try {
			servidorSocket = new ServerSocket();
			InetSocketAddress direccion = new InetSocketAddress(IP_SERVER,PUERTO);
			//decimos al socket que escuche peticiones desde la IP y el puerto
			servidorSocket.bind(direccion);
			System.out.println("SERVIDOR: Esperando peticion...");
			//estamos continuamente escuchando 
			while(true){
				
				//Cuando la conexion es establecida, se crea un socket en 
				//para llevar la comunicacion
				socketConexion = servidorSocket.accept();//Se parar· el programa, hasta que entre la peticion de un cliente
													//Y se crear un objeto Socket
				
				new ServerMessageController(socketConexion);
				
				
				
				
				
				
				
				//entrada = new InputStreamReader(socketConexion.getInputStream());
				//este InputStreamReader permite leer caracter a caracter
				//BufferedReader bf = new BufferedReader(entrada);
				//este BufferedReader permite leer frase a frase
				
				//El servidor se quedarÌa aquÌ parado hasta que el cliente escriba algo
				//String stringRecibido = bf.readLine();//"3-4"
				//TODO LO QUE LLEGA DEL CLIENTE Y LO QUE LE MANDE AL SERVIDOR SON STRING
				
				
				
				//salida = new PrintStream(socketConexion.getOutputStream());
				//salida.println(serverController.decodemessage(stringRecibido));
				
				
				
				
				
				/*
				System.out.println("SERVIDOR: Me ha llegado del cliente: " + stringRecibido);
				String[] operadores = stringRecibido.split("-");
				int iNumero1 = Integer.parseInt(operadores[0]);//3
				int iNumero2 = Integer.parseInt(operadores[1]);//4
				int resultado = iNumero1 + iNumero2;//7 
				//Thread.sleep(10000);//simulamos que tardamos un tiempo en calcular
				salida = new PrintStream(socketConexion.getOutputStream());
				salida.println("resultado:" + resultado);
				*/
			}
		} catch (IOException excepcion) {
			System.out.println(excepcion);
		}finally {//ES MUYYY IMPORTANTE QUE EN LOS SOCKETS SIEMPRE SE CIERREN LAS CONEXIONES
			try {
				if(salida != null && entrada != null){
					salida.close();
					entrada.close();
					socketConexion.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	} 
	

}
