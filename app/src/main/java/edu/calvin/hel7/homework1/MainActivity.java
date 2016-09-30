package edu.calvin.hel7.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private EditText value1;
    private EditText value2;
    private Spinner spinner;
    private Button calculate;
    private TextView result;
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value1 = (EditText) findViewById(R.id.valueEditText1);
        value2 = (EditText) findViewById(R.id.valueEditText2);
        spinner = (Spinner) findViewById(R.id.spinner);
        calculate = (Button) findViewById(R.id.calculateButton);
        result = (TextView) findViewById(R.id.resultTextView);

        calculate.setOnClickListener(this);

        savedValues = getSharedPreferences( "SavedValues", MODE_PRIVATE);

    }

    @Override
    public void onPause() {
        Editor editor = savedValues.edit();
        editor.putString( "value1", value1.getText().toString() );
        editor.putString( "value2", value2.getText().toString() );
        editor.putInt( "spinner", spinner.getSelectedItemPosition() );
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        value1.setText( savedValues.getString("value1", ""));
        value2.setText( savedValues.getString("value2", ""));
        spinner.setSelection( savedValues.getInt("spinner", 0));

    }

    @Override
    public void onClick(View v) {

        Integer intValue1 = Integer.valueOf( value1.getText().toString() );
        Integer intValue2 = Integer.valueOf( value2.getText().toString() );
        Integer calculation;

        if ( spinner.getSelectedItem().toString().equals( "+" ) ) {
            calculation = intValue1 + intValue2;
        }
        else if ( spinner.getSelectedItem().toString().equals( "-" ) ) {
            calculation = intValue1 - intValue2;
        }
        else if ( spinner.getSelectedItem().toString().equals( "*" ) ) {
            calculation = intValue1 * intValue2;
        }
        else {
            calculation = intValue1 / intValue2;
        }

        result.setText( calculation.toString() );
    }

}
