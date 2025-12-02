package src.vocab;
import java.util.List;
import java.util.ArrayList;

//단어장 클래스
public class WordBook {

    // 추가: 리스트 끝에 추가
    public void add(Word w) {
        list.add(w);
    }

    // 삭제: 첫 매칭 항목 삭제 후 true 반환
    // equalsIgnoreCase: 대소문자 무시
    public boolean delete(String english) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEnglish().equalsIgnoreCase(english)) {
                list.remove(i);
                return true;
            }
        }
    }

    // 검색: 첫 매칭 단어 반환 (없으면 null)
    public Word find(String english) {
        for (Word w : list) {
            if (w.getEnglish().equalsIgnoreCase(english)) {
                return w;
            }
        }
        return null;
    }

    public ArrayList<Word> getAll() {
        return list; // 내부 리스트 직접 반환
    }

    public int size() {
        return list.size();
    }
}
