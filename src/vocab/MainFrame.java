package src.vocab;

import javax.swing.*;

public class MainFrame extends JFrame {

    private WordBook book;
    private FileManager fm;
    private QuizManager qm;

    public MainFrame() {
        book = new WordBook();
        fm = new FileManager();
        qm = new QuizManager(book);

        setTitle("나만의 단어장");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 실제 버튼 배치, 이벤트 처리 등은 3주차에서 수행
        // 2주차는 GUI "구조만" 작성
        JLabel label = new JLabel("GUI는 3주차부터 구현됩니다.", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}
