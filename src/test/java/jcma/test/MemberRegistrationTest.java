package jcma.test;

import jcma.controller.MemberRegistration;
import jcma.data.MemberListProducer;
import jcma.model.Member;
import jcma.model.Member_;
import jcma.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

@RunWith(Arquillian.class)
public class MemberRegistrationTest {
// ------------------------------ FIELDS ------------------------------

    @Inject
    Logger log;

    @Inject
    MemberListProducer memberListProducer;

    @Inject
    MemberRegistration memberRegistration;

// -------------------------- STATIC METHODS --------------------------

    @Deployment
    public static Archive<?> createTestArchive()
    {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(Member.class, Member_.class, MemberRegistration.class, MemberListProducer.class, Resources.class)
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

// -------------------------- OTHER METHODS --------------------------

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint)
    {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass());
    }

    @Test
    public void testRegister() throws Exception
    {
        int memberListSizeBeforeTest = memberListProducer.getMembers().size();
        Member newMember = memberRegistration.getNewMember();
        newMember.setName("Ala Bezkota");
        newMember.setEmail("ala@bezkota.pl");
        newMember.setPhoneNumber("1234567890");

        memberRegistration.register();

        assertNotSame(memberListSizeBeforeTest, memberListProducer.getMembers().size());
        assertNotNull(newMember.getId());
    }

    @Test
    public void testRegisterEventHandler() throws Exception
    {
        Member newMember = memberRegistration.getNewMember();
        newMember.setName("Ala Exception");
        newMember.setEmail("ala@kot.pl");
        newMember.setPhoneNumber("1234567809");

        memberRegistration.register();

        assertNotNull(newMember.getId());
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    public void testRegisterWithTooShortNumber() throws Exception
    {
        Member newMember = memberRegistration.getNewMember();
        newMember.setName("Ala Exception");
        newMember.setEmail("ala@exception.pl");
        newMember.setPhoneNumber("12345");

        memberRegistration.register();
    }
}
