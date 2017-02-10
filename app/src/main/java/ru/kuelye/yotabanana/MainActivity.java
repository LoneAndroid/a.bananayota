package ru.kuelye.yotabanana;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        // заполняем ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Баланс 350 \u20BD");

        // находим текстовые поля
        roublesTextView = (TextView) findViewById(R.id.priceTextView);
        final TextView minutesTextView = (TextView) findViewById(R.id.minutesTextView);
        // настраиваем действие при движении ползунка
        SeekBar minutesSeekBar = (SeekBar) findViewById(R.id.seekBar);
        minutesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) { // progress содержит позицию ползунка, 0 - крайняя левая
                minutes = progress * 200 + 200; // считаем минуты и сохраняем в глобальную переменную
                minutesTextView.setText(minutes + " минут");
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

        // устанавливаем начальные значения
        minutes = 600;
        smsEnabled = false;
        minutesSeekBar.setProgress(2);
        smsSwitch.setChecked(false);
        updateRoubles();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // создаём меню
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) { // если нажали на
            case R.id.char_item: // кнопку перехода в чат
                // переходим туда
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
                // возвращаем true, мол, нажатие обработано
                return true;
            default:
                return true;
        }
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
