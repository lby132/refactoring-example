package me.whiteship;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //Runtime환경까지 가져오기. 이 옵션 없으면 아무기능없음 그냥 주석이나 마찬가지.
@Target({ElementType.TYPE, ElementType.FIELD})  // 이 인터페이스를 붙이는걸 제한할 수 있다.
@Inherited
public @interface MyAnnotation {

    String value() default "lby";

    int number() default 100;
}
