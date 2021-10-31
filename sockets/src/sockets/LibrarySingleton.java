package sockets;

public class LibrarySingleton {
	private static Library library;

	
	private LibrarySingleton () {
		Library library= null;
	}
	public static Library getInstance() {
		if (library == null) {
			library = new Library();
		}
		
		return library;
	}
}
