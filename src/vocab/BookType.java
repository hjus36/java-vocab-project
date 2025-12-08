package src.vocab;

// 단어장 종류(전공 / 토익 / JLPT)를 나타내는 enum
public enum BookType {
    MAJOR("word_data/major_vocab.txt"),
    TOEIC("word_data/toeic_vocab.txt"),
    JLPT("word_data/jlpt_vocab.txt");

    private final String path; // 각 단어장에 대응되는 파일 경로

    BookType(String path) {
        this.path = path;
    }

    // 실제 파일 경로 반환
    public String getPath() {
        return path;
    }

    // 콤보박스 등에 표시될 이름
    @Override
    public String toString() {
        switch (this) {
            case MAJOR:
                return "전공 단어장";
            case TOEIC:
                return "토익 단어장";
            case JLPT:
                return "JLPT 단어장";
            default:
                return super.toString();
        }
    }
}
