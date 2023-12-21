# 전자정부 API SpringFramework 예제

> 예제입니다. 단순 구동 가능한 웹서버이며 많은 수정이 필요합니다.

## 명칭

> `Eclpise`의 <u>`WorkSpace`</u> = `IntelliJ`의 <u>`Project`</u>

> `Eclpise`의 <u>`Project`</u> = `IntelliJ`의 <u>`Module`</u>

## 예제 환경

### IDE
- IntelliJ

### API TEST TOOL
- Postman

### TomcatServer
- 9.0.82

### JAVA Version
- 11.0.20
    ```
    openjdk 11.0.20.1 2023-08-24
    OpenJDK Runtime Environment (build 11.0.20.1+1-post-Ubuntu-0ubuntu122.04)
    OpenJDK 64-Bit Server VM (build 11.0.20.1+1-post-Ubuntu-0ubuntu122.04, mixed mode, sharing)
    ```

### 사용 가능 환경
- [ x ] Windows
- [ x ] Ubuntu(Linux)


## 적용 방법
1. 프로젝트와 모듈생성
2. 모듈에 맞게 `pom.xml` 수정
3. 해당 디렉토리를 모듈 디렉토리에 맞게 복사
4. 프로젝트구조 > 프로젝트설정(모듈) > `+` 클릭 > `Spring` > 적용
5. `MavenClean` > `MavenInstall`

## 디렉토리 구조
```
Project
    > Module_1
        > src
            > main
                > java
                    > egovframework
                    > custom_module
                        ...
                            > controller
                            > dao
                            > service
                            ...
                > resources
                    > custom_module
                        > mapper
                        > message
                        > spring
                        custom_module.properties
                > webapp
                    ...
        pom.xml
```

## 예제 결과
> 아래 링크를 Postman에서 GET 방식으로 전송합니다.
> `HEADER`에 `Content-Type: application/json` 을 꼭 추가합니다.

> 형식 : `http://localhost:[PORT]/[MODULE_NAME]/rest/example/[CUSTOM_TEXT]`

> 예 : `http://localhost:8085/egovSampleProject_war/rest/example/chb0110`

### 출력
```json
{
    "dataCnt": 1,
    "params": {
        "custom_value": "chb0110"
    },
    "resultList": [
        {
            "CV": "chb0110",
            "HELLO": "Hello"
        }
    ],
    "Result": "success"
}
```