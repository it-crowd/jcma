package jcma.domain;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import pl.com.it_crowd.seam.framework.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "POST")
public class Post implements Serializable, Identifiable<Long> {
// ------------------------------ FIELDS ------------------------------

    @NotNull
    @ManyToOne(optional = false)
    @ForeignKey(name = "FK___POST___AUTHOR")
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private User author;

    @NotNull
    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Id
    @GeneratedValue(generator = "POST_ID_SEQUENCE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "POST_ID_SEQUENCE", sequenceName = "POST_ID_SEQUENCE", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Length(min = 1, max = 255)
    @Column(name = "TITLE", nullable = false, length = 255)
    private String title;

// --------------------- GETTER / SETTER METHODS ---------------------

    public User getAuthor()
    {
        return author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String name)
    {
        this.title = name;
    }

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }

        Post post = (Post) o;

        return !(id != null ? !id.equals(post.id) : post.id != null);
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
