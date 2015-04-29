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


public class Summary extends ActionBarActivity {

    private String topic;
    private int count;
    private Bundle questionBundle;
    private int numCorrect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_activity);
        Intent launched = getIntent();

        topic = launched.getStringExtra("topic");
        String correctAnswer = launched.getStringExtra("correctAnswer");
        String selectedAnswer = launched.getStringExtra("selectedAnswer");
        count = launched.getIntExtra("count",0);
        numCorrect = launched.getIntExtra("numCorrect", 0);
        questionBundle = launched.getBundleExtra("questions");
        final ArrayList<question> questions = (ArrayList)questionBundle.getSerializable("serializedQuestions");

        TextView correctAnswerView = (TextView)findViewById(R.id.correctAnswer);
        correctAnswerView.setText("The correct answer is: " + correctAnswer);

        TextView selectedAnswerView = (TextView)findViewById(R.id.selectedAnswer);
        selectedAnswerView.setText("You chose: " + selectedAnswer);

        if(selectedAnswer.equals(correctAnswer)){
            numCorrect++;
        }

        TextView correctNumber = (TextView) findViewById(R.id.correctNum);
        correctNumber.setText(numCorrect + " out of " + count + " correct");
        Button next = (Button) findViewById(R.id.next);
        if(count == questions.size()){
            next.setText("Finish");
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == questions.size()){
                    Intent topicList = new Intent(Summary.this, MainActivity.class);
                    startActivity(topicList);
                }else {
                    Intent quiz = new Intent(Summary.this, Quiz.class);
                    quiz.putExtra("topic", topic);

                    quiz.putExtra("count", count);
                    quiz.putExtra("numCorrect", numCorrect);
                    quiz.putExtra("questions", questionBundle);
                    startActivity(quiz);
                    finish();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer_summary, menu);
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
