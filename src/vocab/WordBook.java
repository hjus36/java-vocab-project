package src.vocab;
import java.util.ArrayList;

public class WordBook {
    private ArrayList<Word> list = new ArrayList<>();

    // 추가
    public void add(Word w) {
        list.add(w);
    }

    // 삭제
    public boolean delete(String english) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEnglish().equalsIgnoreCase(english)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    // 검색
    public Word find(String english) {
        for (Word w : list) {
            if (w.getEnglish().equalsIgnoreCase(english)) {
                return w;
            }
        }
        return null;
    }

    // 전체 리스트 반환
    public ArrayList<Word> getAll() {
        return list;
    }

    public int size() {
        return list.size();
    }
}
