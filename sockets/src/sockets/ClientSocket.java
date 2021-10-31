package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

public class ClientSocket {
	public static final int PUERTO = 2017;
	public static final String IP_SERVER = "localhost";
	ClientMessageController clientController = new ClientMessageController();
	int electionValue = 0;

	public void start() {

		Socket socketCliente = null;
		InputStreamReader entrada = null;
		PrintStream salida = null;

		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER,
				PUERTO);

		Scanner sc = new Scanner(System.in);
		
		try {

			while (electionValue != 5) {

				socketCliente = new Socket();
				socketCliente.connect(direccionServidor);
				//System.out.println("Conexion establecida... a " + IP_SERVER
					//	+ " por el puerto " + PUERTO);

				entrada = new InputStreamReader(socketCliente.getInputStream());// entrada
																				// de
																				// datos
																				// del
																				// servidor
																				// (from)
				salida = new PrintStream(socketCliente.getOutputStream());// salida
																			// de
																			// datos
																			// al
																			// servidor(to)
				String value2 = null;
				System.out.println(
						"Por favor introduzca:" + "\n1:Consulta libro por ISBN."
								+ "\n2:Consulta libro por titulo."
								+ "\n3:Consulta libro por Autor."
								+ "\n4:Añadir nuevo libro." + " \n5:Salir");
				String value1 = sc.nextLine();
				switch (value1) {
				case "1":
					System.out.println("Escriba el ISBN");
					value2 = sc.nextLine();

					break;
				case "2":
					System.out.println("Escriba el titulo del libro");
					value2 = sc.nextLine();
					break;
				case "3":
					System.out.println("Escriba el nombre del autor");
					value2 = sc.nextLine();
					break;
				case "4":
					JSONObject json = new JSONObject();

					String aux = null;
					StringBuilder sb = new StringBuilder();
					System.out.println("Dame el ISBN");
					aux = sc.nextLine();
					json.put("isbn", (Integer.parseInt(aux)));

					System.out.println("Dame el titulo de la obra:");

					aux = sc.nextLine();
					json.put("title", aux);

					System.out.println("Dame el autor de la obra");
					aux = sc.nextLine();
					json.put("author", aux);

					System.out.println("Dame el importe");
					aux = sc.nextLine();
					json.put("price", (Double.parseDouble(aux)));
					sb.append("[");
					sb.append(json.toString());
					sb.append("]");
					value2 = sb.toString();
					
					break;
				case "5":
					System.out.println("Que la fuerza te acompañe!!!!");
					break;
				default:
					System.out.println("El valor introducido no es correcto."
							+ "\n Vuelva a intentarlo.");

				}

				if (value1.equals("1") || value1.equals("2")
						|| value1.equals("3") || value1.equals("4")) {

					StringBuilder sb = new StringBuilder();
					sb.append("{");
					sb.append("\"No\": \"" + value1 + "\",");
					sb.append("\"Option\":" + value2);
					sb.append("}");
					
					String sendString = sb.toString();
					salida.println(sendString);
				}
				BufferedReader bf = new BufferedReader(entrada);
				String resultado = bf.readLine();
					if(value1.equals("1") || value1.equals("2")
							|| value1.equals("3")) {
					clientController.decodemessage(resultado);
					}else {
					System.out.println(resultado);
					}

				/*
				 * String numero2 = sc.nextLine(); String operandos = value1 +
				 * "-" + numero2;// 3-4 salida.println(operandos);// 3-4
				 * 
				 * // esta clase nos ayuda a leer datos del servidor linea a
				 * linea BufferedReader bf = new BufferedReader(entrada); // En
				 * la siguiente linea se va a quedar parado el hilo principal //
				 * de ejecuciÛn hasta que el servidor responda String resultado
				 * = bf.readLine();// Hola soy el servidor, te // reenvio // la
				 * // suma:7
				 * 
				 * System.out.println("CLIENTE: " + resultado);// resultado:7
				 * String[] arrayResultado = resultado.split(":");
				 * 
				 * System.out.println("CLIENTE: El resultado de la suma es: " +
				 * arrayResultado[1]);// 7
				 */
				salida.close();
				entrada.close();
				// socketCliente.close();
				// sc.close();
			}
		} catch (Exception e) {
			System.err.println("Error: " + e);
			e.printStackTrace();
		} finally {// ES MUYYY IMPORTANTE QUE EN LOS SOCKETS SIEMPRE SE CIERREN
					// LAS CONEXIONES
			try {
				salida.close();
				entrada.close();
				socketCliente.close();
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
