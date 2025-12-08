package src.vocab;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

// 파일 저장/불러오기 담당 클래스
public class FileManager {

    // 단어장 내용을 파일에 저장 (어떤 단어장인지 BookType으로 구분)
    public boolean save(WordBook book, BookType type) {
        // type에 따라 다른 파일 경로 사용
        File file = new File(type.getPath());
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs(); // 디렉토리 자동 생성
        }

        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file),
                        StandardCharsets.UTF_8))) {

            // 단어장에 있는 모든 단어들을 가져와서 저장
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

    // 파일에서 단어장으로 불러오기 (어떤 단어장인지 BookType으로 구분)
    public boolean load(WordBook book, BookType type) {
        File file = new File(type.getPath());
        if (!file.exists()) {
            // 저장된 파일이 없는 경우
            return false;
        }

        // 기존 내용 초기화 후 파일에서 다시 채움
        book.clear();

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

    // 기본(전공) 단어장용 편의 메서드 - 원래 코드와 호환용
    public boolean save(WordBook book) {
        return save(book, BookType.MAJOR);
    }

    public boolean load(WordBook book) {
        return load(book, BookType.MAJOR);
    }
}
