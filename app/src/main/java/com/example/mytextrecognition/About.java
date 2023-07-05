package com.example.mytextrecognition;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class About extends AppCompatActivity {
    TextView linkTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        linkTextView = findViewById(R.id.hyperlink1);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        linkTextView = findViewById(R.id.hyperlink2);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        linkTextView = findViewById(R.id.hyperlink3);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        linkTextView = findViewById(R.id.hyperlink4);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        linkTextView = findViewById(R.id.hyperlink5);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        linkTextView = findViewById(R.id.hyperlink6);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        linkTextView = findViewById(R.id.hyperlink7);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
