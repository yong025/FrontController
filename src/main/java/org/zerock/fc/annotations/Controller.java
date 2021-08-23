package org.zerock.fc.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//클래스 선언부
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

    String value();
}
