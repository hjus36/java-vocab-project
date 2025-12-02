package src.vocab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class QuizFrame extends JFrame {

    private WordBook book;
    private Word current;
    private Random rand = new Random();

    private JLabel engLabel;
    private JTextField answerField;
    private JButton checkBtn;

    public QuizFrame(WordBook book) {
        this.book = book;

        setTitle("퀴즈");
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));
        setLocationRelativeTo(null); // 화면 중앙에 띄우기

        if (book.size() == 0) {
            JOptionPane.showMessageDialog(null, "단어가 없습니다.");
            dispose(); // 단어 없으면 창 닫음
            return;
        }

        int idx = rand.nextInt(book.size()); // Random을 통한 무작위 출제
        current = book.getAll().get(idx);

        engLabel = new JLabel("영단어: " + current.getEnglish(), SwingConstants.CENTER);
        answerField = new JTextField();
        checkBtn = new JButton("정답 확인");

        add(engLabel);
        add(answerField);
        add(checkBtn);

        checkBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = answerField.getText().trim();
                
                if (user.equals(current.getKorean())) {
                    JOptionPane.showMessageDialog(QuizFrame.this, "정답!");
                } else {
                    JOptionPane.showMessageDialog(QuizFrame.this, "오답!\n정답: " + current.getKorean());
                }
            }
        });

        // 엔터키로도 정답 확인
        answerField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBtn.doClick();
            }
        });

        setVisible(true);
    }
}
