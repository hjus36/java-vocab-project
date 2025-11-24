package src.vocab;

import java.util.ArrayList;
import java.util.List;

// 여러 단어를 관리
public class WordBook {
    private List<Word> words = new ArrayList<>();

    public void add(Word word) {
        words.add(word);
    }

    public List<Word> getAll() {
        return words;
    }
}