package jcma.test;

import jcma.controller.MemberRegistration;
import jcma.data.MemberListProducer;
import jcma.model.Member;
import jcma.model.Member_;
import jcma.util.Resources;
import org.jboss.arquillian.ajocado.framework.GrapheneSelenium;
import org.jboss.arquillian.ajocado.geometry.Point;
import org.jboss.arquillian.ajocado.locator.element.ElementLocator;
import org.jboss.arquillian.ajocado.utils.URLUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.io.File;
import java.net.URL;

import static org.jboss.arquillian.ajocado.Graphene.guardHttp;
import static org.jboss.arquillian.ajocado.Graphene.guardXhr;
import static org.jboss.arquillian.ajocado.Graphene.id;

@RunWith(Arquillian.class)
public class RegistrationFormTest {

    // ------------------------------ FIELDS ------------------------------
    @Inject
    Logger log;

    @ArquillianResource
    private URL deploymentURL;

    @Drone
    private GrapheneSelenium grapheneSelenium;

// -------------------------- STATIC METHODS --------------------------

    @Deployment
    public static Archive<?> createTestArchive()
    {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(Member.class, Member_.class, MemberRegistration.class, MemberListProducer.class, Resources.class)
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            .addPackages(true, "org.jboss.arquillian.ajocado")
            .addAsWebResource(new File("src/main/webapp/index.xhtml"), ArchivePaths.create("index.xhtml"))
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
        grapheneSelenium.open(URLUtils.buildUrl(deploymentURL, "index"));
        grapheneSelenium.setSpeed(500);

        click(id("register"));
    }

    private void ajaxClick(ElementLocator<?> clickTarget)
    {
        guardXhr(grapheneSelenium).click(clickTarget);
    }

    private void ajaxClickAt(ElementLocator<?> clickTarget, Point coords)
    {
        guardXhr(grapheneSelenium).clickAt(clickTarget, coords);
    }

    private void click(ElementLocator<?> clickTarget)
    {
        guardHttp(grapheneSelenium).click(clickTarget);
    }

    private void insertInto(ElementLocator<?> locator, String value)
    {
        grapheneSelenium.type(locator, value);
    }
}
