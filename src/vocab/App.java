package src.vocab;

import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                WordBook book = new WordBook();
                FileManager fm = new FileManager();

                // 기존 저장 파일이 있으면 불러오기 시도
                fm.load(book);

                new MainFrame(book, fm);
            }
        });
    }
}
