package com.example.location.model;

import java.io.Serializable;
import java.util.List;

public class ParentImage implements Serializable {
    Integer id;
    String name;
    List<Imagen> imagens;

    public ParentImage() {
    }

    public ParentImage(Integer id, String name, List<Imagen> imagens) {
        this.id = id;
        this.name = name;
        this.imagens = imagens;
    }

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

    public List<Imagen> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagen> imagens) {
        this.imagens = imagens;
    }

    public void addImage(Imagen imagen) {
        this.imagens.add(imagen);
    }
}
