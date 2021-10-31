package sockets;

public class Book {
	private int isbn;
	private String title;
	private String author;
	private Double price;

	public Book(int isbn, String title, String author, Double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	/**
	 * @return the idBook
	 */
	public int getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the idBook to set
	 */
	public void setisbn(int isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "isbn=" + isbn + ", title=" + title + ", author="
				+ author + ", price=" + price;
	}


}
