package com.example.exchangerates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.exchangerates.FirstFragment.FirstFragment;
import com.example.exchangerates.SecondFragment.SecondFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linearLayout1, new FirstFragment());
        fragmentTransaction.replace(R.id.linearLayout2, new SecondFragment());
        fragmentTransaction.commit();

    }
}