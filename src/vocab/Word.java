package src.vocab;

public class Word {
    private String english;
    private String korean;

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

