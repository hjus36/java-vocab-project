package src.vocab;

import java.util.*;

// 단어들을 관리하는 단어장 클래스
public class WordBook {

    private final List<Word> words = new ArrayList<>();

    // 단어 추가 (같은 영어 단어가 있으면 추가하지 않음)
    // true  = 새로 추가
    // false = 이미 존재해서 추가 실패
    public boolean add(Word word) {
        if (word == null || word.getEnglish() == null) {
            return false;
        }
        // 중복 체크 (equals는 영어 단어 기준)
        if (words.contains(word)) {
            return false;
        }
        words.add(word);
        return true;
    }

    // 영어 단어로 삭제
    public boolean remove(String english) {
        if (english == null) return false;
        for (int i = 0; i < words.size(); i++) {
            Word w = words.get(i);
            if (w.getEnglish() != null &&
                w.getEnglish().equalsIgnoreCase(english)) {
                words.remove(i);
                return true;
            }
        }
        return false;
    }


    // 전체 단어 리스트 반환 (복사본)
    public List<Word> getAll() {
        return new ArrayList<>(words);
    }

    public int size() {
        return words.size();
    }

    public boolean isEmpty() {
        return words.isEmpty();
    }

    public void clear() {
        words.clear();
    }

    // 통계 문자열
    public String getStatsString() {
        return "현재 저장된 단어 수 : " + size() + "개";
    }

    // 키워드로 부분 검색
    public List<Word> search(String keyword) {
        List<Word> result = new ArrayList<>();
        if (keyword == null) return result;

        String keyLower = keyword.toLowerCase().trim();
        if (keyLower.isEmpty()) return result;

        for (Word w : words) {
            String eng = w.getEnglish();
            String mean = w.getMeaning();

            boolean match = false;

            if (eng != null && eng.toLowerCase().contains(keyLower)) {
                match = true;
            }

            if (!match && mean != null && mean.contains(keyword)) {
                match = true;
            }

            if (match) {
                result.add(w);
            }
        }
        return result;
    }
}
