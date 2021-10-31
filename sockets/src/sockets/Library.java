package sockets;

import java.util.ArrayList;

public class Library {
	private ArrayList<Book> books = new ArrayList<Book>();

	public Library() {
		Book libro1= new Book(1,"El Quijote", "Miguel de Cervantes", 15.50);
		Book libro2= new Book(2,"La Colmena", "Camilo Jose Cela", 17.50);
		Book libro3= new Book(3,"Boxwood", "Camilo Jose Cela", 12.50);
		Book libro4= new Book(4,"Platero y yo", "Juan Ramon Jimenez", 15.50);
		Book libro5= new Book(5,"Romancero Gitano", "Federico Garcia Lorca", 19.50);
		addBookToLibrary(libro1);
		addBookToLibrary(libro2);
		addBookToLibrary(libro3);
		addBookToLibrary(libro4);
		addBookToLibrary(libro5);
	}
	public void addBookToLibrary(Book newBook) {
		books.add(newBook);
	}
	public ArrayList<Book> getArrayBook() {
		return books;
	}
	public void addBook(int isbn,String title,String author, Double price) {
		Book book= new Book(isbn,title,author,price);
		addBookToLibrary(book);
	}
	public String getToString(int i) {
		return books.get(i).toString();
	}
	public ArrayList<Book> consultByIsbn(int isbn) {
		ArrayList<Book> aux = new ArrayList<Book>();
		for(Book book: this.books) {
			if(isbn == book.getIsbn()) {
				
				aux.add(book);
			}
		}
		return aux; 
	}
	public ArrayList<Book> consultByTitle(String title) {
		ArrayList<Book> aux = new ArrayList<Book>();
		for(Book book: this.books) {
			if(title.equals(book.getTitle())) {
				aux.add(book);
			}
		}
		
		return aux;
		
	}
	public ArrayList<Book> consultByName(String author) {
		ArrayList<Book> aux = new ArrayList<Book>();
		for(Book book: this.books) {
			if(author.equals(book.getAuthor())) {
				aux.add(book);
			}
		}
		return aux;
	}
	 
	
}
