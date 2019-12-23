package com.ss.lms.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.dao.*;
import com.ss.lms.entity.*;

public class AdminService extends BaseService {
	/*
	 * TODO: 12/23/2019
	 */
	ConnectionUtil connUtil = new ConnectionUtil();

	/*
	 * READ METHODS:
	 */
	public List<Author> readAuthors() throws SQLException {
		Connection conn = null;
		List<Author> authors = new ArrayList<Author>();
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			authors = adao.readAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading authors faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return authors;
	}

	public List<Book> readBooks() throws SQLException {
		Connection conn = null;
		List<Book> books = new ArrayList<Book>();
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			books = bdao.readBooks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return books;
	}

	public List<BookCopies> readBookCopies() throws SQLException {
		Connection conn = null;
		List<BookCopies> bcs = new ArrayList<BookCopies>();
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			bcs = bcdao.readBookCopies();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return bcs;
	}

	public List<BookLoans> readBookLoans() throws SQLException {
		Connection conn = null;
		List<BookLoans> bls = new ArrayList<BookLoans>();
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bls = bldao.readBookLoans();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return bls;
	}

	public List<Borrower> readBorrowers() throws SQLException {
		Connection conn = null;
		List<Borrower> borrowers = new ArrayList<Borrower>();
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			borrowers = bdao.readBorrowers();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading authors faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return borrowers;
	}

	public List<Branch> readBranches() throws SQLException {
		Connection conn = null;
		List<Branch> branches = new ArrayList<Branch>();
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			branches = bdao.readBranches();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading books faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return branches;
	}

	public List<Genre> readGenres() throws SQLException {
		Connection conn = null;
		List<Genre> genres = new ArrayList<Genre>();
		try {
			conn = connUtil.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			genres = gdao.readGenres();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading genres faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return genres;
	}

	public List<Publisher> readPublishers() throws SQLException {
		Connection conn = null;
		List<Publisher> ps = new ArrayList<Publisher>();
		try {
			conn = connUtil.getConnection();
			PublisherDAO dao = new PublisherDAO(conn);
			ps = dao.readPublishers();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Reading publishers faiiled");
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return ps;
	}

	/*
	 * ADD METHODS:
	 */

	public String addAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			Integer authorId = adao.saveAuthorWithId(author);
			if (author.getBooks() != null) {
				for (Book b : author.getBooks())
					adao.insertBookAuthors(b.getBookId(), authorId);
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Adding Author Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Author Added Successfully";
	}

	public String addBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			Integer bookId = bdao.saveBookWithId(book);
			if (book.getAuthors() != null) {
				for (Author a : book.getAuthors())
					bdao.insertBookAuthors(bookId, a.getAuthorId());
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Adding Book Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Book Added Successfully";
	}

	public String addBookCopies(BookCopies bc) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			Integer bookId = bcdao.saveBookCopiesWithId(bc);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Adding Book Copies Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Book Copy Added Successfully";
	}

	public String addBookLoans(BookLoans bl) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			Integer bookId = bldao.saveBookLoansWithId(bl);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Adding Book Loans Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Book Loan Added Successfully";
	}

	public String addBorrower(Borrower b) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			Integer bookId = bdao.saveBorrowerWithId(b);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Adding Borrower Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Borrower Added Successfully";
	}

	public String addBranch(Branch b) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			Integer bookId = bdao.saveBranchWithId(b);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Adding Library Branch Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Library Branch Added Successfully";
	}

	public String addGenre(Genre g) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO dao = new GenreDAO(conn);
			Integer bookId = dao.saveGenreWithId(g);
			if (g.getBooks() != null) {
				for (Book b : g.getBooks())
					dao.insertBookGenre(g.getGenreId(), b.getBookId());
			}
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Adding Genre Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Genre Added Successfully";
	}

	public String addPublisher(Publisher p) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO dao = new PublisherDAO(conn);
			Integer bookId = dao.savePublisherWithId(p);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Adding Publisher Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Publisher Added Successfully";
	}

	/*
	 * UPDATE METHODS:
	 */
	public String editAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.editAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Editing Author Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Edit Author Successfully";
	}

	public String editBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO dao = new BookDAO(conn);
			dao.editBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Editing Book Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Book Edit Success";
	}

	public String editBookCopies(BookCopies bc) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO dao = new BookCopiesDAO(conn);
			dao.editBookCopies(bc);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Editing Book Copies Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Book Copies Edit Success";
	}

	public String editBookLoans(BookLoans bl) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO dao = new BookLoansDAO(conn);
			dao.editBookLoans(bl);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Editing Book Loans Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Book Loans Edit Success";
	}

	public String editBorrower(Borrower b) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO dao = new BorrowerDAO(conn);
			dao.editBorrower(b);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Editing Borrower Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Borrower Edit Success";
	}

	public String editBranch(Branch b) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO dao = new BranchDAO(conn);
			dao.editBranch(b);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Editing Branch Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Branch Edit Success";
	}

	public String editGenre(Genre g) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO dao = new GenreDAO(conn);
			dao.editGenre(g);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Editing Genre Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Genre Edit Success";
	}

	public String editPublisher(Publisher p) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO dao = new PublisherDAO(conn);
			dao.editPublisher(p);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Editing publisher Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Publisher Edit Success";
	}

	/*
	 * DELETE METHODS:
	 */
	public String deleteAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(author);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Deleting Author Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Deleted Author";
	}

	public String deleteBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO dao = new BookDAO(conn);
			dao.deleteBook(book);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Delete Book Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Book Delete Success";
	}

	public String deleteBookCopies(BookCopies bc) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO dao = new BookCopiesDAO(conn);
			dao.deleteBookCopies(bc);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Delete Book Copies Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Book Copies Delete Success";
	}

	public String deleteBookLoans(BookLoans bl) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO dao = new BookLoansDAO(conn);
			dao.deleteBookLoans(bl);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Delete Book Loans Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Book Loans Delete Success";
	}

	public String deleteBorrower(Borrower b) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO dao = new BorrowerDAO(conn);
			dao.deleteBorrower(b);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Delete Borrower Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Borrower Delete Success";
	}

	public String deleteBranch(Branch b) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO dao = new BranchDAO(conn);
			dao.deleteBranch(b);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Delete Branch Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Branch Delete Success";
	}

	public String deleteGenre(Genre g) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO dao = new GenreDAO(conn);
			dao.deleteGenre(g);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Delete Genre Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Genre Delete Success";
	}

	public String deletePublisher(Publisher p) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO dao = new PublisherDAO(conn);
			dao.deletePublisher(p);
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Deleting publisher Failed");
			if (conn != null) {
				conn.rollback();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return "Publisher Delete Success";
	}
}
