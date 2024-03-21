/**
 * Represents a blog entity with its attributes and associations.
 */
package com.chstudebaker.herobase.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Blog")
public class Blog {

    // Unique identifier for the blog
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BlogId")
    private int blogId;

    // Title of the blog
    @Column(name = "BlogTitle")
    private String blogTitle;

    // Content of the blog
    @Column(name = "BlogContent")
    private String blogContent;

    // Date and time when the blog was created
    @Column(name = "DateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    // Hero associated with the blog
    @ManyToOne
    @JoinColumn(name = "HeroID")
    private Hero hero;

    // Default constructor
    public Blog() {
    }

    // Constructor with all fields
    public Blog(int blogId, String blogTitle, String blogContent, Date dateTime, Hero hero) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.dateTime = dateTime;
        this.hero = hero;
    }

    // Constructor with title and content fields
    public Blog(String blogTitle, String blogContent) {
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
    }

    // Getters and setters

    /**
     * Retrieves the blog ID.
     * @return The blog ID.
     */
    public int getBlogId() {
        return blogId;
    }

    /**
     * Sets the blog ID.
     * @param blogId The blog ID to set.
     */
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    /**
     * Retrieves the blog title.
     * @return The blog title.
     */
    public String getBlogTitle() {
        return blogTitle;
    }

    /**
     * Sets the blog title.
     * @param blogTitle The blog title to set.
     */
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    /**
     * Retrieves the blog content.
     * @return The blog content.
     */
    public String getBlogContent() {
        return blogContent;
    }

    /**
     * Sets the blog content.
     * @param blogContent The blog content to set.
     */
    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    /**
     * Retrieves the date and time when the blog was created.
     * @return The date and time of the blog creation.
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time of the blog creation.
     * @param dateTime The date and time to set.
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Retrieves the ID of the associated hero.
     * @return The hero ID.
     */
    public int getHeroID() {
        return hero != null ? hero.getHeroId() : 0;
    }

    /**
     * Sets the ID of the associated hero.
     * @param heroID The hero ID to set.
     */
    public void setHeroID(int heroID) {
        if (hero == null) {
            hero = new Hero();
        }
        hero.setHeroId(heroID);
    }

    /**
     * Retrieves the hero associated with the blog.
     * @return The hero associated with the blog.
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Sets the hero associated with the blog.
     * @param hero The hero to associate with the blog.
     */
    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
