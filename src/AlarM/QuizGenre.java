package AlarM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import MainGUI.MainGui;

public class QuizGenre{
    public JFrame quizFrame;
    public JLabel questionLabel;
    public JTextField answerField;
    public JButton submitButton;
    public TimeGui tG;
    //    public MainGui mz;
    QuizGenre(TimeGui tG){
        this.tG = tG;
        // New JFrame for the question
        quizFrame = new JFrame();
        quizFrame.setTitle("알람을 끄려면 문제를 푸세요!");

        //새 창 닫기는 이걸로 DISPOSE_ON_CLOSE 아니면 다 닫힘
        quizFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        quizFrame.setSize(300, 200);

        MathQuiz mathQuiz = new MathQuiz();
        professorQuiz professorQuiz = new professorQuiz();
        // Create UI for the question


        if(tG.getSelectedOption().equals("덧셈 문제")){
            questionLabel = new JLabel(mathQuiz.mathQuizText());
        }else if(tG.getSelectedOption().equals("과목 문제")){
            questionLabel = new JLabel(professorQuiz.professorText());
        }

        //
        answerField = new JTextField(10);
        submitButton = new JButton("정답!");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = answerField.getText();
                if(tG.getSelectedOption().equals("덧셈 문제")){
                    if (mathQuiz.res.equals(answer)) {
                        JOptionPane.showMessageDialog(quizFrame, "정답!");
                        quizFrame.dispose();
                        tG.alarmStop();

//                        MainGui().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(quizFrame, "오답!");
                    }
                }else if(tG.getSelectedOption().equals("과목 문제")){
                    if (professorQuiz.professorName().equals(answer)) {
                        JOptionPane.showMessageDialog(quizFrame, "정답!");
                        quizFrame.dispose();
                        tG.alarmStop();
                    } else {
                        JOptionPane.showMessageDialog(quizFrame, "오답!");
                    }
                }
            }
        });
    }
}