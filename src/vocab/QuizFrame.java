package src.vocab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizFrame extends JFrame {

    private final WordBook book;
    private final QuizManager quizManager;

    private JLabel questionLabel;     // "영단어: Abstraction"
    private JTextField answerField;   // 사용자 입력
    private JButton checkButton;      // 정답 확인
    private JButton closeButton;      // 닫기

    private Word currentWord;         // 현재 문제로 출제된 단어

    public QuizFrame(WordBook book, QuizManager quizManager) {
        this.book = book;
        this.quizManager = quizManager;

        setTitle("영어 단어 퀴즈");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        setVisible(true);

        loadNextQuestion();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // 상단: 문제 표시
        questionLabel = new JLabel("문제를 불러오는 중...", SwingConstants.CENTER);
        questionLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        add(questionLabel, BorderLayout.NORTH);

        // 중앙: 정답 입력 필드
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel("뜻(한글) 입력: ");
        answerField = new JTextField();
        centerPanel.add(label, BorderLayout.WEST);
        centerPanel.add(answerField, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // 하단: 버튼들
        JPanel bottomPanel = new JPanel();
        checkButton = new JButton("정답 확인");
        closeButton = new JButton("닫기");
        bottomPanel.add(checkButton);
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // 이벤트 처리
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        // 엔터 키로도 정답 확인 가능
        answerField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // 다음 문제 불러오기
    private void loadNextQuestion() {
        if (book.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "단어장이 비어 있습니다.\n먼저 단어를 추가해 주세요.",
                    "알림",
                    JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
            return;
        }

        currentWord = quizManager.getRandomWord();
        if (currentWord == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "문제를 가져올 수 없습니다.",
                    "알림",
                    JOptionPane.WARNING_MESSAGE
            );
            dispose();
            return;
        }

        questionLabel.setText("영단어: " + currentWord.getEnglish());
        answerField.setText("");
        answerField.requestFocus();
    }

    // 정답 확인 로직
    private void checkAnswer() {
        if (currentWord == null) {
            return;
        }

        String userAns = answerField.getText();
        if (userAns == null || userAns.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "답을 입력해 주세요.",
                    "알림",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        boolean correct = quizManager.checkAnswer(currentWord, userAns);

        if (correct) {
            JOptionPane.showMessageDialog(
                    this,
                    "정답입니다!",
                    "결과",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "틀렸습니다.\n정답: " + currentWord.getMeaning(),
                    "결과",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        // 다음 문제로 넘어가기
        loadNextQuestion();
    }
}
