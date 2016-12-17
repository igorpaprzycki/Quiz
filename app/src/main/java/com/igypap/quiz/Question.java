package com.igypap.quiz;

import java.io.Serializable;
import java.util.List;

/**
 * Created by igypap on 17.12.16.
 */

public class Question implements Serializable {
    private String question;
    private List<String> answers;
    private int correctAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
