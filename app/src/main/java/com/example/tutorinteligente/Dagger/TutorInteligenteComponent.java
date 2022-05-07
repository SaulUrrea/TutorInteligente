package com.example.tutorinteligente.Dagger;

import com.example.tutorinteligente.Application.TutorInteligenteApplication;
import com.example.tutorinteligente.Main.Presenter.PresenterHome;
import com.example.tutorinteligente.Main.View.Activity.EditProfileActivity;
import com.example.tutorinteligente.Main.View.Activity.ProfileActivity;
import com.example.tutorinteligente.Session.Presenter.PresenterSession;
import com.example.tutorinteligente.Session.View.Activity.LoginActivity;
import com.example.tutorinteligente.Session.View.Fragments.LoginFragment;
import com.example.tutorinteligente.Session.View.Fragments.RegisterFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = TutorInteligenteModule.class)
public interface TutorInteligenteComponent {
    void inject(TutorInteligenteApplication tutorInteligenteApplication);
    void inject(PresenterSession presenterSession);
    void inject(LoginActivity loginActivity);
    void inject(PresenterHome presenterHome);
    void inject(ProfileActivity homeActivity);
    void inject(EditProfileActivity editProfileActivity);
    void inject(RegisterFragment registerFragment);
    void inject(LoginFragment loginFragment);
}
