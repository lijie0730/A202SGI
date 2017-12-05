package com.example.a555l.quizapp;

public class Quiz {

    private String question_num;

    public String getQuestion_num() {
        return question_num;
    }

    public void setQuestion_num(String question_num) {
        this.question_num = question_num;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }

    private String question_answer;

    private long id;



    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

}
