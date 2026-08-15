# spring-boot-member-api

Spring Boot로 회원 CRUD REST API를 단계별로 구현한 학습 프로젝트입니다.
Controller / Service / Repository 계층을 분리하고 Spring Data JPA를 적용했으며, H2 실습을 거쳐 MySQL 8.4에 데이터를 영구 저장하는 과정까지 진행했습니다.

## 기술 스택

- Java 21
- Spring Boot 4.1.0
- Gradle
- Spring Web MVC
- Spring Data JPA
- H2 (학습 단계)
- MySQL 8.4.11

## 아키텍처

```text
Client -> Controller -> Service -> Repository -> JPA -> MySQL
```

- `Controller`: HTTP 요청과 응답 처리
- `Service`: 회원 CRUD 비즈니스 로직 처리
- `Repository`: JPA를 통한 데이터 접근
- `MySQL`: 애플리케이션과 분리된 영구 데이터 저장소

## API

| Method | Endpoint | 설명 |
| --- | --- | --- |
| `GET` | `/hello` | 서버 동작 확인용 문자열 응답 |
| `GET` | `/member` | 학습 초기 단일 `Member` JSON 응답 |
| `POST` | `/members` | 회원 등록 |
| `GET` | `/members` | 회원 전체 조회 |
| `GET` | `/members/{id}` | 회원 단건 조회 |
| `PUT` | `/members/{id}` | 회원 수정 |
| `DELETE` | `/members/{id}` | 회원 삭제 |

`GET /member` 응답 예시:

```json
{
  "id": 1,
  "name": "동민",
  "age": 34
}
```

## 구현 기능

- [x] Spring Boot 서버 실행
- [x] `GET /hello`
- [x] `Member` 객체 JSON 응답
- [x] 회원 전체 조회
- [x] 회원 단건 조회
- [x] 회원 등록
- [x] Controller / Service / Repository 계층 분리
- [x] JPA 연동
- [x] H2 연동
- [x] 회원 수정
- [x] 회원 삭제
- [x] MySQL 연동
- [x] MySQL 영구 저장 확인
- [ ] Docker 컨테이너화

## 데이터 저장 방식 변화

```text
초기: Repository -> ArrayList
      서버 재시작 시 데이터 소멸

중간: Repository -> JPA -> H2

최종: Repository -> JPA -> MySQL
      Spring Boot 재시작 후에도 데이터 유지 확인
```

## 실행 방법

Windows에서 다음 명령으로 애플리케이션을 실행합니다.

```powershell
.\gradlew.bat bootRun
```

실행 후 `http://localhost:8080/hello` 또는 회원 API 주소에서 응답을 확인할 수 있습니다.

## 실행 및 검증 화면

### 1. Spring Boot 서버와 조회 API

![Spring Boot 서버 실행](docs/images/01-springboot-server-running.png)

![회원 전체 조회](docs/images/04-members-list-json-response.png)

### 2. 회원 CRUD

![회원 등록](docs/images/07-member-post-json-response.png)

![회원 수정](docs/images/10-member-put-update-success.png)

![회원 삭제](docs/images/11-member-delete-success.png)

### 3. JPA와 데이터베이스 영속성

![JPA와 H2 저장 확인](docs/images/09-jpa-h2-member-persisted.png)

![MySQL 연결 성공](docs/images/12-mysql-connection-success.png)

![MySQL member 테이블 생성](docs/images/13-mysql-member-table-created.png)

Spring Boot를 재시작한 뒤 API와 MySQL에서 같은 회원 데이터가 유지되는 것을 확인했습니다.

![Spring Boot 재시작 후 회원 조회](docs/images/14-mysql-member-api-read-success.png)

![MySQL 직접 조회](docs/images/15-mysql-direct-select-success.png)

그 밖의 단계별 캡처는 [`docs/images/`](docs/images/)에서 확인할 수 있습니다.

## 트러블슈팅

### Java 8이 우선 적용되어 Spring Boot가 실행되지 않음

기존 Java 8이 먼저 인식되어 Java 21이 필요한 Spring Boot 프로젝트를 실행할 수 없었습니다. Temurin JDK 21을 설치하고 `JAVA_HOME`과 Windows `PATH`를 수정한 뒤, IntelliJ 터미널을 다시 열어 Java 21 적용을 확인해 해결했습니다.

### MySQL 명령을 찾지 못함

MySQL 설치 후 터미널에서 `mysql` 명령을 인식하지 못했습니다. 아래 경로를 Windows `PATH`에 추가하고 새 터미널에서 명령 실행을 확인해 해결했습니다.

```text
C:\Program Files\MySQL\MySQL Server 8.4\bin
```

## 보안 설정

MySQL 비밀번호와 같은 비밀값은 README나 Git 저장소에 직접 기록하지 않습니다. 로컬 환경에서는 환경변수 등으로 값을 주입하고, 공개 가능한 설정에는 다음과 같이 변수 참조만 사용합니다.

```properties
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}
```

실제 비밀번호가 들어 있는 별도 로컬 설정 파일을 사용할 경우 해당 파일은 `.gitignore`에 추가해야 합니다.

## 다음 단계

1. Docker 컨테이너화
2. 테스트 및 예외 처리 보강
3. AWS / Kubernetes 배포
