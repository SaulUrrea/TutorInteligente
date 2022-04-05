package com.example.tutorinteligente.Dagger;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.Presenter.PresenterHome;
import com.example.tutorinteligente.Session.Presenter.PresenterSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TutorInteligenteModule {
    TutorInteligenteApplication application;

    public TutorInteligenteModule(TutorInteligenteApplication Tutorapplication){
        this.application=Tutorapplication;
    }

    @Provides
    @Singleton
    TutorInteligenteApplication application() { return application; }

    @Provides
    @Singleton
    PresenterSession presenterSessionManager() { return new PresenterSession(this.application); }

    @Provides
    @Singleton
    PresenterHome presenterHomeManager() { return new PresenterHome(this.application); }

}
