# todolist-test

투두리스트 제작

1. 사용한 기술 및 구조
    * MVC 패턴 사용
    * VIEW 템플릿으로는 타임리프 사용
    * Spring JPA 사용
    * 현재 H2 database 를 기본적으로 사용하고 있음(테스트시 H2 database 1.4.200버전 설치추천)

2. 서비스 흐름(todolist-usecase.svg 참고)
    1. 회원가입
    2. 로그인
    3. 회원탈퇴(탈퇴된 회원 ID로는 재가입 불가)
    4. TODO 리스트 작성
    5. TODO 리스트 조회(가장 최근, 전체 목록 조회 가능)
    6. TODO 리스트 상태 변경

3. 풀 테스트 했을 때 이름 변경되는지