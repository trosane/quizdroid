package edu.washington.trosane.quizdroid;

import java.util.List;

public interface TopicRepository {

    public String getTitle();

    public void setTitle(String a);

    public List<Question> getQuestion();

    public void setQuestion(List<Question> a);

}