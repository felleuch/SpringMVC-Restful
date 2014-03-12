package com.faiez;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@XmlRootElement
public class BookWrapper implements Serializable {

    private List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public BookWrapper(List<Book> bookList) {
        this.bookList = bookList;
    }

    public BookWrapper() {
    }
}
