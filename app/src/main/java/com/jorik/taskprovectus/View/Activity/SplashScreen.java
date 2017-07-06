package com.jorik.taskprovectus.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent moveRandUsersIntent = new Intent(this, RandUsersActivity.class);
    startActivity(moveRandUsersIntent);
    finish();
  }
}
