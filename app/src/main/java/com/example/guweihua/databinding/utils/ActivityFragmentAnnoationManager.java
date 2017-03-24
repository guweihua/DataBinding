package com.example.guweihua.databinding.utils;

import com.example.guweihua.databinding.annoation.ActivityFragmentAnnoation;

import java.util.IllegalFormatCodePointException;



/**
 * Created by guweihua on 2017/3/23.
 */

public class ActivityFragmentAnnoationManager {

    public static int check(Object clazz) {
        boolean annotationPresent = clazz.getClass().isAnnotationPresent(ActivityFragmentAnnoation.class);
        if (!annotationPresent){
            throw new IllegalStateException("Activity必须有注解");
        }
        ActivityFragmentAnnoation annotation = clazz.getClass().getAnnotation(ActivityFragmentAnnoation.class);
        return annotation.contentId();
    }
}
