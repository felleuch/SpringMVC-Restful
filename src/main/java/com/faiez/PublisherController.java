package com.faiez;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Transactional(readOnly = true)
@Repository
@Controller
@RequestMapping(value = "/publisher/*")
public class PublisherController {

    @Autowired
    private SessionFactory sessionFactory;

    public Session  getSession(){
        return sessionFactory.getCurrentSession();
    }

    @RequestMapping(value = "/book",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE },
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @Transactional(readOnly = false)
    public @ResponseBody Long saveBook(@RequestBody Book book) {
    	return (Long)getSession().save(book);
    }
    @RequestMapping(value = "/book/{id}",method = RequestMethod.DELETE)
    @Transactional(readOnly = false)
    public void removeBook(@PathVariable Long id) {
      getSession().delete(getSession().get(Book.class,id));
    }

    @RequestMapping(value = "/books",produces = {MediaType.APPLICATION_XML_VALUE},method = RequestMethod.GET)
    @ResponseStatus( HttpStatus.OK )
    public @ResponseBody    BookWrapper allBooks() {
    	List<Book> bookList= (List<Book>)getSession().createQuery("SELECT b FROM Book b").list();
    	return new BookWrapper(bookList) ;
    }

    @RequestMapping(value = "/book",method = RequestMethod.PUT,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @Transactional(readOnly = false)
    public void updateBook(@RequestBody Book book) {
        getSession().saveOrUpdate(book);
    }
}
