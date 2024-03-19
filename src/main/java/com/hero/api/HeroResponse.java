package com.hero.api;

public class HeroResponse {
    private int id;
    private String codeName;
    private String realName;
    private String alignment;

    // Constructors
    public HeroResponse() {
    }

    public HeroResponse(int id, String codeName, String realName, String alignment) {
        this.id = id;
        this.codeName = codeName;
        this.realName = realName;
        this.alignment = alignment;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
}
