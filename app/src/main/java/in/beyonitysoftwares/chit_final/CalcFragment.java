package in.beyonitysoftwares.chit_final;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalcFragment extends Fragment {

    private Spinner spinner1;
    TextView answer, spinnerText;
    EditText editNum;
    String[] amoutns = {"₹ \t 50000", "₹ \t 75000", "₹ \t 100000", "₹ \t 200000", "₹ \t 300000", "₹ \t 400000"};

    public CalcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calc, container, false);
        answer = (TextView) (R.id.answer);
        spinnerText = (TextView) findViewById(R.id.spinnerText);
        ArrayAdapter ad = new ArrayAdapter(this, R.layout.spinner_text, amoutns);
        ad.setDropDownViewResource(R.layout.spinner_text);
        spinner1 = (Spinner) findViewById(R.id.chitAmount);
        spinner1.setAdapter(ad);

        editNum = (EditText) findViewById(R.id.editnum);
        editNum.setText("0");
        editNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    String value = spinner1.getSelectedItem().toString();
                    value = value.replace("₹ \t ", "");
                    Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                    Double chitAmount = Double.parseDouble(value);
                    Double input = Double.parseDouble(editNum.getText().toString());
                    if (!(input == 0)) {
                        Double output = (((chitAmount * 3) / 100 + chitAmount) - input) / 20;
                        answer.setText(String.valueOf(output));
                    }

                }
                return true;
            }

        });


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = spinner1.getSelectedItem().toString();
                value = value.replace("₹ \t ", "");
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                Double chitAmount = Double.parseDouble(value);
                Double input = Double.parseDouble(editNum.getText().toString());
                if (!(input == 0)) {
                    Double output = (((chitAmount * 3) / 100 + chitAmount) - input) / 20;
                    answer.setText(String.valueOf(output));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    } catch (Exception e) {
        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
    }
//add items into spinner dynamically


//get the selected dropdown list value
}

}