package com.packagename.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity // This tells Hibernate to make a table out of this class
public class Pokemon {
    @Id
    private Integer id;
    private String name;
    private String spriteURL;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpriteURL(String spriteURL) {
        this.spriteURL = spriteURL;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpriteURL() {
        return spriteURL;
    }

    public String getType() {
        return type;
    }
}
