package com.example.aniquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.aniquiz.databinding.ActivityMainBinding;
import com.example.aniquiz.model.QuestionList;
import com.example.aniquiz.model.Questions;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aniquiz.viewmodel.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    QuizViewModel quizViewModel;
    List<Questions>questionsList;
    private List<RadioButton> radioButtons;

    static int result=0;
    static int totalquestion=0;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        result=0;
        totalquestion=0;

        radioButtons = new ArrayList<>();
        radioButtons.add(mainBinding.option1RadioButton);
        radioButtons.add(mainBinding.option2RadioButton);
        radioButtons.add(mainBinding.option3RadioButton);
        radioButtons.add(mainBinding.option4RadioButton);

        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        DisplayFirstQuestion();

        mainBinding.NextBtn.setOnClickListener(v -> {
            if (mainBinding.NextBtn.getText().equals("Finish")){
                Intent i = new Intent(MainActivity.this, Result_Activity.class);
                startActivity(i);
                finish();
            }
            //DisplayNextQuestion();
            if (isAnyRadioButtonSelected()) {
                // Check which radio button is selected
                RadioButton selectedRadioButton = getSelectedRadioButton();

                // Check if the answer is correct
                boolean isCorrect = checkAnswer(selectedRadioButton); // Implement your logic to check the answer

                // Update the color of the selected radio button based on correctness
                updateRadioButtonColor(selectedRadioButton, isCorrect);

                // Load the next question
                loadNextQuestion();
            } else {
                // Display a toast message if no option is selected
                showToast("Please select an option");
            }
        });
    }
   public  void DisplayFirstQuestion(){
        quizViewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {
               questionsList = questions;
               mainBinding.questionTextView.setText("Question 1: "+questions.get(0).getQuestion());
               mainBinding.option1RadioButton.setText(questions.get(0).getOption1());
               mainBinding.option2RadioButton.setText(questions.get(0).getOption2());
               mainBinding.option3RadioButton.setText(questions.get(0).getOption3());
               mainBinding.option4RadioButton.setText(questions.get(0).getOption4());
            }
        });
    }

    private boolean isAnyRadioButtonSelected() {
        for (RadioButton radioButton : radioButtons) {
            if (radioButton.isChecked()) {
                return true;
            }
        }
        return false;
    }
    private RadioButton getSelectedRadioButton() {
        for (RadioButton radioButton : radioButtons) {
            if (radioButton.isChecked()) {
                return radioButton;
            }
        }
        return null;
    }
    private void loadNewQuestion() {
        // Reset the color of all radio buttons to default (white)
        for (RadioButton radioButton : radioButtons) {
            radioButton.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        }

        // Implement your logic to load a new question


        int selectedOption = mainBinding.optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOption != -1) {
            // More Questions to Display??
            if ((questionsList.size() - i) > 0) {

                // Getting the number of questions
                totalquestion = questionsList.size();
                mainBinding.questionTextView.setText("Question "+(i+1)+ " : "+
                        questionsList.get(i).getQuestion());

                mainBinding.option1RadioButton.setText(questionsList.get(i).getOption1());
                mainBinding.option2RadioButton.setText(questionsList.get(i).getOption2());
                mainBinding.option3RadioButton.setText(questionsList.get(i).getOption3());
                mainBinding.option4RadioButton.setText(questionsList.get(i).getOption4());


                // Check if it is the last question
                if(i == (questionsList.size() -1)){
                    mainBinding.NextBtn.setText("Finish");
                }
                mainBinding.optionsRadioGroup.clearCheck();

            }
        }

    }

    private void loadNextQuestion() {
        i++;
        // Check if there are more questions (replace with your logic)
        if (i < getTotalQuestions()) {
            loadNewQuestion();
        } else if (i == (questionsList.size() -1) ){
            mainBinding.NextBtn.setText("Finish");
        }
    }

    private boolean checkAnswer(RadioButton selectedRadioButton) {

            if(selectedRadioButton.getText().toString().equals(
                    questionsList.get(i).getCorrectAnswer()
            )){
                result++;
                mainBinding.corrans.setText(
                        "Correct Answer: "+questionsList.get(i).getCorrectAnswer()
                );
                return true;
            }
            else {
                mainBinding.corrans.setText(
                        "Correct Answer: "+questionsList.get(i).getCorrectAnswer()
                );
            }


        return false; // Replace with your implementation
    }

    private void updateRadioButtonColor(RadioButton radioButton, boolean isCorrect) {
        // Update the color of the radio button based on correctness
        int color = isCorrect ? Color.GREEN : Color.RED;
        radioButton.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Replace this method with your logic to get the total number of questions
    private int getTotalQuestions() {
        return questionsList.size(); // Replace with the actual number of questions
    }
}
