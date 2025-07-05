package com.example.aniquiz.retrofit;

import com.example.aniquiz.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionsAPI {
    @GET("my_quiz_app_api.php")
    Call<QuestionList>getQuestions();
}
