package com.chegsmania.hng60.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chegsmania.hng60.utils.FragmentInterface;
import com.chegsmania.hng60.R;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private FragmentInterface mInterface;
    private EditText emailText, passwordText;

    public LoginFragment() { }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        if (container != null){
            view = inflater.inflate(R.layout.fragment_login, container, false);
            initViews(view);
        }
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mInterface = (FragmentInterface) getActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signupPromptTextview:
                RegistrationFragment fragment = new RegistrationFragment();
                mInterface.switchSignInSignOut(fragment);
                break;
            case R.id.loginButton:
                loginUser();
                break;
        }
    }

    private void initViews(View view){
        emailText = view.findViewById(R.id.emailEdittext);
        passwordText = view.findViewById(R.id.passwordEdittext);
        Button loginBtn = view.findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(this);
        TextView signUpPromptText = view.findViewById(R.id.signupPromptTextview);
        signUpPromptText.setOnClickListener(this);
    }

    private void loginUser() {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            emailText.setError("Field required");
            emailText.requestFocus();
        } else if (!isEmailValid(email)){
            emailText.setError("Invalid email");
            emailText.requestFocus();
        } else if (TextUtils.isEmpty(password)){
            passwordText.setError("Field required");
            passwordText.requestFocus();
        } else if (!isPasswordValid(password)){
            passwordText.setError("Password length must be more than 4 characters");
            passwordText.requestFocus();
        } else
        mInterface.loginUser(email, password);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
