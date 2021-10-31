package sockets;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.Socket;

import com.google.gson.Gson;

public class ServerMessageController implements Runnable{
	Library library;
	private Thread hilo;
	private static int numCliente = 0;
	private Socket socketAlCliente;		
	private PrintStream salida = null;
	private InputStreamReader entrada = null;
	private BufferedReader entradaBuffer = null;
	JSONObject myObject = new JSONObject();

	
	
	public ServerMessageController(Socket socketAlCliente) {
		numCliente++;
		hilo = new Thread(this , "Cliente_"+numCliente);
		this.socketAlCliente = socketAlCliente;

		hilo.start();

	}
	
	
	public void run() {
		//System.out.println("Estableciendo comunicacion con " + hilo.getName());


		
		String finalmessage= null;
		try {
			salida = new PrintStream(socketAlCliente.getOutputStream());
			//Entrada del servidor al cliente
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			entradaBuffer = new BufferedReader(entrada);
			finalmessage = decodemessage(entradaBuffer.readLine());
			salida.println(finalmessage);
			socketAlCliente.close();
		} catch (IOException e) {
			System.err.println("HiloContadorLetras: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("HiloContadorLetras: Error");
			e.printStackTrace();
		}
		
		
		
		
	}

	public String decodemessage(String message) throws JSONException {
		JSONObject newjson = new JSONObject(message);
		StringBuilder sb = new StringBuilder();
		switch (newjson.getString("No")) {
		case "1":
			sb.append("{\"No\": \"1\",");
			sb.append(consultByIsbn(newjson.getInt("Option")));
			sb.append("}");
			break;
		case "2":
			sb.append("{\"No\": \"2\",");
			sb.append(consultByTitle(newjson.getString("Option")));
			sb.append("}");
			break;
		case "3":
			sb.append("{\"No\": \"3\",");
			sb.append(consultByName(newjson.getString("Option")));
			sb.append("}");
			break;
		case "4":
			JSONArray array = newjson.getJSONArray("Option");
			JSONObject object = array.getJSONObject(0);
			library=LibrarySingleton.getInstance();
			library.addBook(object.getInt("isbn"), object.getString("title"),
					object.getString("author"), object.getDouble("price"));

			sb.append("Se ha añadido un libro nuevo.");
			break;
		}
		System.out.println(sb.toString());

		return sb.toString();
	}

	public String buildJson(ArrayList<Book> aux) {

		StringBuilder sb = new StringBuilder();
		Gson gson = new Gson();

		
		sb.append("\"books\":[");
		
		for (int i = 0; i < aux.size(); i++) {
			


			String json = gson.toJson(aux.get(i));	

			sb.append(json);
			sb.append((i < aux.size()-1) ? "," : "");
		}
		sb.append("]");
		return sb.toString();
	}

	public String consultByIsbn(int isbn) {
		library = LibrarySingleton.getInstance();
		String result = null;
		result = buildJson(library.consultByIsbn(isbn));
		if(result.equals("\"books\":[]")) {
			result = "\"books\":[{\"Error\": \"No existe libro con este ISBN\"}]";
		}
		return result;
	}

	public String consultByName(String name) {
		library = LibrarySingleton.getInstance();
		String result = null;
		result = buildJson(library.consultByName(name));
		if(result.equals("\"books\":[]")) {
			result = "\"books\":[{\"Error\": \"No existen libros de este Author\"}]";
		}
		return result;
	}

	public String consultByTitle(String title) {
		
		library = LibrarySingleton.getInstance();
		String result = null;
		result = buildJson(library.consultByTitle(title));
		if(result.equals("\"books\":[]")) {
			result = "\"books\":[{\"Error\": \"No existe libro con este Titulo\"}]";
		}
		return result;
	}

	
}
