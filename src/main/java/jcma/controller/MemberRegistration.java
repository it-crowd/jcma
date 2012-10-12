package jcma.controller;

import jcma.model.Member;
import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Stateful
@Model
public class MemberRegistration {
// ------------------------------ FIELDS ------------------------------

    @SuppressWarnings("unused")
    @Inject
    private EntityManager em;

    @SuppressWarnings({"unused", "CdiInjectionPointsInspection"})
    @Inject
    @Category("jcma-arq")
    private Logger log;

    @SuppressWarnings("unused")
    @Inject
    private Event<Member> memberEventSrc;

    private Member newMember;

// --------------------- GETTER / SETTER METHODS ---------------------

    @Produces
    @Named
    public Member getNewMember()
    {
        return newMember;
    }

// -------------------------- OTHER METHODS --------------------------

    @PostConstruct
    public void initNewMember()
    {
        newMember = new Member();
    }

    public void register() throws Exception
    {
        log.info("Registering " + newMember.getName());
        em.persist(newMember);
        memberEventSrc.fire(newMember);
        initNewMember();
    }
}
