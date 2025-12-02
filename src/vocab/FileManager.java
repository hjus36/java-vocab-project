package src.vocab;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_PATH = "word_data/Word.txt";

    public void save(WordBook book) {
        try {
            // 파일 출력: UTF-8로 저장 (한글 깨짐 방지)
            OutputStreamWriter osw = new OutputStreamWriter(
                    new FileOutputStream(FILE_PATH), "UTF-8"
            );
            BufferedWriter bw = new BufferedWriter(osw);
            ArrayList<Word> list = book.getAll();
            for (Word w : list) {
                // 형식: 영어,한글
                bw.write(w.getEnglish() + "," + w.getKorean());
                bw.newLine();
            }
            bw.close();
            System.out.println("저장 완료: " + FILE_PATH);
        } catch (IOException e) {
            // 파일 쓰기/인코딩 관련 예외 처리
            System.out.println("저장 중 오류 발생");
        }
    }

    public void load(WordBook book) {
        try {
            // 파일 입력: UTF-8로 읽기 (한글 깨짐 방지)
            InputStreamReader isr = new InputStreamReader(
                    new FileInputStream(FILE_PATH), "UTF-8"
            );
            BufferedReader br = new BufferedReader(isr);
            book.getAll().clear(); // 기존 목록 초기화 후 로드
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 2) {
                    // 형식이 맞을 때만 추가 (간단한 유효성 검사)
                    book.add(new Word(p[0], p[1]));
                }
            }
            br.close();
            System.out.println("불러오기 완료: " + FILE_PATH);
        } catch (FileNotFoundException e) {
            // 파일이 없을 경우 (첫 실행 등)
            System.out.println("저장된 파일이 없습니다.");
        } catch (IOException e) {
            // 읽기 중 오류
            System.out.println("불러오기 오류");
        }
    }
}
