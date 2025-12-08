package src.vocab;

import java.util.ArrayList;
import java.util.List;

public class WordBook {
    

    // 2주차: 단어들을 넣어 둘 리스트 생성
    private List<Word> words = new ArrayList<>();
    
    // 3주차: 단어 추가 기능 강화 (중복 체크 추가)
    //  - Word.equals()를 기반으로 같은 영어 단어면 추가하지 않음
    //  - true  = 새로 추가됨
    //  - false = 중복이어서 추가 실패
   
    public boolean add(Word word) {
        if (word == null) {
            return false;
        }

        for (Word w : words) {
            if (w.equals(word)) {   // 같은 영어 단어라면 중복 처리
                return false;
            }
        }

        words.add(word);
        return true;
    }

    // 2주차: 전체 목록 가져오기
    public List<Word> getAll() {
        return words;
    }

    
    // 3주차: 외부 클래스(App/QuizManager) 호환용 메소드 추가
    //  size()  → 단어 개수 반환
   
    public int size() {
        return words.size();
    }

    
    // 3주차: App과 호환되도록 find() 메소드 추가
    //  내부적으로는 findByEnglish() 호출
    
    public Word find(String english) {
        return findByEnglish(english);
    }

    // 3주차: App과 호환되도록 delete() 메소드 추가
    //  내부적으로는 removeByEnglish() 호출
    public boolean delete(String english) {
        return removeByEnglish(english);
    }

    // 2주차: 영어 단어로 찾기 (기본 검색 기능)
    public Word findByEnglish(String english) {
        if (english == null) return null;

        for (Word w : words) {
            if (w.getEnglish().equalsIgnoreCase(english)) {
                return w;
            }
        }
        return null;
    }

    // 2주차: 영어 단어 삭제 기능
    public boolean removeByEnglish(String english) {
        Word target = findByEnglish(english);
        if (target == null) return false;

        return words.remove(target);
    }

    // 2주차: 키워드 검색 기능 (영어/뜻 검색)
    public List<Word> search(String keyword) {
        List<Word> result = new ArrayList<>();

        if (keyword == null || keyword.trim().equals("")) {
            return result;
        }

        String k = keyword.toLowerCase();

        for (Word w : words) {
            String e = w.getEnglish().toLowerCase();
            String m = w.getMeaning().toLowerCase();

            if (e.contains(k) || m.contains(k)) {
                result.add(w);
            }
        }

        return result;
    }

 
    // 3주차: 통계 기능 (전체 단어 수 반환)
    //  GUI, 파일저장, 퀴즈 기능에서 활용 가능하도록 구조화
    public int getTotalCount() {
        return words.size();
    }
    // 3주차: 단어장이 비어 있는지 여부 판단
    public boolean isEmpty() {
        return words.isEmpty();
    }

    
    // 3주차: 단어장 상태 출력용 요약 기능
    public String getStats() {
        return "단어 수 : " + getTotalCount() + "개";
    }
}
