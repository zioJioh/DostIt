package AlarM;

import java.util.ArrayList;
public class professorQuiz{
    public String question;
    public String professor;
    public ArrayList<Professor> questionList;
    public int random;
    public String res;
    professorQuiz(){
        random = (int) (Math.random() * 4);
        questionList = new ArrayList<Professor>();
        questionList.add(new Professor("김대엽 교수님", "웹프로그램, 자바, C언어를 가르치시는 교수님의 성함은?"));
        questionList.add(new Professor("양수미 교수님", "데이터구조를 가르치시는 교수님의 성함은?"));
        questionList.add(new Professor("고승철 교수님", "현대암호론을 가르치시는 교수님의 성함은?"));
        questionList.add(new Professor("김영미 교수님", "C++을 가르치시는 교수님의 성함은?"));
    }
    String professorText(){
        return questionList.get(random).question;
    }
    String professorName(){
        return questionList.get(random).professor;
    }
}
class Professor{
    String professor;
    String question;
    Professor(){}
    Professor(String professor, String question){
        this.professor = professor;
        this.question = question;
    }
}
