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

public class RegistrationFragment extends Fragment implements View.OnClickListener{

    private FragmentInterface mInterface;
    private EditText emailText, passwordText, confirmPasswordText, firstNameText, lastNameText, phoneNumbertext;

    public RegistrationFragment() { }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        if (container != null){
            view = inflater.inflate(R.layout.fragment_registration, container, false);
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
            case R.id.loginPromptTextview:
                LoginFragment fragment = new LoginFragment();
                mInterface.switchSignInSignOut(fragment);
                break;
            case R.id.signupButton:
                registerUser();
                break;
        }
    }

    private void initViews(View view){
        emailText = view.findViewById(R.id.emailEdittext);
        passwordText = view.findViewById(R.id.passwordEdittext);
        confirmPasswordText = view.findViewById(R.id.confirmPasswordEdittext);
        firstNameText = view.findViewById(R.id.firstnameEdittext);
        lastNameText = view.findViewById(R.id.lastnameEdittext);
        phoneNumbertext = view.findViewById(R.id.phoneNumberEdittext);
        Button signupBtn = view.findViewById(R.id.signupButton);
        signupBtn.setOnClickListener(this);
        TextView loginPromptText = view.findViewById(R.id.loginPromptTextview);
        loginPromptText.setOnClickListener(this);
    }

    private void registerUser() {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String confirmPassword = confirmPasswordText.getText().toString().trim();
        String firstname = firstNameText.getText().toString().trim();
        String lastname = lastNameText.getText().toString().trim();
        String phone = phoneNumbertext.getText().toString().trim();
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
        } else if (TextUtils.isEmpty(confirmPassword)){
            confirmPasswordText.setError("Field required");
            confirmPasswordText.requestFocus();
        } else if (!confirmPassword.equals(password)){
            confirmPasswordText.setError("Password does not match");
            confirmPasswordText.requestFocus();
        } else
            mInterface.registerUser(email, password, firstname, lastname, phone);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
