# 🗒 커머스 만들기

이용자가 상품 판매와 구매를 동시에 할 수 있는 커머스 프로젝트입니다.

## ERD 
![image](https://github.com/BBuSong/cms-project1/assets/129375053/1d4ad7dc-86d9-4e1c-a6a1-2d29622c71e8)

## 프로젝트 기능 및 설계
- 회원가입 기능
  - 사용자는 회원가입을 할 수 있다. 일반적으로 모든 사용자는 회원가입시 USER 권한 (일반 권한)을 지닌다.
  - 회원가입시 아이디, 패스워드와 카드 번호를 입력받으며, 아이디는 unique 해야한다.

- 로그인 기능
  - 사용자는 로그인을 할 수 있다. 로그인시 회원가입때 사용한 아이디와 패스워드가 일치해야한다.
 

- 상품 등록 기능 
  - 로그인한 사용자는 상품을 등록할 수 있다.
  - 사용자는 상품 이름, 가격, 배송비를 작성할 수 있다.

- 상품 구매 기능
  - 로그인한 사용자는 상품을 구매할 수 있다. 
  - 회원가입 시 입력했던 카드 번호와 비밀번호가 입력한 카드 번호와 비밀번호가 일치하면 구매할 수 있다.

- 장바구니 추가 기능
  - 로그인한 사용자는 장바구니를 추가할 수 있다.

- 장바구니 조회 기능
  - 로그인한 사용자는 장바구니를 조회할 수 있다.

- 장바구니 삭제 기능
  - 로그인한 사용자는 장바구니를 삭제할 수 있다.
 
- 상품 검색 기능
  - 권한과 상관없이 모든 사용자는 상품을 검색할 수 있다.

- 실시간 인기 검색어 기능
  - 최근 이용자들이 많이 찾아 본 상품을 볼 수 있다.


## Trouble Shooting
[go to the trouble shooting section](doc/TROUBLE_SHOOTING.md)

### Tech Stack
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>
