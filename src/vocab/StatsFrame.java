package src.vocab;

import javax.swing.*;
import java.awt.*;

public class StatsFrame extends JFrame {

    public StatsFrame() {
        setTitle("통계");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 통계 기능은 아직 미구현 상태
        JLabel label = new JLabel("통계 기능은 3주차에 구현될 예정", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}
