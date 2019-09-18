package com.chegsmania.hng60.utils;

import androidx.fragment.app.Fragment;

public interface FragmentInterface {
    void switchSignInSignOut(Fragment fragment);
    void registerUser(String email, String password, String firstname, String lastname, String phoneNumber);
    void loginUser(String email, String password);
}
