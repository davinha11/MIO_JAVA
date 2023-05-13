package com.davena.exercises.Fourth_lesson;

public abstract class AbstractShape implements MyShape {
    String id;

    public AbstractShape(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
