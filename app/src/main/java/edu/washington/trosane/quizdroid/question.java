package edu.washington.trosane.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;

class Question implements Serializable {
    private String question;
    private ArrayList<String> allanswers;
    private int correct;

    public Question() {

    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String a) {
        this.question = a;
    }

    public ArrayList<String> getAnswers() {
        return this.allanswers;
    }

    public void setAnswers(String a, String b, String c, String d) {
        ArrayList<String> answers = new ArrayList<String>();
        answers.add(a);
        answers.add(b);
        answers.add(c);
        answers.add(d);
        this.allanswers = answers;

    }

    public int getSize() {
        return this.allanswers.size();
    }
    public int getCorr() {
        return this.correct;
    }

    public void setCorr(int a) {
        this.correct = a;
    }
}