package com.chegsmania.hng60.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.widget.Toast;

import com.chegsmania.hng60.utils.FragmentInterface;
import com.chegsmania.hng60.fragments.LoginFragment;
import com.chegsmania.hng60.R;
import com.chegsmania.hng60.utils.PreferenceUtils;

import java.util.List;

public class AuthenticationActivity extends AppCompatActivity implements FragmentInterface {

    private static final int FADE_IN_DURATION = 300;
    private static final int FADE_OUT_DURATION = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        LoginFragment fragment = new LoginFragment();
        initFragmentOnActivity(fragment, R.id.container);
        PreferenceUtils.initSharedPreferences(this);
    }

    public void initFragmentOnActivity(Fragment fragment, int containerId) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public void performFragmentTransition(Fragment nextFragment, int containerID){
        if (isDestroyed()) {
            return;
        }

        Fragment previousFragment = getSupportFragmentManager().findFragmentById(containerID);

        if (previousFragment != null && nextFragment != null) {
            Fade exitFade = new Fade();
            exitFade.setDuration(FADE_OUT_DURATION);
            previousFragment.setExitTransition(exitFade);

            Fade enterFade = new Fade();
            enterFade.setDuration(FADE_IN_DURATION);
            nextFragment.setEnterTransition(enterFade);

            getSupportFragmentManager().beginTransaction()
                    .replace(containerID, nextFragment)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void switchSignInSignOut(Fragment fragment) {
        performFragmentTransition(fragment, R.id.container);
    }

    @Override
    public void registerUser(String email, String password, String firstname, String lastname, String phone) {
        PreferenceUtils.registerUser(email, password, firstname, lastname, phone);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void loginUser(String email, String password) {
        List<String> user = PreferenceUtils.getUser();
        String userEmail = user.get(0);
        String userPassword = user.get(1);
        if (email.equals(userEmail) && password.equals(userPassword)){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show();
        }
    }
}
