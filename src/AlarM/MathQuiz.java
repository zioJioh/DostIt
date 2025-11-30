package AlarM;
import java.util.*;
public class MathQuiz{
    public int randomA;
    public int randomB;
    public String  quizString;
    public String res;
    MathQuiz(){
        randomA = (int) (Math.random()*100 + 1);
        randomB = (int) (Math.random()*100 + 1);
        res = String.valueOf(randomA + randomB);
    }
    String mathQuizText(){
        quizString = randomA + " + " + randomB + " = ?";
        return quizString;
    }
}
