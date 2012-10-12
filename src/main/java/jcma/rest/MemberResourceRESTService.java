package jcma.rest;

import jcma.model.Member;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/members")
@RequestScoped
public class MemberResourceRESTService {
// ------------------------------ FIELDS ------------------------------

    @SuppressWarnings("unused")
    @Inject
    private EntityManager em;

// -------------------------- OTHER METHODS --------------------------

    @SuppressWarnings("unchecked")
    @GET
    @Produces("text/xml")
    public List<Member> listAllMembers()
    {
        return em.createQuery("select m from Member m order by m.name").getResultList();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces("text/xml")
    public Member lookupMemberById(@PathParam("id") long id)
    {
        return em.find(Member.class, id);
    }
}
