package com.quizgame.triviamaker;

import java.util.ArrayList;

public class QuestionLibrary {

    public ArrayList<String> mQuestions;
    public String mChoices [][];
    public String mCorrectAnswers[];

    public QuestionLibrary(ArrayList<Question> questions) {
        mQuestions = new ArrayList<String>();
        mChoices = new String[250][4];
        mCorrectAnswers = new String[250];

        for(int i = 0; i < questions.size(); i++) {
            mQuestions.add(questions.get(i).question);
            mChoices[i][0] = questions.get(i).choice1;
            mChoices[i][1] = questions.get(i).choice2;
            mChoices[i][2] = questions.get(i).choice3;
            mChoices[i][3] = questions.get(i).choice4;
            mCorrectAnswers[i] = questions.get(i).choicecorrect;
        }
    }

    public String getQuestion(int a) {
        String question = mQuestions.get(a);
        return question;
    }

    public String getChoice1(int a) {
        String choice1 = mChoices[a][0];
        return choice1;
    }

    public String getChoice2(int a) {
        String choice2 = mChoices[a][1];
        return choice2;
    }

    public String getChoice3(int a) {
        String choice3 = mChoices[a][2];
        return choice3;
    }

    public String getChoice4(int a) {
        String choice4 = mChoices[a][3];
        return choice4;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }
 }
