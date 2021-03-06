/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.rest;

import com.potatoni.entity.Book;
import com.potatoni.exception.ResourceNotExistsException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author LinFan
 */
@Stateless
@Path("com.potatoni.entity.book")
public class BookFacadeREST extends AbstractFacade<Book> {
    @PersistenceContext(unitName = "DatabaseLayerPU")
    private EntityManager em;

    public BookFacadeREST() {
        super(Book.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Book entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Book entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Book find(@PathParam("id") Integer id) {
        Book book = super.find(id);
        if (book == null)
            throw new ResourceNotExistsException();
        return book;
    }
    
    @GET
    @Path("/name/{name}")
    @Produces({"application/xml", "application/json"})
    public List<Book> findByName(@PathParam("name") String name) {
        Query query = em.createQuery("SELECT b FROM Book b, Bookinfo info WHERE b.isbn = info.isbn and info.title like :name");
        query.setParameter("name", "%" + name.toLowerCase() + "%");
        return query.getResultList();
    }
    
    @GET
    @Path("/isbn/{isbn}")
    @Produces({"application/xml", "application/json"})
    public List<Book> findByIsbn(@PathParam("isbn") String isbn) {
        Query query = em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn and b.soldDate = NULL");
        query.setParameter("isbn", isbn);
        return query.getResultList();
    }
    
    @GET
    @Path("/ownerid/{ownerId}")
    @Produces({"application/xml", "application/json"})
    public List<Book> findByOwnerId(@PathParam("ownerId") Integer ownerId) {
        Query query = em.createQuery("SELECT b FROM Book b WHERE b.ownerId = :ownerId");
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }
    
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Book> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Book> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
