package com.igypap.quiz;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by igypap on 17.12.16.
 */

public class RandomQuestionsDatabase implements IQuestionDatabase {

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            Question question = new Question();
            //tresc pytania
            int left = random.nextInt(100), right = random.nextInt(100);
            question.setQuestion(String.format("%d + %d = ?", left, right));
            int correctAnswer = left + right;
            //odpowiedzi
            List<String> answers = new LinkedList<>();
            int correctPosition = random.nextInt(3);
            for (int j = 0; j < 3; j++) {
                answers.add(random.nextInt(200) + "");
            }
            answers.set(correctPosition, correctAnswer + "");

            question.setAnswers(answers);
            question.setCorrectAnswer(correctPosition);
            questions.add(question);

        }
        return questions;
    }
}
