package src.vocab;

import java.util.*;

// 퀴즈 출제 및 정답 기록 관리
public class QuizManager {

    private final WordBook book;
    private final Random random = new Random();

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

    // 정답 체크 + 통계 업데이트
    public boolean checkAnswer(Word questionWord, String userAnswer) {
        if (questionWord == null) return false;

        String correct = questionWord.getMeaning();
        String answer = userAnswer == null ? "" : userAnswer.trim();
        boolean isCorrect = correct.trim().equals(answer);

        totalQuestions++;
        if (isCorrect) {
            correctAnswers++;
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

    public String getStatsString() {
        if (totalQuestions == 0) {
            return "아직 퀴즈 풀이 기록이 없습니다.";
        }
        return "총 풀이 문제 수 : " + totalQuestions +
               "개\n정답 수 : " + correctAnswers +
               "개\n정답률 : " + String.format("%.1f", getAccuracy()) + "%";
    }
}
