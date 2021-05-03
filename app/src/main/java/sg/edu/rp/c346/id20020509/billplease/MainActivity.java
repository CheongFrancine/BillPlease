package sg.edu.rp.c346.id20020509.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //Step 1: Declare the field variables
    TextView tvAmount;
    TextView tvPaxNum;
    TextView tvDiscount;
    TextView tvTotal;
    TextView tvSplitPay;
    EditText etAmount;
    EditText etPaxNum;
    EditText etDiscount;
    ToggleButton tbSVS;
    ToggleButton tbGST;
    RadioGroup rgPayMode;
    Button bSplit;
    Button bReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2: Link the field variables to UI components in layout
        tvAmount = findViewById(R.id.textView);
        tvPaxNum = findViewById(R.id.textView2);
        tvDiscount = findViewById(R.id.textView3);
        tvTotal = findViewById(R.id.textView4);
        tvSplitPay = findViewById(R.id.textView5);
        etAmount = findViewById(R.id.editText);
        etPaxNum = findViewById(R.id.editText2);
        etDiscount = findViewById(R.id.editText3);
        tbSVS = findViewById(R.id.toggleButton);
        tbGST = findViewById(R.id.toggleButton2);
        rgPayMode = findViewById(R.id.radioGroup);
        bSplit = findViewById(R.id.button);
        bReset = findViewById(R.id.button2);

        bSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double doubleAmt = Double.parseDouble(tvAmount.getText().toString().trim());
                int intPaxNum = Integer.parseInt(tvPaxNum.getText().toString().trim());
                int intDiscount = Integer.parseInt(etDiscount.getText().toString().trim());
                if (doubleAmt != 0 && intPaxNum != 0) {
                    if (tbSVS.isChecked() && tbGST.isChecked()) {
                        doubleAmt = doubleAmt * 1.17;
                    }
                    else if (tbSVS.isChecked() && !tbGST.isChecked()) {
                        doubleAmt = doubleAmt * 1.1;
                    }
                    else if (!tbSVS.isChecked() && tbGST.isChecked()) {
                        doubleAmt = doubleAmt * 1.07;
                    }
                    else {
                        doubleAmt = doubleAmt;
                    }
                }
                else {
                    tvTotal.setText("Invalid input");
                    tvSplitPay.setText("");
                }

                if (intDiscount != 0) {
                    doubleAmt = doubleAmt * (1 - (intDiscount / 100));
                }

                double doubleSplit = doubleAmt / intPaxNum;
                String stringSplit = String.format("%2f", doubleSplit);
                tvTotal.setText("Total Bill: $" + doubleAmt);

                if (rgPayMode.getCheckedRadioButtonId() == R.id.radioButton) {
                    tvSplitPay.setText("Each Pays: $" + stringSplit + " in cash");
                } else {
                    tvSplitPay.setText("Each Pays: $" + stringSplit + " via PayNow to 912345678");
                }

            }
          }
        );

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.setText("");
                etPaxNum.setText("");
                tbSVS.setChecked(false);
                tbGST.setChecked(false);
                etDiscount.setText("");
                rgPayMode.check(R.id.radioButton);
                tvTotal.setText("Total Bill: $");
                tvSplitPay.setText("");
            }
        });

    }
}