package src.vocab;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        WordBook book = new WordBook();
        FileManager fm = new FileManager();
        QuizManager qm = new QuizManager(book);

        while (true) {
            System.out.println("\n===== 단어장 프로그램(2주차 버전) =====");
            System.out.println("1. 단어 추가");
            System.out.println("2. 단어 삭제");
            System.out.println("3. 단어 검색");
            System.out.println("4. 전체 목록 출력");
            System.out.println("5. 저장");
            System.out.println("6. 불러오기");
            System.out.println("7. 퀴즈 한 문제(테스트용)");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            int menu = in.nextInt();
            in.nextLine();

            if (menu == 1) {
                System.out.print("영단어: ");
                String e = in.nextLine();
                System.out.print("뜻: ");
                String k = in.nextLine();
                book.add(new Word(e, k));
            } else if (menu == 2) {
                System.out.print("삭제할 단어: ");
                String e = in.nextLine();
                if (book.delete(e))
                    System.out.println("삭제 완료");
                else
                    System.out.println("단어 없음");
            } else if (menu == 3) {
                System.out.print("검색할 단어: ");
                String e = in.nextLine();
                Word w = book.find(e);
                if (w != null)
                    System.out.println(w);
                else
                    System.out.println("검색 실패");
            } else if (menu == 4) {
                for (Word w : book.getAll())
                    System.out.println(w);
            } else if (menu == 5) {
                fm.save(book);
            } else if (menu == 6) {
                fm.load(book);
            } else if (menu == 7) {
                Word w = qm.getRandomWord();
                if (w == null) {
                    System.out.println("단어가 없습니다.");
                } else {
                    System.out.println("영단어: " + w.getEnglish());
                    System.out.print("뜻 입력: ");
                    String ans = in.nextLine();
                    if (qm.check(ans, w))
                        System.out.println("정답!");
                    else
                        System.out.println("오답! 정답: " + w.getKorean());
                }
            } else if (menu == 0) {
                break;
            }
        }

        in.close();
    }
}
