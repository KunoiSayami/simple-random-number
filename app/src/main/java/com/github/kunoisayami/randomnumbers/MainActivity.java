package com.github.kunoisayami.randomnumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RandomNumberMain";

    private TextView resultView, versionView;

    private EditText etNumber, etText;


    private Button btnReset, btnStart;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        resultView = findViewById(R.id.textResult);
        etNumber = findViewById(R.id.editNumber);
        etText = findViewById(R.id.editTexts);
        btnReset = findViewById(R.id.buttonReset);
        btnStart = findViewById(R.id.buttonStart);
        versionView = findViewById(R.id.textVersion);
        // https://stackoverflow.com/a/21119027
        versionView.setText("version: " + BuildConfig.VERSION_NAME);

        radioGroup = findViewById(R.id.radioGroup);

        etText.setEnabled(false);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            etNumber.setEnabled(R.id.radioNumber == checkedId);
            etText.setEnabled(R.id.radioString == checkedId);

            if (R.id.radioNumber == checkedId) {
                etNumber.requestFocus();
            } else {
                etText.requestFocus();
            }

        });

        btnStart.setOnClickListener(v -> {
            Random random = new Random();
            if (etText.isEnabled()) {

                var group = etText.getText().toString().split(" ");
                resultView.setText(group[random.nextInt(group.length)]);
            } else {
                if (etNumber.getText().length() > 0) {
                    var upper = Integer.parseInt(etNumber.getText().toString());

                    if (upper <= 0) {
                        Toast.makeText(this, "Should input number> 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resultView.setText(Integer.toString(random.nextInt(upper) + 1));

                } else {
                    Toast.makeText(this, "Should enter a number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        resultView.setOnClickListener(v -> {
            var text = resultView.getText();
            if (text.toString().isEmpty()) {
                return;
            }
            var clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Result", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        });

        btnReset.setOnClickListener(v -> {
            etText.setText("");
            etNumber.setText("1");
            resultView.setText("");
        });

        versionView.setOnLongClickListener(v -> {
            var intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KunoiSayami/simple-random-number"));
            startActivity(intent);
            return false;
        });
    }
}