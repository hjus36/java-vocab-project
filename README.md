# Java Vocabulary Project

자바프로그래밍 팀 프로젝트로 개발한 영어 단어장 프로그램입니다.
전공 영어 / 토익 / JLPT 단어장을 구분하여 관리하고,
단어 학습과 반복 퀴즈를 통해 효율적으로 공부할 수 있도록 구성했습니다.

## 기능

- 단어 추가 / 삭제 / 검색
- 단어장 저장 및 불러오기 (전공 / 토익 / JLPT 단어장 선택 기능)
- 랜덤 퀴즈 출제 및 정답률 통계
- 퀴즈 종료 후 요약 표시
- 오답노트 기능
- 오답노트 파일 자동 저장
- 오답퀴즈 기능(오답만 다시 풀 수 있는 기능)
- GUI 기반 단어장 / 퀴즈 / 통계 화면 제공

## 개발 환경

- Java JDK 22
- VS Code
- Git / GitHub

## 패키지 구조

- `src/vocab/App.java` : 메인 실행 클래스
- `src/vocab/BookType.java` : 단어장 종류(전공/토익/JLPT) 관리
- `src/vocab/Word.java` : 단어 한 개 정보
- `src/vocab/WordBook.java` : 여러 단어 관리
- `src/vocab/FileManager.java` : 파일 저장/불러오기
- `src/vocab/MainFrame.java` : GUI 메인 화면
- `src/vocab/QuizFrame.java` : GUI 단어 퀴즈 화면
- `src/vocab/QuizManager.java` : 퀴즈 문제 제공/정답 체크/누적 통계 관리/오답리스트 관리
- `src/vocab/StatsFrame.java` : 단어장/퀴즈 통계 화면

## 현재 구현 현황 (4주차 최종)

| 클래스명       | 구현 상태   | 설명 |
|----------------|-------------|-------|
| **Word**       | 완성 | 단어 구조 및 중복 처리 |
| **WordBook**   | 완성 | 단어 관리 기능(추가/삭제/검색/목록) |
| **BookType**   | 완성 | 단어장 종류 및 파일 경로 관리 |
| **FileManager**| 완성 | 단어장 저장·불러오기 및 오답노트 파일 저장 |
| **QuizManager**| 완성 | 퀴즈/정답처리/통계/오답관리 기능 |
| **App**        | 완성 | 프로그램 실행 |
| **MainFrame**  | 완성 | 메인 UI / 단어관리 / 검색 / 퀴즈 / 오답노트 |
| **QuizFrame**  | 완성 | 퀴즈 진행, 세션 요약, 오답 처리, 오답퀴즈 지원 |
| **StatsFrame** | 완성 | 단어장 및 퀴즈 통계 출력 |
