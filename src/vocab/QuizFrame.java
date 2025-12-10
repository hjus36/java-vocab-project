package src.vocab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizFrame extends JFrame {

    private final WordBook book;
    private final QuizManager quizManager;

    private final FileManager fileManager;
    private final BookType currentType;
    private final boolean wrongOnly;   // 오답 전용 퀴즈 구분   

    private JLabel questionLabel;     // "영단어: Abstraction"
    private JTextField answerField;   // 사용자 입력
    private JButton checkButton;      // 정답 확인
    private JButton closeButton;      // 닫기

    private Word currentWord;         // 현재 문제로 출제된 단어

    private int startTotalQuestions;
    private int startCorrectAnswers;

    // 생성자
    public QuizFrame(WordBook book, QuizManager quizManager, FileManager fileManager,
                        BookType currentType, boolean wrongOnly) {
        this.book = book;
        this.quizManager = quizManager;
        this.fileManager = fileManager;
        this.currentType = currentType;
        this.wrongOnly = wrongOnly;

        // 세션 시작 시점 통계 기록
        this.startTotalQuestions = quizManager.getTotalQuestions();
        this.startCorrectAnswers = quizManager.getCorrectAnswers();

        setTitle(wrongOnly ? "오답 퀴즈" : "영어 단어 퀴즈");
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
        questionLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 16));
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
                showQuizSummary();
                dispose();
            }
        });

        // X 버튼으로 닫을 때도 요약 보여주기
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                showQuizSummary();
            }
        });
    }

    // 다음 문제 불러오기
    private void loadNextQuestion() {
        if (wrongOnly) {
            // 오답 전용 모드
            if (!quizManager.hasWrongNotes()) {
                JOptionPane.showMessageDialog(
                        this,
                        "오답노트가 비어 있어 오답 퀴즈를 진행할 수 없습니다.",
                        "알림",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
                return;
            }
            currentWord = quizManager.getRandomWrongWord();
        } else {
            // 일반 퀴즈 모드
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

    // 현재 오답노트를 파일로 저장
    private void saveWrongNotesToFile() {
        if (fileManager == null || currentType == null) {
            return;
        }

        java.util.List<Word> wrongs = quizManager.getWrongList();
        if (wrongs.isEmpty()) {
            return;
        }

        boolean ok = fileManager.saveWrongNotes(wrongs, currentType);
        if (!ok) {
            System.err.println("오답노트 저장 중 오류 발생");
        }
    }

    // 퀴즈 세션 요약 보여주기
    private void showQuizSummary() {
        int endTotal = quizManager.getTotalQuestions();
        int endCorrect = quizManager.getCorrectAnswers();

        int sessionTotal = endTotal - startTotalQuestions;
        int sessionCorrect = endCorrect - startCorrectAnswers;

        if (sessionTotal <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "이번 퀴즈 세션에서 푼 문제가 없습니다.",
                    "퀴즈 요약",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        double sessionAccuracy = (sessionCorrect * 100.0) / sessionTotal;

        String msg =
                "이번 퀴즈 요약\n"
                + "------------------------\n"
                + "풀이한 문제 수 : " + sessionTotal + "개\n"
                + "맞힌 문제 수   : " + sessionCorrect + "개\n"
                + "정답률         : " + String.format("%.1f", sessionAccuracy) + "%";

        JOptionPane.showMessageDialog(
                this,
                msg,
                "퀴즈 요약",
                JOptionPane.INFORMATION_MESSAGE
        );

        saveWrongNotesToFile();
    }
}
