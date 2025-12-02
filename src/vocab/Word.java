package src.vocab;

// 단어 한 개 저장용 클래스
public class Word {

    private String english;   // 영어
    private String meaning;   // 뜻

    // 생성자 
    public Word(String english, String meaning) {
        this.english = english;
        this.meaning = meaning;
    }

    // getter들 (getEnglish , getMeaning)
    public String getEnglish() {
        return english;
    }

    public String getMeaning() {
        return meaning;
    }

    
    @Override
    public String toString() {
        return english + " : " + meaning;
    }
    // 출력할께 알아보기 쉽게하기 위해 선언

    
}
