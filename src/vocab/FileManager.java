package src.vocab;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// 파일 저장/불러오기 담당 클래스
public class FileManager {

    private static final String FILE_PATH = "word_data/Word.txt";

    // 단어장 내용을 파일에 저장
    public boolean save(WordBook book) {
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs(); // 디렉토리 자동 생성
        }

        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file),
                        StandardCharsets.UTF_8))) {

            List<Word> list = book.getAll();
            for (Word w : list) {
                // 형식: 영어,뜻
                bw.write(w.getEnglish() + "," + w.getMeaning());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace(); // 콘솔에 오류 출력
            return false;
        }
    }

    // 파일에서 단어장으로 불러오기
    public boolean load(WordBook book) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            // 저장된 파일이 없는 경우
            return false;
        }

        book.clear(); // 기존 내용 초기화 후 불러오기

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", 2); // 영어,뜻
                if (p.length == 2) {
                    String eng = p[0].trim();
                    String mean = p[1].trim();
                    if (!eng.isEmpty() && !mean.isEmpty()) {
                        book.add(new Word(eng, mean));
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
