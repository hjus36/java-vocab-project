package src.vocab;
import java.util.List;
import java.util.ArrayList;

//단어장 클래스
public class WordBook {

<<<<<<< HEAD
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
=======
    // 단어 넣어두는 리스트
    private List<Word> words = new ArrayList<>();

    // 단어 추가 -> 아직은 같은 영어 단어가 있어도 그냥 또 들어는 상태임. 추후 보강예정
    public void add(Word word) {
        if (word != null) {
            words.add(word);
>>>>>>> origin/main
        }
    }

<<<<<<< HEAD
    // 검색: 첫 매칭 단어 반환 (없으면 null)
    public Word find(String english) {
        for (Word w : list) {
=======
    // 단어장 안에 모든 단어들 돌려주는 기능 (전체 목록 반환)
    public List<Word> getAll() {
        return words;
    }

    // 특정 영어단어 찾는 기능 
    public Word findByEnglish(String english) {
        if (english == null) {
            return null;
        }
            
        for (Word w : words) {
>>>>>>> origin/main
            if (w.getEnglish().equalsIgnoreCase(english)) {
                return w;
            }
        }
        return null;
    }

<<<<<<< HEAD
    public ArrayList<Word> getAll() {
        return list; // 내부 리스트 직접 반환
    }

    public int size() {
        return list.size();
    }
=======
    // 영어단어로 삭제하는 기능 
    public boolean removeByEnglish(String english) {
        Word target = findByEnglish(english);
        if (target == null) { // 단어가 없을 때 
            return false;
        }
        return words.remove(target); // true상태 -> 삭제
    }

    // 검색 기능
    public List<Word> search(String keyword) {
        List<Word> result = new ArrayList<>(); //result 리스트 추가 

        if (keyword == null || keyword.trim().equals("")) {
            return result;
        }

        String k = keyword.toLowerCase();

        for (Word w : words) {
            String e = w.getEnglish().toLowerCase(); // 대소문자 상관 없이 비교
                                                    //  -> 단어를 소문자로 바꾸는 원리.
            String m = w.getMeaning().toLowerCase();

            if (e.contains(k) || m.contains(k)) { // 단어 or 뜻 하나라고 있으면 단어 출력 함. 
                result.add(w); //result에 추가함. 
            }
        }

        return result;
    }

>>>>>>> origin/main
}
