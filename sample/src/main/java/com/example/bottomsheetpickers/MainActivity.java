package com.example.bottomsheetpickers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.philliphsu.bottomsheetpickers.time.BottomSheetTimePickerDialog;
import com.philliphsu.bottomsheetpickers.time.grid.GridTimePickerDialog;
import com.philliphsu.bottomsheetpickers.time.numberpad.NumberPadTimePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements BottomSheetTimePickerDialog.OnTimeSetListener {
    private static final String TAG = "MainActivity";

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView) findViewById(R.id.text);

        final RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int checkedId = group.getCheckedRadioButtonId();
                if (checkedId == R.id.choice_number_pad
                        || checkedId == R.id.choice_number_pad_dark) {
                    NumberPadTimePickerDialog dialog = NumberPadTimePickerDialog.newInstance(MainActivity.this);
                    dialog.setThemeDark(checkedId == R.id.choice_number_pad_dark);
                    dialog.show(getSupportFragmentManager(), TAG);
                } else if (checkedId == R.id.choice_grid_picker
                        || checkedId == R.id.choice_grid_picker_dark) {
                    Calendar now = Calendar.getInstance();
                    GridTimePickerDialog dialog = GridTimePickerDialog.newInstance(
                            MainActivity.this,
                            now.get(Calendar.HOUR_OF_DAY),
                            now.get(Calendar.MINUTE),
                            DateFormat.is24HourFormat(MainActivity.this));
                    dialog.setThemeDark(checkedId == R.id.choice_grid_picker_dark);
                    dialog.show(getSupportFragmentManager(), TAG);
                }
            }
        });
    }

    @Override
    public void onTimeSet(ViewGroup viewGroup, int hourOfDay, int minute) {
        Calendar cal = new java.util.GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        mText.setText("Time set: " + DateFormat.getTimeFormat(this).format(cal.getTime()));
    }
}
