package edu.washington.trosane.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Topic implements Serializable{
    private String title;
    private List<Question> question;
    public Topic() {


    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String a) {
        this.title = a;
    }

    public List<Question> getQuestion() {
        return this.question;
    }

    public void setQuestion(ArrayList<Question> b ) {
        this.question = b;
    }
}