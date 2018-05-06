package bookstore.service.impl;

import bookstore.dao.BookDao;
import bookstore.model.Book;
import bookstore.service.BookService;

import bookstore.util.SearchConnection;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void addBook(Book book) {
        bookDao.save(book);

        TransportClient client = SearchConnection.getClient();
        try {
            IndexResponse response = client.prepareIndex("bookstore", "book", book.getIsbn())
                .setSource(jsonBuilder()
                    .startObject()
                        .field("title", book.getTitle())
                        .field("author", book.getAuthor())
                        .field("isbn", book.getIsbn())
                        .field("publisher", book.getPublisher())
                        .field("published_date", book.getPublishedDate())
                        .field("category", book.getCategory())
                        .field("price", book.getPrice())
                        .field("inventory", book.getInventory())
                        .field("description", book.getDescription())
                    .endObject()
                )
                .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(Book book) {
        bookDao.delete(book);

        TransportClient client = SearchConnection.getClient();
        DeleteResponse response = client.prepareDelete("bookstore", "book", book.getIsbn()).get();
    }

    public void updateBook(Book book) {
        bookDao.update(book);

        TransportClient client = SearchConnection.getClient();
        try {
            client.prepareUpdate("bookstore", "book", book.getIsbn())
                .setDoc(jsonBuilder()
                    .startObject()
                    .field("title", book.getTitle())
                    .field("author", book.getAuthor())
                    .field("isbn", book.getIsbn())
                    .field("publisher", book.getPublisher())
                    .field("published_date", book.getPublishedDate())
                    .field("category", book.getCategory())
                    .field("price", book.getPrice())
                    .field("inventory", book.getInventory())
                    .field("description", book.getDescription())
                    .endObject()
                )
                .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Book getBookByISBN(String isbn) {
        return bookDao.getBookByISBN(isbn);
    }

    public List<Book> getBookByCategory(String category) {
        return bookDao.getBookByCategory(category);
    }

    public List<Book> getBookByKeyword(String keyword) {
        TransportClient client = SearchConnection.getClient();
        SearchResponse response = client.prepareSearch("bookstore")
            .setTypes("book")
            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
            .setQuery(QueryBuilders.termQuery("title", keyword))
            .setFrom(0).setSize(60).setExplain(true)
            .get();

        SearchHits searchHits = response.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String json = hit.getSourceAsString();
            System.out.print(json);
        }

        return bookDao.getBookByKeyword(keyword);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public List<String> getAllIsbns() {
        return bookDao.getAllIsbns();
    }

    public List<String> getAllCatagories() {
        return bookDao.getAllCategories();
    }

    public List<Object> getBookStat() {
        return bookDao.getBookStat();
    }


}
