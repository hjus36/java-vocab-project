package src.vocab;

public class Word {

    private String english; // 영어 단어
    private String korean;  // 한국어 뜻

    // 생성자
    public Word(String english, String korean) {
        this.english = english;
        this.korean = korean;
    }

    public String getEnglish() {
        return english;
    }

    public String getKorean() {
        return korean;
    }

    @Override
    public String toString() {
        return english + " : " + korean;
    }
}
