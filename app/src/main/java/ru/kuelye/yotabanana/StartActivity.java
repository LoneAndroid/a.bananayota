package ru.kuelye.yotabanana;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by Alexey Leshchuk (ale@) on 2/4/2017.
 */

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // настраиваем действия для галочки
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final Button connectButton = (Button) findViewById(R.id.connect_button);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { // isChecked содержит true, если галочка поставлена, и false - в противном случае
                if (isChecked == true) { // если галочка стоит
                    connectButton.setEnabled(true); // включаем кнопку
                } else { // иначе
                    connectButton.setEnabled(false); // выключаем кнопку
                }
            }
        });
        // настраиваем действие по нажатию на кнопку
        final Context context = this;
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // запускаем главную активность
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                // закрываем стартовую активность
                finish();
            }
        });
    }

}
