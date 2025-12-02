package src.vocab;

public class App {
    
    // 프로그램 실행
    public static void main(String[] args) {

        WordBook book = new WordBook(); 
        FileManager fm = new FileManager(); 
        fm.load(book); // 시작 시 저장된 단어 불러오기 (없으면 안내 메시지)
        new MainFrame(book, fm); // 메인 GUI 프레임 생성 및 표시
    }
}
