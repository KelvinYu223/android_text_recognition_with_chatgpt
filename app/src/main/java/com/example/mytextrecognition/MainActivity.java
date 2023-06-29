package com.example.mytextrecognition;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView detectedText;
    Button btn_detect,pick_image;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        imageView = findViewById(R.id.img);
        detectedText = findViewById(R.id.detectedText);
        btn_detect = findViewById(R.id.button_detect);
        pick_image = findViewById(R.id.pickImage);
        pick_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MainActivity.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 4000)
                        .start();
            }
        });
        btn_detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detect();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        Uri imageUri = data.getData();
        if (imageUri!=null){
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "Please select an image or take picture....", Toast.LENGTH_SHORT).show();
        }
    }

    public void detect() {
        if (imageBitmap !=null){
            InputImage image = InputImage.fromBitmap(imageBitmap, 0);
            TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
            Task<Text> result =
                    recognizer.process(image)
                            .addOnSuccessListener(new OnSuccessListener<Text>() {
                                @Override
                                public void onSuccess(Text visionText) {
                                    Toast.makeText(MainActivity.this, "Text detected successful....", Toast.LENGTH_SHORT).show();
                                    detectedText.setText(visionText.getText());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "Failed to detect text.....", Toast.LENGTH_SHORT).show();
                                }
                            });
        }
        else {
            Toast.makeText(MainActivity.this, "Failed to detected text.....", Toast.LENGTH_SHORT).show();
        }

    }
}