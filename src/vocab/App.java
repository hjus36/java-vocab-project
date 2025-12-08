package src.vocab;

public class App {

    public static void main(String[] args) {
        WordBook book = new WordBook();
        FileManager fm = new FileManager();

        fm.load(book, BookType.MAJOR);

        new MainFrame(book, fm); 
    }
}
