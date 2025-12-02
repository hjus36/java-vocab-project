package src.vocab;

import java.io.*;

public class FileManager {
    private static final String FILE_PATH = "word_data/Word.txt";

    public void save(WordBook book) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(FILE_PATH), "UTF-8"));

            for (Word w : book.getAll()) {
                bw.write(w.getEnglish() + "," + w.getKorean());
                bw.newLine();
            }

            bw.close();
            System.out.println("[Info] 저장 완료: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("[Error] 파일 저장 중 오류 발생");
        }
    }

    public void load(WordBook book) {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(FILE_PATH), "UTF-8"));

            book.getAll().clear();

            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;

                String[] p = line.split(",");
                if (p.length == 2) {
                    book.add(new Word(p[0], p[1]));
                }
            }

            br.close();
            System.out.println("[Info] 불러오기 완료: " + FILE_PATH);
        } catch (FileNotFoundException e) {
            // 초기 실행 시 파일이 없을 수 있음 → 그냥 안내만
            System.out.println("[Info] 저장된 파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("[Error] 파일 읽기 오류");
        }
    }
}
