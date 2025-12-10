package src.vocab;

import java.util.*;

// 퀴즈 출제 및 정답 기록 관리
public class QuizManager {

    private final WordBook book;
    private final Random random = new Random();

    private final List<Word> wrongList = new ArrayList<>(); // 오답노트 저장용 리스트

    private int totalQuestions;   // 전체 푼 문제 수
    private int correctAnswers;   // 정답 수

    public QuizManager(WordBook book) {
        this.book = book;
    }

    // 랜덤 단어 하나 반환 (단어장이 비어있으면 null)
    public Word getRandomWord() {
        List<Word> list = book.getAll();
        if (list.isEmpty()) {
            return null;
        }
        int idx = random.nextInt(list.size());
        return list.get(idx);
    }

    // 오답 리스트에서 랜덤 단어 하나 반환
    public Word getRandomWrongWord() {
        if (wrongList.isEmpty()) return null;
        int idx = random.nextInt(wrongList.size());
        return wrongList.get(idx);
    }    

    // 정답 체크 및 통계 업데이트
    public boolean checkAnswer(Word questionWord, String userAnswer) {
        if (questionWord == null) return false;

        String correct = questionWord.getMeaning();
        String answer = userAnswer == null ? "" : userAnswer.trim();
        boolean isCorrect = correct.trim().equals(answer);

        totalQuestions++;
        if (isCorrect) {
            correctAnswers++;
        } else {
            // 오답이면 오답리스트에 등록 (중복 방지)
            if (!wrongList.contains(questionWord)) {
                wrongList.add(questionWord);
            }
        }

        return isCorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public double getAccuracy() {
        if (totalQuestions == 0) return 0.0;
        return (correctAnswers * 100.0) / totalQuestions;
    }

    public List<Word> getWrongList() {
        return new ArrayList<>(wrongList);
    }

    public boolean hasWrongNotes() {
        return !wrongList.isEmpty();
    }

    public void clearWrongList() {
        wrongList.clear();
    }

    public String getStatsString() {
        if (totalQuestions == 0) {
            return "아직 퀴즈 풀이 기록이 없습니다.";
        }
        return "총 풀이 문제 수 : " + totalQuestions +
               "개\n정답 수 : " + correctAnswers +
               "개\n정답률 : " + String.format("%.1f", getAccuracy()) + "%";
    }
}
