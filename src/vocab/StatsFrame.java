package src.vocab;

import javax.swing.*;
import java.awt.*;

// 통계 표시용 GUI 창
public class StatsFrame extends JFrame {

    public StatsFrame(WordBook book, QuizManager quizManager) {
        setTitle("단어장 통계");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents(book, quizManager);

        setVisible(true);
    }

    private void initComponents(WordBook book, QuizManager quizManager) {
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("통계 정보", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        StringBuilder sb = new StringBuilder();
        sb.append("[단어장 통계]\n");
        sb.append(book.getStatsString()).append("\n\n");

        sb.append("[퀴즈 통계]\n");
        sb.append(quizManager.getStatsString()).append("\n");

        area.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.CENTER);
    }
}
