package com.example.library.entity;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-09-05 20:04
 * @depiction ：自定义注解
 */

@Target({
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface PopupViewTrack {
    String trackEventType();
    String pageId();
    String pageDesc();

}
