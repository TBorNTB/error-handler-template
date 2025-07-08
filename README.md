# error-handler-template
공통적으로 사용할 수 있는 에러 처리 모듈입니다. 
git pull 받은 다음 코드를 살펴보고 각자의 프로젝트에서 사용하시면 될거같아요 
궁금하신 부분은 개인적으로 연락해주시면 되겠습니다. 

### 사용법

1. 아래와 같이 new Application() 을 예외를 발생시킨다. ErrorCode는 자신의 서비스에 맞게 계속 추가하시면 된다.

```
 @GetMapping("/health")
    public String health() {
        throw new ApiException(ErrorCode.BAD_REQUEST, "현재 보내신 요청을 잘못된 요청입니다.");
    }
```

2. 에러코드 관련해서는 아래와 같이 계속 추가하시면 됩니다. 현재 내용은 지워도 됩니다. 또한 각자만의 프로젝트에 맞는 에러code 클래스를 새로 만들어서
   ErrorCodeIfs를 구현받으면 확장에 용이합니다

```
@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeIfs{

    OK(200,200,"성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),1234,"잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),500,"서버 에러"),

    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(),512,"Null Pointer"),
    MULTI_REQUEST(405,405,"하루 한번만 요청 가능합니다"),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}

```
3. 아래 그림은 이 에러를 발생시켰을 때 반환하는 경우입니다.
```
 throw new ApiException(ErrorCode.BAD_REQUEST, "현재 보내신 요청을 잘못된 요청입니다."); 
```

ErrorCode.BAD_REQUEST는 실제로 400번 에러코드를 반환합니다.그리고 
**예시를 위해 설정한겁니다. 
현재 BAD_REQUEST관련해서 우리 서비스는 1234코드를 반환할거야!! 라고 정해서 아래와 같이 설정을 하고 에러를 발생시키면
에러 처리기에서 가공하여 아래와 같이 에러를 반환합니다.
```
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),1234,"잘못된 요청"),
```

![image](https://github.com/user-attachments/assets/45ed507e-e0c2-4d1b-b1c7-2738d8446bc1)



