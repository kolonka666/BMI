package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private final String fileName = "Bmr.txt";
    EditText heightText;
    EditText weightText;
    EditText ageText;
    Spinner activityText;
    RadioGroup sexGroup;
    String sex;
    int coef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightText = findViewById(R.id.heightText);
        weightText = findViewById(R.id.weightText);
        ageText = findViewById(R.id.ageText);
        activityText = findViewById(R.id.activityText);
        sexGroup = findViewById(R.id.sexGroup);

        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.male:
                        sex = "мужчина";
                        coef = 5;
                        break;
                    case R.id.female:
                        coef = -161;
                        sex = "Женщина";
                }
            }
        });
    }

    public void onClick(View v){
        int height = 0, weight = 0, age = 0;
        float bmr = 0;
        try {
            height = Integer.parseInt(heightText.getText().toString());
            weight = Integer.parseInt(weightText.getText().toString());
            age = Integer.parseInt(ageText.getText().toString());

            bmr = (9.99f * weight) + (6.25f * height) - (4.29f * age) + coef;
            String activ = activityText.getItemAtPosition(activityText.getSelectedItemPosition())
                    .toString();

            String text = "Высота: " + height + "\n" +
                    "Вес: " + weight + "\n" +
                    "Возраст: " + age + "\n" +
                    "Пол: " + sex + "\n" +
                    "Активность: " + activ + "\n" +
                    "BMR: " + bmr;

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                fileOutputStream.write(text.getBytes());
                Toast.makeText(this, "Сохранено", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Ваш bmr " + bmr , Toast.LENGTH_LONG).show();

            }
            catch (Exception e) {
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show();
        }



    }


}