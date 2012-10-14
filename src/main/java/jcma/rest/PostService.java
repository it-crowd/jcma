package jcma.rest;

import jcma.domain.Post;
import jcma.domain.User;
import jcma.post.PostDAO;
import jcma.user.UserDao;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/post")
public class PostService {
// ------------------------------ FIELDS ------------------------------

    @Inject
    private PostDAO postDAO;

    @Inject
    private UserDao userDao;

// -------------------------- OTHER METHODS --------------------------

    @Produces({"application/json", "application/xml"})
    @GET
    @Path("/add")
    public Post addPost(@QueryParam("title") String title, @QueryParam("content") String content)
    {
        final Post post = new Post(getCurrentUser(), content, title);
        postDAO.save(post);
        return post;
    }

    @Produces({"application/json", "application/xml"})
    @GET
    @Path("/{id}")
    public Post getPost(@PathParam("id") Long id)
    {
        return postDAO.getPostById(id);
    }

    @Produces({"application/json", "application/json-in-script"})
    @GET
    @Path("/list")
    public List<Post> getPosts()
    {
        return postDAO.getPosts();
    }

    @Produces({"application/json", "application/xml"})
    @GET
    @Path("/search")
    public List<Post> searchPosts(@QueryParam("query") String query)
    {
        return postDAO.search(query);
    }

    @Wrapped(element = "posts")
    @Produces("application/xml")
    @GET
    @Path("/list")
    public List<Post> getPostsAsXML()
    {
        return postDAO.getPosts();
    }

    @DELETE
    @Path("/{id}")
    public boolean removePost(@PathParam("id") Long id)
    {
        return postDAO.removePostById(id);
    }

    private User getCurrentUser()
    {
        return userDao.getUsers().get(0);
    }

    @GET
    @Wrapped(element = "posts")
    @Produces("application/xml")
    @Path("/range/{start}-{end}")
    public List<Post> getPostsInRange(@PathParam(value = "start") Integer start, @PathParam(value = "end") Integer end){
        return postDAO.getPostsRange(start-1, end);
    }

    @GET
    @Wrapped(element = "posts")
    @Produces("application/xml")
    @Path("/page/{pageNo}/{pageSize}")
    public List<Post> getPostPageAsXml(@PathParam(value = "pageNo") Integer pageNo,
                                  @PathParam(value = "pageSize") Integer pageSize){
        return postDAO.getPostPage(pageNo, pageSize);
    }

    @GET
    @Produces({"application/json", "application/json-in-script"})
    @Path("/page/{pageNo}/{pageSize}")
    public List<Post> getPostPage(@PathParam(value = "pageNo") Integer pageNo,
                                  @PathParam(value = "pageSize") Integer pageSize){

        return postDAO.getPostPage(pageNo, pageSize);
    }

    @GET
    @Wrapped(element="total")
    @Produces({"application/json", "application/json-in-script"})
    @Path("/totalNumber")
    public Long getPostCount(){
        return postDAO.getPostCount();
    }
}
