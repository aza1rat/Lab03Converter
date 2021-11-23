package com.example.lab03_converterkashitsin;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import my.functions.InfoConverter;

public class MainActivity extends AppCompatActivity {

    Spinner SP_From;
    Spinner SP_To;
    EditText In_Value;
    TextView TV_Result;
    RadioGroup RB_S_Group;
    ArrayAdapter <InfoConverter> adapter_dlina;
    ArrayAdapter <InfoConverter> adapter_plosh;
    ArrayAdapter <InfoConverter> adapter_massa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SP_From = findViewById(R.id.Spinner_From);
        SP_To = findViewById(R.id.Spinner_To);
        In_Value = findViewById(R.id.Input_Value);
        TV_Result = findViewById(R.id.TV_Result);
        RB_S_Group = findViewById(R.id.RB_Group);

        adapter_dlina = new ArrayAdapter <InfoConverter>(this, android.R.layout.simple_list_item_1);
        adapter_dlina.add(new InfoConverter(1.0, "mm"));
        adapter_dlina.add(new InfoConverter(0.1, "cm"));
        adapter_dlina.add(new InfoConverter(0.001, "m"));
        adapter_dlina.add(new InfoConverter(0.000001, "km"));

        adapter_plosh = new ArrayAdapter <InfoConverter>(this, android.R.layout.simple_list_item_1);
        adapter_plosh.add(new InfoConverter(1.0, "кв. mm"));
        adapter_plosh.add(new InfoConverter(0.01, "кв. cm"));
        adapter_plosh.add(new InfoConverter(0.0001, "кв. дц"));
        adapter_plosh.add(new InfoConverter(0.000001, "кв. m"));

        adapter_massa = new ArrayAdapter <InfoConverter>(this, android.R.layout.simple_list_item_1);
        adapter_massa.add(new InfoConverter(1.0, "г"));
        adapter_massa.add(new InfoConverter(0.001, "кг"));
        adapter_massa.add(new InfoConverter(0.00001, "ц"));
        adapter_massa.add(new InfoConverter(0.000001, "т"));
        SP_From.setAdapter(adapter_dlina);
        SP_To.setAdapter(adapter_dlina);
    }

    public  void selectSI(View v)
    {
        switch (RB_S_Group.getCheckedRadioButtonId())
        {
            case R.id.RB_Dlina:
                SP_From.setAdapter(adapter_dlina);
                SP_To.setAdapter(adapter_dlina);break;
            case R.id.RB_Ploshad:
                SP_From.setAdapter(adapter_plosh);
                SP_To.setAdapter(adapter_plosh);break;
            case R.id.RB_Massa:
                SP_From.setAdapter(adapter_massa);
                SP_To.setAdapter(adapter_massa);break;
        }
    }
    public void doConvert(View v)
    {
        String str_num = In_Value.getText().toString();
        if (str_num.isEmpty())
        {
            Toast.makeText(this,"Пустое значение",Toast.LENGTH_LONG).show();
            return;
        }
        double num = 0.0;
        try {
            num = Double.parseDouble(str_num);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this,"Введено неправильное значение",Toast.LENGTH_LONG).show();
            return;
        }
        InfoConverter arad_from = (InfoConverter) SP_From.getSelectedItem();
        InfoConverter arad_to = (InfoConverter) SP_To.getSelectedItem();
        double rez = (double) num / arad_from.k * arad_to.k;
        TV_Result.setText(String.valueOf(rez));
    }
}