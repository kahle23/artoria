package com.apyhs.artoria.entity;

public class Student extends Person {

    private static final Integer DEFAULT_SCORE = 0;

    private Integer score;
    private String email;

    public Student() {
    }

    protected Student(Integer score, String email) {
        this.score = score;
        this.email = email;
    }

    private Student(String tmp) {

    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", height=" + getHeight() +
                ", score=" + score +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public void fly() {
        System.out.println("fly");
    }
}
