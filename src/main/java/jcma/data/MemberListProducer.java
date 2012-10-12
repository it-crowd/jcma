package jcma.data;

import jcma.model.Member;
import jcma.model.Member_;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@RequestScoped
public class MemberListProducer {
// ------------------------------ FIELDS ------------------------------

   @Inject
   private EntityManager em;

   private List<Member> members;

// --------------------- GETTER / SETTER METHODS ---------------------

   @Produces
   @Named
   public List<Member> getMembers() {
      return members;
   }

// -------------------------- OTHER METHODS --------------------------

   public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Member member) {
      retrieveAllMembersOrderedByName();
   }

   @PostConstruct
   public void retrieveAllMembersOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
      Root<Member> member = criteria.from(Member.class);
      criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
      members = em.createQuery(criteria).getResultList();
   }
}
