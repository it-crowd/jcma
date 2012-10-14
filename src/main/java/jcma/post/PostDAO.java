package jcma.post;

import jcma.domain.Post;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PostDAO {
// ------------------------------ FIELDS ------------------------------

    @PersistenceContext(unitName = "jcma")
    private EntityManager entityManager;

// -------------------------- OTHER METHODS --------------------------

    public Post getPostById(Long id)
    {
        return (Post) entityManager.createQuery("select p from Post p where p.id=:id").setParameter("id", id).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPosts()
    {
        return entityManager.createQuery("select p from Post p").getResultList();
    }

    public boolean removePostById(Long id)
    {
        return 0 < entityManager.createQuery("delete Post where id=:id").setParameter("id", id).executeUpdate();
    }

    public void save(Post post)
    {
        entityManager.merge(post);
    }

    public List<Post> search(String query)
    {
        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        final Query fullTextQuery = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder()
            .forEntity(Post.class)
            .get()
            .phrase()
            .onField("content")
            .sentence(query)
            .createQuery();
        return fullTextEntityManager.createFullTextQuery(fullTextQuery, Post.class).getResultList();
    }

    public List<Post> getPostsRange(Integer start, Integer end) {
        return (List<Post>) entityManager.createQuery("SELECT p FROM Post p").setFirstResult(start).setMaxResults(end-start).getResultList();
    }

    public Long getPostCount() {
        return (Long) entityManager.createQuery("SELECT count(p) FROM Post p").getSingleResult();
    }

    public List<Post> getPostPage(Integer pageNo, Integer pageSize){
        Integer start = pageSize * (pageNo - 1);
        Integer end = start + pageSize;
        return this.getPostsRange(start, end);
    }
}
