package src.vocab;

// 단어 한 개 저장용 클래스
public class Word {

    private String english;   // 영어
    private String meaning;   // 뜻(한글)

    // 생성자 
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

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return english + " : " + meaning;
    }

    // 소문자로 통일 후 비교
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Word)) return false;
        Word other = (Word) obj;
        if (english == null || other.english == null) return false;
        return english.equalsIgnoreCase(other.english);
    }

    @Override
    public int hashCode() {
        return english == null ? 0 : english.toLowerCase().hashCode();
    }
}
