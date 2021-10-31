package sockets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientMessageController {
	public ClientMessageController() {

	}

	public void decodemessage(String message) throws JSONException {
		JSONObject json = new JSONObject(message);
		JSONArray array = json.getJSONArray("books");

		switch (json.getString("No")) {
		case "1":

			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				if (object.has("Error")) {
					System.out.println(object.getString("Error"));
				} else {
					System.out.println("El libro con el ISBN "
							+ object.getInt("isbn") + " es:");
					System.out.println("Titulo: " + object.getString("title"));
					System.out.println("Autor: " + object.getString("author"));
					System.out.println("Precio: " + object.getDouble("price"));
				}
			}
			break;
		case "2":

			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				if (object.has("Error")) {
					System.out.println(object.getString("Error"));
				} else {
					System.out.println("El libro con el Titulo "
							+ object.getString("title") + " es:");
					System.out.println("ISBN: " + object.getInt("isbn"));
					System.out.println("Autor: " + object.getString("author"));
					System.out.println("Precio: " + object.getDouble("price"));
				}
			}
			break;
		case "3":
			System.out.println("El autor ha escrito");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				if (object.has("Error")) {
					System.out.println(object.getString("Error"));
				} else {
					System.out.println("ISBN:" + object.getInt("isbn"));
					System.out.println("Titulo:" + object.getString("title"));
					System.out.println("Precio: " + object.getDouble("price"));
				}
			}
			break;

		}
	}
}
