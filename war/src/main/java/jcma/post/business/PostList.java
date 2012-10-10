package jcma.post.business;

import jcma.domain.Post;
import jcma.framework.business.EntityQuery;
import pl.com.it_crowd.seam.framework.conditions.DynamicParameter;
import pl.com.it_crowd.seam.framework.conditions.FreeCondition;

import java.io.Serializable;
import java.util.Arrays;

public class PostList extends EntityQuery<Post> implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private Criteria searchCriteria = new Criteria();

// --------------------------- CONSTRUCTORS ---------------------------

    public PostList()
    {
        setEjbql("select p from Post p");
        final FreeCondition nameCondition = new FreeCondition("lower(title) like lower(concat(", searchCriteria.titleBridge, ",'%'))");
        setConditions(Arrays.asList(nameCondition));
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Criteria getSearchCriteria()
    {
        return searchCriteria;
    }

// -------------------------- INNER CLASSES --------------------------

    public static class Criteria implements Serializable {
// ------------------------------ FIELDS ------------------------------

        private String title;

        private DynamicParameter<String> titleBridge = new DynamicParameter<String>() {
            @Override
            public String getValue()
            {
                return title;
            }
        };

// --------------------- GETTER / SETTER METHODS ---------------------

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }
    }
}
