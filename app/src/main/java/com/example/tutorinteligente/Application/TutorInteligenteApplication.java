package com.example.tutorinteligente.Application;


import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.tutorinteligente.Dagger.DaggerTutorInteligenteComponent;
import com.example.tutorinteligente.Dagger.TutorInteligenteComponent;
import com.example.tutorinteligente.Dagger.TutorInteligenteModule;

public class TutorInteligenteApplication extends MultiDexApplication {

    private static TutorInteligenteApplication tutorInteligenteApplication;
    private static TutorInteligenteComponent tutorInteligenteComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        tutorInteligenteApplication=this;
        tutorInteligenteComponent = DaggerTutorInteligenteComponent.builder()
                .tutorInteligenteModule(new TutorInteligenteModule(this))
                .build();
        tutorInteligenteComponent.inject(this);

    }

    public static TutorInteligenteComponent getTutorInteligenteComponent(){
        return tutorInteligenteComponent;
    }
    public static TutorInteligenteApplication getTutorInteligenteApplication(){
        return tutorInteligenteApplication;
    }

}
