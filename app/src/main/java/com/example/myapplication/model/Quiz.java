package com.example.myapplication.model;

public class Quiz {

    private Long id;
    private String title;

    public Quiz(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTittle(String title) {
        this.title = title;
    }

}
