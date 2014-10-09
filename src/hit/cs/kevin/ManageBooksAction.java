package hit.cs.kevin;

import java.sql.*;
import java.util.Vector;

import com.opensymphony.xwork2.ActionSupport;

public class ManageBooksAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4865661938793657579L;
	private BookDB bookDB;
	private Vector<Book> books;
	private Vector<Author> authors;
	private Vector<Book> booksFind;
	private Book book;
	private Author author;
	private String isbn;
	private String authorName;
	
	public ManageBooksAction(){
		bookDB = new BookDB();
		
		books = new Vector<Book>();
		authors = new Vector<Author>();
		booksFind = new Vector<Book>();
		
		book = new Book();
		author = new Author();
		
		bookDB.readBookDB();
		
		authorName = null;
	}
	
	public String list(){
		books = bookDB.getBookData();
		authors = bookDB.getAuthorData();
		//System.err.println(books.elementAt(3).isbn.toString());
		return "success";
	}
	
	public String delete() {
		bookDB.delete(isbn);
		bookDB.readBookDB();
		books = bookDB.getBookData();
		authors = bookDB.getAuthorData();
		return "success";
	}
	
	public String details() {
		book = bookDB.getBook(isbn);
		author = bookDB.getAuthor(book.authorid);
		return "success";
	}
	
	public String findBooks() {
		authors = bookDB.getAuthorData();
		books = bookDB.getBookData();
		String idString = "0";
		for (Author author : authors) {
			if (author.name.compareTo(authorName) == 0) {
				idString = author.authorid;
			}
		}

		if(idString.compareTo("0") == 0){
			return "fail";
		}
		
		for (Book book : books) {
			if(book.authorid.compareTo(idString) == 0){
				System.out.println(book.isbn);
				System.out.println(book.publishdate);
				booksFind.add(book);
			}
		}
		return "success";
	}

	public Vector<Book> getBooks() {
		return books;
	}

	public void setBooks(Vector<Book> books) {
		this.books = books;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Vector<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Vector<Author> authors) {
		this.authors = authors;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Vector<Book> getBooksFind() {
		return booksFind;
	}

	public void setBooksFind(Vector<Book> booksFind) {
		this.booksFind = booksFind;
	}
	
}

class BookDB{
	private Vector<Book> bookData;
	private Vector<Author> authorData;
	
	public BookDB(){
		bookData = new Vector<Book>();
		authorData = new Vector<Author>();
	}
	
	// get book data from mysql:bookdb
	public void readBookDB(){
		String url = "jdbc:mysql://localhost:3306/bookdb";
		String user = "root";
		String password = "apple610"; 
		String driveName = "com.mysql.jdbc.Driver";
		
		try{
			Class.forName(driveName);
			//System.out.println("loading database successfully!");
			//System.out.println("=============================================================");
		}catch(java.lang.ClassNotFoundException e){
			System.out.println("loading database error!");
			System.out.println(e.getMessage());
		}
		
		try {
			// 1. Get connection to a database
			Connection myConn = DriverManager.getConnection(url, user, password);
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			ResultSet myRs = myStmt.executeQuery("select * from book");
			
			// 4. Process the result set
			while(myRs.next()){
				/*System.out.println(myRs.getString("ISBN") + ", " + myRs.getString("title") + ", " 
							+ myRs.getString("authorid") +  ", " + myRs.getString("publishdate") 
							+ ", " + myRs.getString("publisher") + ", " 
							+ myRs.getString("price"));*/
				Book book = new Book();
				book.isbn = myRs.getString("ISBN");
				book.title = myRs.getString("title");
				book.authorid = myRs.getString("authorid");
				book.publishdate = myRs.getString("publishdate");
				book.publisher = myRs.getString("publisher");
				book.price = myRs.getString("price");
				
				bookData.add(book);
			}
			
			ResultSet myRs2 = myStmt.executeQuery("select * from author");
			
			while(myRs2.next()){
				/*System.out.println(myRs2.getString("authorid") + ", " + myRs2.getString("name") + ", " 
							+ myRs2.getString("age") +  ", " + myRs2.getString("country"));*/
				
				Author author = new Author();
				author.authorid = myRs2.getString("authorid");
				author.name = myRs2.getString("name");
				author.age = myRs2.getString("age");
				author.country = myRs2.getString("country");
				
				authorData.add(author);
				//System.out.println("add author success.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector<Book> getBookData() {
		return bookData;
	}

	public void setBookData(Vector<Book> bookData) {
		this.bookData = bookData;
	}
	
	public Vector<Author> getAuthorData() {
		return authorData;
	}

	public void setAuthorData(Vector<Author> authorData) {
		this.authorData = authorData;
	}

	public Book getBook(String isbn){
		Book result = new Book();
		for (Book book : bookData) {
			if(book.isbn.compareTo(isbn) == 0){
				return book;
			}
		}
		return result;
	}
	
	public Author getAuthor(String authorid){
		Author result = new Author();
		for (Author author : authorData) {
			if(author.authorid.compareTo(authorid) == 0){
				return author;
			}
		}
		return result;
	}
	
	public void delete(String isbn){
		String url = "jdbc:mysql://localhost:3306/bookdb";
		String user = "root";
		String password = "apple610"; 
		String driveName = "com.mysql.jdbc.Driver";
		
		//System.out.println("begin delete");
		
		try{
			Class.forName(driveName);
			//System.out.println("loading database successfully!");
			//System.out.println("=============================================================");
		}catch(java.lang.ClassNotFoundException e){
			System.out.println("loading database error!");
			System.out.println(e.getMessage());
		}
		
		try {
			// 1. Get connection to a database
			Connection myConn = DriverManager.getConnection(url, user, password);
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			//System.out.println(isbn);
			myStmt.executeUpdate("delete from book where isbn = " + "'" + isbn + "'");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println("end delete");
	}
	
}

class Book{
	public String isbn;
	public String title;
	public String authorid;
	public String publishdate;
	public String publisher;
	public String price;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorid() {
		return authorid;
	}

	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
    
}

class Author{
	public String authorid;
	public String name;
	public String age;
	public String country;
	
	public String getAuthorid() {
		return authorid;
	}
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
