package ru.kuelye.yotabanana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int minutes; // количество минут
    boolean smsEnabled; // содержит true, если подключены безлимитные SMS, и false - если не подключены
    int roubles; // стоимость пакета в рублях
    TextView roublesTextView; // текстовое поле для стоимости пакета в рублях

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим текстовое поле для стоимость пакета в рублях
        roublesTextView = (TextView) findViewById(R.id.priceTextView);
        // настраиваем действие при движении ползунка
        SeekBar minutesSeekBar = (SeekBar) findViewById(R.id.seekBar);
        minutesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) { // progress содержит позицию ползунка, 0 - крайняя левая
                minutes = progress * 200 + 200; // считаем минуты и сохраняем в глобальную переменную
                updateRoubles(); // обновляем рубли
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        // настраиваем действие при переключении переключателя
        Switch smsSwitch = (Switch) findViewById(R.id.switch1);
        smsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean enabled) { // enabled содержит true, если переключатель включён, false - иначе
                smsEnabled = enabled; // сохраняем в глобальную переменную
                updateRoubles(); // обновляем рубли
            }
        });
    }

    /**
     * Метод считает рубли и отображает их в текстовое поле.
     */
    private void updateRoubles() {
        // считаем
        roubles = minutes / 2;
        if (smsEnabled == true) {
            roubles += 50;
        }
        // отображаем
        roublesTextView.setText(String.valueOf(roubles));
    }


}
