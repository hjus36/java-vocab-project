# Java Vocabulary Project

자바프로그래밍 팀 프로젝트로 만드는 영어 단어장 프로그램입니다.  
전공 영어 + 토익 단어를 조금 더 편하게 관리하고 공부해 보자는 목적입니다.

## 기능

- 단어 추가 / 삭제 / 검색
- 단어장 저장 및 불러오기
- 랜덤 퀴즈 출제 및 정답률 통계
- GUI 기반 단어장/퀴즈/통계 화면

## 개발 환경

- Java JDK 22
- VS Code
- Git / GitHub

## 패키지 구조

- `src/vocab/App.java` : 메인 실행 클래스
- `src/vocab/Word.java` : 단어 한 개 정보
- `src/vocab/WordBook.java` : 여러 단어 관리
- `src/vocab/FileManager.java` : 파일 저장/불러오기
- `src/vocab/MainFrame.java` : GUI 메인 화면
- `src/vocab/QuizFrame.java` : 단어 퀴즈 GUI
- `src/vocab/QuizManager.java` : 퀴즈 문제 제공·정답 판별·통계 관리
- `src/vocab/StatsFrame.java` : 단어장 + 퀴즈 통계 화면

## 현재 구현 현황 (3주차)

| 클래스명       | 구현 상태   | 설명 |
|----------------|-------------|-------|
| **Word**       | 완성 | 단어 데이터 구조 구현. 중복 비교를 위한 equals()/hashCode() 정리 완료. |
| **WordBook**   | 완성 | 단어 추가·삭제·검색·목록 기능 완성. 중복 추가 방지 및 단어 수 통계 제공. |
| **FileManager**| 완성 | 단어장 파일 저장/불러오기 정상 동작. UTF-8 처리 및 디렉토리 자동 생성 지원. |
| **QuizManager**| 완성 | 랜덤 문제 출제·정답 판별 구현. 문제 수·정답 수·정답률 통계 기능 포함. |
| **App**        | 완성 | 프로그램 실행 구조 정리됨. 저장된 단어 자동 로드 후 메인 화면 실행. |
| **MainFrame**  | 완성 | 단어 추가/삭제/목록/저장/불러오기/퀴즈/통계 GUI 기능 모두 정상 작동. |
| **QuizFrame**  | 완성 | 영어 단어 퀴즈 GUI 완성. 정답 확인 및 다음 문제 자동 출제. 퀴즈 기록 반영. |
| **StatsFrame** | 완성 | 단어장·퀴즈 통계를 출력하는 GUI 완성. 정답률·단어 수 등 제공. |
