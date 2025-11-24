package src.vocab;

// 단어 1개 정보
public class Word {
    private String english;
    private String meaning;

    public Word(String english, String meaning) {
        this.english = english;
        this.meaning = meaning;
    }

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
}