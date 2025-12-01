package src.vocab;

import java.util.Random;

public class QuizManager {
    private WordBook book;
    private Random rand = new Random();

    public QuizManager(WordBook book) {
        this.book = book;
    }

    // 랜덤 한 문제 가져오기
    public Word getRandomWord() {
        int n = book.size();
        if (n == 0)
            return null;
        int idx = rand.nextInt(n);
        return book.getAll().get(idx);
    }

    // 정답 판별
    public boolean check(String answer, Word w) {
        return w.getKorean().equals(answer);
    }
}
