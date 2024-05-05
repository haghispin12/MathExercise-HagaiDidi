package com.example.mathexercise_hagaididi;

public class mission {
    private String name;
    private int percentage;
    private long id;
    private String priority;

    public mission(String name, int percentage, String priority, long id) {
        this.name = name;
        this.percentage = percentage;
        this.priority=priority;
        this.id=id;
    }

    public mission(String name, int percentage, String priority) {
        this.name = name;
        this.percentage = percentage;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
