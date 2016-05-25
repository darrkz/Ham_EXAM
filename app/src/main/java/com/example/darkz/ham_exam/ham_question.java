package com.example.darkz.ham_exam;

/**
 * Created by darkz on 2016/5/15.
 */
public class ham_question {
    private String question_id;
    private String question_level;
    private String question_content;
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private String picture_name;

    public ham_question()
    {
    }
    public ham_question(String question_id, String question_level, String question_content, String answer_a, String answer_b, String answer_c, String answer_d, String picture_name)
    {
        this.question_id=question_id;
        this.question_level=question_level;
        this.question_content=question_content;
        this.answer_a=answer_a;
        this.answer_b=answer_b;
        this.answer_c=answer_c;
        this.answer_d=answer_d;
        this.picture_name=picture_name;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }
    public void setQuestion_level(String question_level) {
        this.question_level = question_level;
    }
    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }
    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }
    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }
    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }
    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }
    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }

    public String getQuestion_id() {
        return question_id;
    }
    public String getQuestion_level() {
        return question_level;
    }
    public String getQuestion_content() {
        return question_content;
    }
    public String getAnswer_a() {
        return answer_a;
    }
    public String getAnswer_b() {
        return answer_b;
    }
    public String getAnswer_c() {
        return answer_c;
    }
    public String getAnswer_d() {
        return answer_d;
    }
    public String getPicture_name() {
        return picture_name;
    }
}
