package src.vocab;

// 단어 1개 정보
public class Word {


    // 2주차: 단어 한 개를 저장하기 위한 기본 필드 정의

    private String english;   // 영어 단어
    private String meaning;   // 뜻(한국어 의미)

  
    // 2주차: 생성자 작성
   
    public Word(String english, String meaning) {
        this.english = english;
        this.meaning = meaning;
    }

    
    // 2주차: 단어 정보 조회용 getter 메소드
   
    public String getEnglish() {
        return english;
    }

    public String getMeaning() {
        return meaning;
    }

 
    // 3주차: 기존 FileManager / QuizManager와의 호환을 위해 추가
    //  - getKorean() → getMeaning()과 동일하게 의미(뜻)를 반환

    public String getKorean() {
        return meaning;
    }

    
    // 3주차: 단어 수정 기능 대비 (뜻을 변경 가능하도록 setter 추가)
    
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    
    // 2주차: 보기 좋게 출력하기 위한 toString() 재정의
    
    @Override
    public String toString() {
        return english + " : " + meaning;
    }

    
    // 3주차: 중복 단어 비교를 위한 equals() / hashCode() 재정의
    //  - 영어 단어가 같으면 같은 단어로 간주 (대소문자 무시)
    //  - WordBook.add()에서 중복 체크에 사용됨
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Word)) return false;

        Word other = (Word) obj;

        if (english == null || other.english == null) {
            return false;
        }

        return english.equalsIgnoreCase(other.english);
    }

    @Override
    public int hashCode() {
        return (english == null) ? 0 : english.toLowerCase().hashCode();
    }
}
