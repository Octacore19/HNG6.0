package com.chegsmania.hng60.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.chegsmania.hng60.R;
import com.chegsmania.hng60.utils.PreferenceUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView text, nameText, emailText, phoneNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.introTextview);
        nameText = findViewById(R.id.nameTextview);
        emailText = findViewById(R.id.emailTextview);
        phoneNumberText = findViewById(R.id.phoneNumberTextview);
        displayUserInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout_menu){
            startActivity(new Intent(this, AuthenticationActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayUserInfo(){
        List<String> user = PreferenceUtils.getUser();
        String email = user.get(0);
        String[] split = email.split("@");
        String username = split[0];
        String name = String.format("%s %s", user.get(2), user.get(3));
        text.setText(String.format("I am %s, master of the four elements [Water, Fire, Earth, and Air].", username));
        nameText.setText(name);
        emailText.setText(email);
        phoneNumberText.setText(user.get(4));

        Log.i("Firstname", user.get(3));
    }
}
