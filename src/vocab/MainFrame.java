package src.vocab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {

    private final WordBook book;
    private final FileManager fileManager;
    private final QuizManager quizManager;

    private JTextField engField;
    private JTextField korField;
    private JTextArea outputArea;

    public MainFrame(WordBook book, FileManager fileManager) {
        this.book = book;
        this.fileManager = fileManager;
        this.quizManager = new QuizManager(book);

        setTitle("나만의 영어 단어장");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // 상단: 입력 영역
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("단어 입력"));

        JLabel engLabel = new JLabel("영어 단어:");
        engField = new JTextField();

        JLabel korLabel = new JLabel("뜻(한글):");
        korField = new JTextField();

        inputPanel.add(engLabel);
        inputPanel.add(engField);
        inputPanel.add(korLabel);
        inputPanel.add(korField);

        add(inputPanel, BorderLayout.NORTH);

        // 중앙: 출력 영역
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("출력"));
        add(scrollPane, BorderLayout.CENTER);

        // 하단: 버튼 영역
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton addButton = new JButton("추가");
        JButton delButton = new JButton("삭제");
        JButton listButton = new JButton("목록");
        JButton saveButton = new JButton("저장");
        JButton loadButton = new JButton("불러오기");
        JButton quizButton = new JButton("퀴즈");
        JButton statsButton = new JButton("통계");

        buttonPanel.add(addButton);
        buttonPanel.add(delButton);
        buttonPanel.add(listButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(quizButton);
        buttonPanel.add(statsButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // 버튼 이벤트

        // 단어 추가
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWord();
            }
        });

        // 단어 삭제
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteWord();
            }
        });

        // 목록 보기
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllWords();
            }
        });

        // 저장
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveWords();
            }
        });

        // 불러오기
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadWords();
            }
        });

        // 퀴즈
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openQuiz();
            }
        });

        // 통계
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStats();
            }
        });
    }

    private void addWord() {
        String eng = engField.getText().trim();
        String kor = korField.getText().trim();

        if (eng.isEmpty() || kor.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "영어 단어와 뜻을 모두 입력해 주세요.",
                    "알림",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        Word w = new Word(eng, kor);
        boolean added = book.add(w);
        if (added) {
            outputArea.append("추가됨: " + w + "\n");
            engField.setText("");
            korField.setText("");
            engField.requestFocus();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "이미 존재하는 단어입니다: " + eng,
                    "중복 단어",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void deleteWord() {
        String eng = engField.getText().trim();

        if (eng.isEmpty()) {
            eng = JOptionPane.showInputDialog(
                    this,
                    "삭제할 영어 단어를 입력하세요:",
                    "단어 삭제",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (eng == null) return; // 취소
            eng = eng.trim();
        }

        if (eng.isEmpty()) {
            return;
        }

        boolean removed = book.remove(eng);
        if (removed) {
            outputArea.append("삭제됨: " + eng + "\n");
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "해당 단어를 찾을 수 없습니다: " + eng,
                    "삭제 실패",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void showAllWords() {
        List<Word> list = book.getAll();
        if (list.isEmpty()) {
            outputArea.setText("단어장이 비어 있습니다.\n");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== 단어 목록 ===\n");
        for (Word w : list) {
            sb.append(w.toString()).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private void saveWords() {
        boolean ok = fileManager.save(book);
        if (ok) {
            JOptionPane.showMessageDialog(
                    this,
                    "저장 완료!",
                    "저장",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "저장 중 오류가 발생했습니다.",
                    "저장 오류",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadWords() {
        boolean ok = fileManager.load(book);
        if (ok) {
            outputArea.setText("불러오기 완료.\n");
            showAllWords();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "불러올 파일이 없거나, 불러오는 중 오류가 발생했습니다.",
                    "불러오기 오류",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void openQuiz() {
        if (book.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "단어장이 비어 있습니다.\n먼저 단어를 추가해 주세요.",
                    "퀴즈 시작 불가",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        new QuizFrame(book, quizManager);
    }

    private void openStats() {
        new StatsFrame(book, quizManager);
    }
}
