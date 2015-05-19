package edu.washington.trosane.quizdroid;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Overview extends ActionBarActivity {
    public static int currentQuestion;
    public static int correctAnswer;
    public static Intent launchedMe;
    public static String answer;
    public static int numcorrect;
    public static int totalQuestions;
    public static String chosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_activity);

        launchedMe = getIntent();
        int number =  launchedMe.getIntExtra("title", 100);
        QuizApp quizzer = QuizApp.getInstance();
        quizzer.setCurrentTopic(number);
        totalQuestions = quizzer.getQuestion().size();
        currentQuestion = quizzer.getCurrentQuestion();
        Log.i("current Question1", ""+currentQuestion);
        Log.i("current Question2", ""+quizzer.getCurrentQuestion());

        TextView title1 = (TextView) findViewById(R.id.textView);
        title1.setText(quizzer.getTitle());
        TextView numberq = (TextView) findViewById(R.id.textView8);
        numberq.setText("Question(s) in Topic:" + totalQuestions);

        //button to next frag
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v.findViewById(R.id.button);
                b.setVisibility(v.INVISIBLE);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.topicid, new Question());
                ft.commit();
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
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
            Intent nextActivity = new Intent(Overview.this, Preferences.class);

            //send intent
            if(nextActivity.resolveActivity(getPackageManager()) != null) {
                startActivity(nextActivity);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class Question extends Fragment {
        public View view;
        public QuizApp quizzer;

        public Question() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_question_activity, container, false);
            quizzer = QuizApp.getInstance();

            correctAnswer = quizzer.getQuestion().get(currentQuestion).getCorr();
            answer = quizzer.getQuestion().get(currentQuestion).getAnswers().get(correctAnswer);

            TextView question = (TextView) view.findViewById(R.id.textView6);
            question.setText(quizzer.getQuestion().get(currentQuestion).getQuestion());
            Log.i("question", quizzer.getQuestion().get(currentQuestion).getQuestion());
            TextView num = (TextView) view.findViewById(R.id.textView7);
            currentQuestion++;
            num.setText("Question " + currentQuestion);
            currentQuestion--;

            //Radio group set text
            RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
            int rad = radioGroup.getChildCount();

            for (int i = 0; i <= rad - 1; i++) {
                View o = radioGroup.getChildAt(i);
                if (o instanceof RadioButton) {
                    RadioButton radioBtn =  (RadioButton)o;
                    radioBtn.setText(quizzer.getQuestion().get(currentQuestion).getAnswers().get(i));

                }
            }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    Button b = (Button) view.findViewById(R.id.button3);
                    b.setVisibility(view.VISIBLE);
                    RadioButton radio = (RadioButton) view.findViewById(checkedId);
                    chosen = radio.getText().toString();
                    Log.i("Chosen", chosen);
                    Log.i("correctAnser", answer);
                    Log.i("Beforeif numCorrect", ""+numcorrect);
                    if(chosen.equals(answer)) {
                        numcorrect++;
                    }
                    Log.i("Afterif numCorrect", ""+numcorrect);
                    currentQuestion++;
                    quizzer.setCurrentQuestion(currentQuestion);
                    currentQuestion--;
                }
            });

            Button btn = (Button) view.findViewById(R.id.button3);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button) v.findViewById(R.id.button3);
                    b.setVisibility(v.INVISIBLE);
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.question_id, new Summary());
                    ft.commit();
                }

            });

            return view;
        }

        public class Summary extends Fragment {
            public Button b;
            public View view;
            public QuizApp quizzer;

            public Summary() {

            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                view = inflater.inflate(R.layout.fragment_summary_activity, container, false);
                quizzer = QuizApp.getInstance();

                TextView ans = (TextView) view.findViewById(R.id.textView4);
                ans.setText("You chose: " + chosen);
                TextView numcorr = (TextView) view.findViewById(R.id.textView5);
                currentQuestion++;
                numcorr.setText(numcorrect + " out of " + currentQuestion + " correct");
                currentQuestion--;

                TextView rightnum = (TextView) view.findViewById(R.id.textView9);
                rightnum.setText("The correct answer is: " + answer);
                b = (Button) view.findViewById(R.id.button2);
                Log.i("currentQ ", ""+currentQuestion);
                currentQuestion++;
                if(currentQuestion == totalQuestions) {
                    b.setText("Finish");
                }

                b.setOnClickListener(new View.OnClickListener() {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();


                    @Override
                    public void onClick(View v) {
                        if(b.getText().toString().equals("Next")) {

                            Button b = (Button) v.findViewById(R.id.button2);
                            b.setVisibility(v.INVISIBLE);
                            ft.replace(R.id.summary_id, new Question());
                            ft.commit();
                        } else {
                            quizzer.setCurrentQuestion(0);
                            numcorrect = 0;
                            Intent nextActivity = new Intent(getActivity(), MainActivity.class);
                            startActivity(nextActivity); // opens a new activity
                        }
                    }
                });
                return view;
            }

        }
    }
}
