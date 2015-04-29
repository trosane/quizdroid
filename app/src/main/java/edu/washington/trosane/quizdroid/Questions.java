package edu.washington.trosane.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;


public class Questions extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity);

        Intent launched = getIntent();
        final String topic = launched.getStringExtra("topic");

        TextView title = (TextView) findViewById(R.id.topic);
        TextView description = (TextView) findViewById(R.id.description);
        TextView numQuestions = (TextView) findViewById(R.id.numQ);
        Button begin = (Button) findViewById(R.id.begin);

        question mathQ1 = new question("What is the next prime number after 7?",
                new String[]{"9", "11", "13", "17"}, 1);
        question mathQ2 = new question("What is 6 squared?", new String[]{"24", "36", "60", "12"}, 1);
        question mathQ3 = new question("What is the remainder of 27/3?", new String[]{"3", "1", "2", "0"}, 3);
        final ArrayList<question> mathQuestions = new ArrayList<question>();
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

        final ArrayList<question> physicsQuestions = new ArrayList<question>();
        physicsQuestions.add(physicsQ1);
        physicsQuestions.add(physicsQ2);


        question marvelQ1 = new question("Which one is not a member of the Xmen?", new String[]{"Spiderman",
                "Cyclops", "Jean Grey", "Gambit"}, 0);
        question marvelQ2 = new question("S.H.I.E.L.D.'s highest ranking agent is...?", new String[]{"Steven Rogers",
                "Peter Parker", "Natalia Romanova", "Nick Fury"}, 3);
        question marvelQ3 = new question("The Fantastic Four have the headquarters in what building?",
                new String[]{"Xavier Institute", "Baxter Building", "Fantastic Headquarters", "Stark Tower"}, 1);

        final ArrayList<question> marvelQuestions = new ArrayList<question>();
        marvelQuestions.add(marvelQ1);
        marvelQuestions.add(marvelQ2);
        marvelQuestions.add(marvelQ3);

        if(topic.equals("Math")){
            title.setText("Math");
            description.setText("Find out how mathematical you are.");
            numQuestions.setText("Questions: " + String.valueOf(mathQuestions.size()));

        }else if(topic.equals("Physics")){
            title.setText("Physics");
            description.setText("Newton did it, so can you.");
            numQuestions.setText("Questions: " + String.valueOf(physicsQuestions.size()));

        }else if(topic.equals("Marvel Super Heroes")){
            title.setText("Marvel Super Heroes");
            description.setText("How many comics have you read?");
            numQuestions.setText("Questions: " + String.valueOf(marvelQuestions.size()));

        }

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(Questions.this, Quiz.class);

                Bundle questionBundle = new Bundle();
                if(topic.equals("Math")){

                    questionBundle.putSerializable("serializedQuestions",mathQuestions);

                }else if(topic.equals("Physics")){

                    questionBundle.putSerializable("serializedQuestions",physicsQuestions);


                }else if(topic.equals("Marvel Super Heroes")){

                    questionBundle.putSerializable("serializedQuestions",marvelQuestions);

                }

                quiz.putExtra("questions", questionBundle);
                quiz.putExtra("count",0);
                quiz.putExtra("topic", topic);
                startActivity(quiz);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_topic_overview, menu);
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
