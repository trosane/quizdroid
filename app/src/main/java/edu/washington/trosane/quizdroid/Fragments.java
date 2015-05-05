package edu.washington.trosane.quizdroid;

import android.app.FragmentManager;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class Fragments extends ActionBarActivity {

    private static String topic;
    private static ArrayList<question> questions;
    private static ArrayList<question> mathQuestions;
    private static ArrayList<question> physicsQuestions;
    private static ArrayList<question> marvelQuestions;

    private static int count;
    private static int correctNum;
    private static String selectedAnswer;
    private static String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        if (savedInstanceState == null) {
            Intent launchedMe = getIntent();
            topic = launchedMe.getStringExtra("topic");
            count = launchedMe.getIntExtra("count", 0);
            correctNum = launchedMe.getIntExtra("correctNum", 0);

            question mathQ1 = new question("What is the next prime number after 7?",
                    new String[]{"9", "11", "13", "17"}, 1);
            question mathQ2 = new question("What is 6 squared?", new String[]{"24", "36", "60", "12"}, 1);
            question mathQ3 = new question("What is the remainder of 27/3?", new String[]{"3", "1", "2", "0"}, 3);
            mathQuestions = new ArrayList<question>();
            mathQuestions.add(mathQ1);
            mathQuestions.add(mathQ2);
            mathQuestions.add(mathQ3);

            question physicsQ1 = new question("A magnifying glass is what type of lens?",
                    new String[]{"concave",
                            "concurrent",
                            "convex",
                            "convert"}, 2);
            question physicsQ2 = new question("Electric resistance is typically measured in what units?",
                    new String[]{"ohms", "joules", "degrees", "amps"}, 0);

            physicsQuestions = new ArrayList<question>();
            physicsQuestions.add(physicsQ1);
            physicsQuestions.add(physicsQ2);


            question marvelQ1 = new question("Which one is not a member of the Xmen?", new String[]{"Spiderman",
                    "Cyclops", "Jean Grey", "Gambit"}, 0);
            question marvelQ2 = new question("S.H.I.E.L.D.'s highest ranking agent is...?", new String[]{"Steven Rogers",
                    "Peter Parker", "Natalia Romanova", "Nick Fury"}, 3);
            question marvelQ3 = new question("The Fantastic Four have the headquarters in what building?",
                    new String[]{"Xavier Institute", "Baxter Building", "Fantastic Headquarters", "Stark Tower"}, 1);

            marvelQuestions = new ArrayList<question>();
            marvelQuestions.add(marvelQ1);
            marvelQuestions.add(marvelQ2);
            marvelQuestions.add(marvelQ3);


            getFragmentManager().beginTransaction()
                    .add(R.id.container, new topicOverviewFragment())
                    .commit();
        }
    }


    public static class topicOverviewFragment extends Fragment {

        public topicOverviewFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.overview_fragment, container, false);


            TextView title = (TextView) rootView.findViewById(R.id.topic);
            TextView description = (TextView) rootView.findViewById(R.id.description);
            Button startBtn = (Button) rootView.findViewById(R.id.begin);
            TextView numQuestions = (TextView) rootView.findViewById(R.id.numQ);

            title.setText(topic);
            if (topic.equals("Math")) {
                title.setText("Math");
                description.setText("Find out how mathematical you are.");
                numQuestions.setText("Questions: " + String.valueOf(mathQuestions.size()));
                questions = mathQuestions;

            } else if (topic.equals("Physics")) {
                title.setText("Physics");
                description.setText("Newton did it, so can you.");
                numQuestions.setText("Questions: " + String.valueOf(physicsQuestions.size()));
                questions = physicsQuestions;

            } else if (topic.equals("Marvel Super Heroes")) {
                title.setText("Marvel Super Heroes");
                description.setText("How many comics have you read?");
                numQuestions.setText("Questions: " + String.valueOf(marvelQuestions.size()));
                questions = marvelQuestions;

            }

            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new questionFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                }
            });


            return rootView;
        }
    }


    public static class questionFragment extends Fragment {

        public questionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.quiz_fragment, container, false);

            TextView question = (TextView) rootView.findViewById(R.id.question);
            final Button nextBtn = (Button) rootView.findViewById(R.id.submit);
            final RadioGroup qGroup = (RadioGroup) rootView.findViewById(R.id.answerGroup);
            RadioButton btn1 = (RadioButton) rootView.findViewById(R.id.answer0);
            RadioButton btn2 = (RadioButton) rootView.findViewById(R.id.answer1);
            RadioButton btn3 = (RadioButton) rootView.findViewById(R.id.answer2);
            RadioButton btn4 = (RadioButton) rootView.findViewById(R.id.answer3);

            final question currentQuestion = questions.get(count);
            question.setText(currentQuestion.question);
            btn1.setText(currentQuestion.option[0]);
            btn2.setText(currentQuestion.option[1]);
            btn3.setText(currentQuestion.option[2]);
            btn4.setText(currentQuestion.option[3]);

            qGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    nextBtn.setVisibility(View.VISIBLE);
                }
            });

            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count++;
                    RadioButton userAns = (RadioButton) rootView.findViewById(qGroup.getCheckedRadioButtonId());
                    selectedAnswer = userAns.getText().toString();
                    answer = currentQuestion.option[currentQuestion.answer];
                    if (selectedAnswer.equals(answer))
                        correctNum++;

                    Fragment fragment = new questionResultsFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                }
            });

            return rootView;
        }
    }

    public static class questionResultsFragment extends Fragment {

        public questionResultsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.summary_fragment, container, false);

            TextView correctNumView = (TextView) rootView.findViewById(R.id.correctNum);
            TextView correctAnswerView = (TextView) rootView.findViewById(R.id.correctAnswer);
            TextView selectedAnswerView = (TextView) rootView.findViewById(R.id.selectedAnswer);

            correctNumView.setText(correctNum + " out of " + count + " correct");
            correctAnswerView.setText("The correct answer is: " + answer);
            selectedAnswerView.setText("You chose: " + selectedAnswer);


            Button nextBtn = (Button) rootView.findViewById(R.id.next);
            if (count == questions.size()) {
                nextBtn.setText("Finish");
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                });
            } else {
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        System.out.println(count);
                        Fragment fragment = new questionFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment)
                                .commit();
                    }
                });
            }
            return rootView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}