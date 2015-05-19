package edu.washington.trosane.quizdroid;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuizApp extends Application implements TopicRepository {
    public static QuizApp instance;
    public List<Topic> list;
    public int currentTopic;
    public int currentQuestion;

    public QuizApp() {
        if(instance== null) {
            instance = this;
        }
    }

    public static void setInstance(){
        QuizApp.instance = instance;
    }

    public static QuizApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Start", "Quiz Loaded");
        setCurrentQuestion(0);
        list = new ArrayList<Topic>();

        Topic a = new Topic();
        a.setTitle("Math");

        //Questions
        ArrayList<Question> mathQuestions = getMath();
        a.setQuestion(mathQuestions);
        list.add(a);

        Topic b = new Topic();
        b.setTitle("Physics");

        //Questions
        ArrayList<Question> physicQuestions = getPhysics();
        b.setQuestion(physicQuestions);
        list.add(b);

        Topic c = new Topic();
        c.setTitle("Marvel Super Heroes");

        //Questions
        ArrayList<Question> marvelQuestions = getMarvel();
        c.setQuestion(marvelQuestions);
        list.add(c);
    }

    public ArrayList<Question> getMath() {
        ArrayList<Question> mathQuestions = new ArrayList<Question>();
        Question math1 = new Question();
        math1.setQuestion("What is the next prime number after 7?");
        math1.setAnswers("9", "11", "13", "17");
        math1.setCorr(1);
        mathQuestions.add(math1);
        Question math2 = new Question();
        math2.setQuestion("What is 6 squared?");
        math2.setAnswers("24", "36", "60", "12");
        math2.setCorr(1);
        mathQuestions.add(math2);
        Question math3 = new Question();
        math3.setQuestion("What is the remainder of 27/3?");
        math3.setAnswers("3", "1", "2", "0");
        math3.setCorr(3);
        mathQuestions.add(math3);
        return mathQuestions;
    }

    public ArrayList<Question> getPhysics() {
        ArrayList<Question> physicsQuestions = new ArrayList<Question>();
        Question physics1 = new Question();
        physics1.setQuestion("A magnifying glass is what type of lens?");
        physics1.setAnswers("concave",
                "concurrent",
                "convex",
                "convert");
        physics1.setCorr(2);
        physicsQuestions.add(physics1);
        Question physics2 = new Question();
        physics2.setQuestion("Electric resistance is typically measured in what units?");
        physics2.setAnswers("ohms", "joules", "degrees", "amps");
        physics2.setCorr(0);
        physicsQuestions.add(physics2);
        return physicsQuestions;
    }

    public ArrayList<Question> getMarvel() {
        ArrayList<Question> marvelQuestions = new ArrayList<Question>();
        Question marvel1 = new Question();
        marvel1.setQuestion("Which one is not a member of the Xmen?");
        marvel1.setAnswers("Spiderman",
                "Cyclops", "Jean Grey", "Gambit");
        marvel1.setCorr(0);
        marvelQuestions.add(marvel1);
        Question marvel2 = new Question();
        marvel2.setQuestion("S.H.I.E.L.D.'s highest ranking agent is...?");
        marvel2.setAnswers("Steven Rogers",
                "Peter Parker", "Natalia Romanova", "Nick Fury");
        marvel2.setCorr(3);
        marvelQuestions.add(marvel2);
        Question marvel3 = new Question();
        marvel3.setQuestion("The Fantastic Four have the headquarters in what building?");
        marvel3.setAnswers("Xavier Institute", "Baxter Building", "Fantastic Headquarters", "Stark Tower");
        marvel3.setCorr(1);
        marvelQuestions.add(marvel3);
        return marvelQuestions;
    }

    public void setCurrentQuestion(int question) {
        this.currentQuestion = question;
    }
    public int getCurrentQuestion() {
        return this.currentQuestion;
    }

    public void setCurrentTopic(int a) {
        this.currentTopic = a;
    }

    @Override
    public String getTitle() {
        return list.get(currentTopic).getTitle();
    }

    @Override
    public void setTitle(String a) {
        list.get(currentTopic).setTitle(a);
    }

    @Override
    public List<Question> getQuestion() {
        return list.get(currentTopic).getQuestion();
    }

    @Override
    public void setQuestion(List<Question> a) {

    }

    public String[] getTopic() {
        String t1 = list.get(0).getTitle();
        String t2 = list.get(1).getTitle();
        String t3 = list.get(2).getTitle();
        String[] topics = new String[] {t1, t2, t3};;
        return topics;
    }

}