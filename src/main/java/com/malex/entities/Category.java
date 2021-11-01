package com.malex.entities;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return id == category1.id &&
                Objects.equals(name, category1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
