package com.example.electricitybill2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUnitsUsed, etRebatePercentage;
    Button btnCalculate, btnClear;
    TextView result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUnitsUsed = findViewById(R.id.etUnitsUsed);
        etRebatePercentage = findViewById(R.id.etRebatePercentage);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        result1 = findViewById(R.id.result1);


        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item){
        int id = item.getItemId();
        if (id == R.id.sub)
        {
            Intent i = new Intent(MainActivity.this,Menu1.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnClear) {
            etUnitsUsed.setText("");
            etRebatePercentage.setText("");
            result1.setText("");
            return;
        }

        switch (view.getId()) {
            case R.id.btnCalculate:
                try {
                    int unitsUsed = Integer.parseInt(etUnitsUsed.getText().toString());
                    double rebatePercentage = Double.parseDouble(etRebatePercentage.getText().toString());

                    double totalCharges = 0.0;

                    if (unitsUsed <= 200) {
                        totalCharges = unitsUsed * 0.218;
                    } else if (unitsUsed <= 300) {
                        totalCharges = (200 * 0.218) + ((unitsUsed - 200) * 0.334);
                    } else if (unitsUsed <= 600) {
                        totalCharges = (200 * 0.218) + (100 * 0.334) + ((unitsUsed - 300) * 0.516);
                    } else {
                        totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((unitsUsed - 600) * 0.546);
                    }

                    double finalCost = totalCharges - (totalCharges * rebatePercentage);

                    String result = String.format("Total Charges: RM %.2f", totalCharges, finalCost);
                    result1.setText(result);

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid input: please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }

}