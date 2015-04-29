package edu.washington.trosane.quizdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class Quiz extends ActionBarActivity {

    private Bundle questionBundle;
    private int count;
    private int numCorrect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        Intent launched = getIntent();
        questionBundle = launched.getBundleExtra("questions");
        count = launched.getIntExtra("count",0);
        numCorrect = launched.getIntExtra("numCorrect",0);
        ArrayList<question> questions = (ArrayList)questionBundle.getSerializable("serializedQuestions");

        final String topic = launched.getStringExtra("topic");
        System.out.println(topic);
        final question thisQuestion = questions.get(count);

        TextView question = (TextView) findViewById(R.id.question);
        question.setText(thisQuestion.question);

        TextView answer0 = (TextView) findViewById(R.id.answer0);
        answer0.setText(thisQuestion.option[0]);

        TextView answer1 = (TextView) findViewById(R.id.answer1);
        answer1.setText(thisQuestion.option[1]);

        TextView answer2 = (TextView) findViewById(R.id.answer2);
        answer2.setText(thisQuestion.option[2]);


        TextView answer3 = (TextView) findViewById(R.id.answer3);
        answer3.setText(thisQuestion.option[3]);

        final Button submit = (Button) findViewById(R.id.submit);

        final RadioGroup answerGroup = (RadioGroup) findViewById(R.id.answerGroup);

        answerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton userAnswer = (RadioButton) findViewById(answerGroup.getCheckedRadioButtonId());
                String selectedAnswer = userAnswer.getText().toString();
                Intent answerSummary = new Intent(Quiz.this, Summary.class);
                answerSummary.putExtra("topic", topic);
                answerSummary.putExtra("selectedAnswer", selectedAnswer);
                answerSummary.putExtra("correctAnswer", thisQuestion.option[thisQuestion.answer]);
                answerSummary.putExtra("count",count+1);
                answerSummary.putExtra("numCorrect", numCorrect);
                answerSummary.putExtra("questions",questionBundle);
                startActivity(answerSummary);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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