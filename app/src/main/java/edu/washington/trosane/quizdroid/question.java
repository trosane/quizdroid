package edu.washington.trosane.quizdroid;

import java.io.Serializable;

public class question implements Serializable {
    String question;
    String[] option;
    int answer;

    public question(String question, String[] option, int answer){
        this.question = question;
        this.option = option;
        this.answer = answer;
    }
}
