package src.vocab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private WordBook book;
    private FileManager fm;
    private JTextField engField;
    private JTextField korField;
    private JTextArea outputArea;

    public MainFrame(WordBook book, FileManager fm) {
        this.book = book;
        this.fm = fm;

        setTitle("단어장 프로그램");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 입력 패널 (영단어 / 뜻)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("영단어:"));
        engField = new JTextField();
        inputPanel.add(engField);
        inputPanel.add(new JLabel("뜻:"));
        korField = new JTextField();
        inputPanel.add(korField);
        add(inputPanel, BorderLayout.NORTH);

        // 출력 영역 (작업 로그 / 전체 목록 출력)
        outputArea = new JTextArea();
        JScrollPane sp = new JScrollPane(outputArea);
        add(sp, BorderLayout.CENTER);

        // 버튼 패널 (기능 버튼들)
        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("추가");
        JButton delBtn = new JButton("삭제");
        JButton listBtn = new JButton("전체 목록");
        JButton saveBtn = new JButton("저장");
        JButton loadBtn = new JButton("불러오기");
        JButton quizBtn = new JButton("퀴즈");
        JButton statsBtn = new JButton("통계"); btnPanel.add(statsBtn);

        btnPanel.add(addBtn);
        btnPanel.add(delBtn);
        btnPanel.add(listBtn);
        btnPanel.add(saveBtn);
        btnPanel.add(loadBtn);
        btnPanel.add(quizBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // 추가 버튼: 입력 검사 후 워드북에 추가
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eng = engField.getText();
                String kor = korField.getText();
                if (eng.isEmpty() || kor.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "단어/뜻 모두 입력하세요.");
                    return;
                }
                book.add(new Word(eng, kor));
                outputArea.append("추가됨: " + eng + " : " + kor + "\n");
                engField.setText("");
                korField.setText("");
            }
        });

        // 삭제 버튼: 영단어 기준(대소문자 무시)으로 삭제 시도
        delBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eng = engField.getText();
                if (eng.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "삭제할 영단어를 입력하세요.");
                    return;
                }
                if (book.delete(eng)) {
                    outputArea.append("삭제됨: " + eng + "\n");
                } else {
                    outputArea.append("삭제 실패(없음): " + eng + "\n");
                }
            }
        });

        // 전체 목록 버튼: 현재 워드북 내용을 출력
        listBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
                for (Word w : book.getAll()) {
                    outputArea.append(w.toString() + "\n");
                }
            }
        });

        // 저장 버튼: FileManager를 통해 파일에 저장
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fm.save(book);
                outputArea.append("[저장 완료]\n");
            }
        });

        // 불러오기 버튼: FileManager로 다시 로드 (메모리 초기화 포함)
        loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fm.load(book);
                outputArea.append("[불러오기 완료]\n");
            }
        });

        // 퀴즈 버튼: 퀴즈 창 생성
        quizBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QuizFrame(book);
            }
        });

        // 통계 버튼: 현재는 플레이스홀더(미구현)
        statsBtn.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 new StatsFrame(); // 아직 미구현
            } 
        });

        setVisible(true);
    }
}
