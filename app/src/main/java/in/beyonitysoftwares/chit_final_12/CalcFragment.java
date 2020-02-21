package in.beyonitysoftwares.chit_final_12;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalcFragment extends Fragment {

    private Spinner spinner1;
    TextView answer, spinnerText;
    EditText editNum;
    String[] amounts = {"₹ \t 50000", "₹ \t 75000", "₹ \t 100000", "₹ \t 200000", "₹ \t 300000", "₹ \t 400000"};
    TextView ch, el, pa;
    FloatingActionButton share;

    public CalcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calc, container, false);
        ch= (TextView) view.findViewById(R.id.chittxt);
        el= (TextView) view.findViewById(R.id.elam);
        pa= (TextView) view.findViewById(R.id.payable) ;
        share = (FloatingActionButton) view.findViewById(R.id.share);
        answer = (TextView) view.findViewById(R.id.answer);
        spinnerText = (TextView) view.findViewById(R.id.spinnerText);
        ArrayAdapter ad = new ArrayAdapter(getContext(), R.layout.spinner_text, amounts);

        ad.setDropDownViewResource(R.layout.spinner_text);
        spinner1 = (Spinner) view.findViewById(R.id.chitAmount);
        spinner1.setAdapter(ad);

        editNum = (EditText) view.findViewById(R.id.editnum);
        editNum.setText("0");

        editNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager inputManager = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    String value = spinner1.getSelectedItem().toString();
                    value = value.replace("₹ \t ", "");
                    Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
                    Double chitAmount = Double.parseDouble(value);
                    Double input = Double.parseDouble(editNum.getText().toString());
                    el.setText(editNum.getText());
                    if (!(input == 0)) {
                        //Double output = (((chitAmount * 3) / 100 + chitAmount) - input) / 20;
                        Double output = (((chitAmount * 3) / 100 + chitAmount) - input) / 12;
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
                ch.setText(value);
                Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
                Double chitAmount = Double.parseDouble(value);
                Double input = Double.parseDouble(editNum.getText().toString());
                if (!(input == 0)) {
                    Double output = (((chitAmount * 3) / 100 + chitAmount) - input) / 12;
                    answer.setText(String.valueOf(output));
                    pa.setText(String.valueOf(output));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
        return view;

    }



//add items into spinner dynamically


//get the selected dropdown list value

}

