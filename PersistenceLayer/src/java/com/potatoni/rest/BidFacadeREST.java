/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.rest;

import com.potatoni.entity.Bid;
import com.potatoni.exception.ResourceCreateException;
import com.potatoni.exception.ResourceNotExistsException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
@Path("com.potatoni.entity.bid")
public class BidFacadeREST extends AbstractFacade<Bid> {
    @PersistenceContext(unitName = "DatabaseLayerPU")
    private EntityManager em;

    public BidFacadeREST() {
        super(Bid.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Bid entity)  {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Bid entity) {
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
    public Bid find(@PathParam("id") Integer id) {
        Bid bid = super.find(id);
        if (bid == null)
            throw new ResourceNotExistsException();
        return bid;
    }
    
    @GET
    @Path("/bookid/{bookId}")
    @Produces({"application/xml", "application/json"})
    public List<Bid> findByBookId(@PathParam("bookId") Integer bookId) {
        Query q = em.createQuery("SELECT b FROM Bid b WHERE b.bookId = :bookId");
        q.setParameter("bookId", bookId);
        return q.getResultList();
    }
    
    @GET
    @Path("/userid/{userId}")
    @Produces({"application/xml", "application/json"})
    public List<Bid> findByUserId(@PathParam("userId") Integer userId) {
        Query q = em.createQuery("SELECT b FROM Bid b WHERE b.userId = :userId");
        q.setParameter("userId", userId);
        return q.getResultList();
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Bid> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Bid> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
