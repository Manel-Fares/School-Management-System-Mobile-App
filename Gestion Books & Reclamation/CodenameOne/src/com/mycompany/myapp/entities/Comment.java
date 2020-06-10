/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;


/**
 *
 * @author Neifos
 */
public class Comment {
    
    private String id ;
    private int thread_id;
    private User author;
    private String body;
    private int ancestors;
    private int depth;
    private String created_at;
    private int state;

    public Comment() {
    }

    public Comment(int thread_id, User author, String body, String created_at) {
        this.thread_id = thread_id;
        this.author = author;
        this.body = body;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getThread_id() {
        return thread_id;
    }

    public void setThread_id(int thread_id) {
        this.thread_id = thread_id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getAncestors() {
        return ancestors;
    }

    public void setAncestors(int ancestors) {
        this.ancestors = ancestors;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", thread_id=" + thread_id + ", author=" + author + ", body=" + body + ", ancestors=" + ancestors + ", depth=" + depth + ", created_at=" + created_at + ", state=" + state + '}';
    }
    
}
