/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.potatoni.rest;

import com.potatoni.entity.YbandmbSession;
import com.potatoni.exception.ResourceNotExistsException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("com.potatoni.entity.ybandmbsession")
public class YbandmbSessionFacadeREST extends AbstractFacade<YbandmbSession> {
    @PersistenceContext(unitName = "DatabaseLayerPU")
    private EntityManager em;

    public YbandmbSessionFacadeREST() {
        super(YbandmbSession.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(YbandmbSession entity) {
        super.create(entity);
        System.out.println(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, YbandmbSession entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public YbandmbSession find(@PathParam("id") String id) {
        YbandmbSession session = super.find(id);
        if (session == null) {
            throw new ResourceNotExistsException();
        } else {
            return session;
        }
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<YbandmbSession> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<YbandmbSession> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
