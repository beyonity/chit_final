package in.beyonitysoftwares.chit_final_12;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.text.DecimalFormat;

import static androidx.constraintlayout.widget.Constraints.TAG;


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
    DecimalFormat df2 = new DecimalFormat("#.##");

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

     /*   editNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
                        answer.setText(String.valueOf(df2.format(output)));
                        pa.setText(String.valueOf(df2.format(output)));
                    }

                }
                return true;
            }


        });*/


        editNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0) {
                    String value = spinner1.getSelectedItem().toString();
                    value = value.replace("₹ \t ", "");
                    Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
                    Double chitAmount = Double.parseDouble(value);
                    Double input = Double.parseDouble(editNum.getText().toString());
                    el.setText(editNum.getText());
                    if (!(input == 0)) {
                        //Double output = (((chitAmount * 3) / 100 + chitAmount) - input) / 20;
                        Double output = (((chitAmount * 3) / 100 + chitAmount) - input) / 12;
                        answer.setText(String.valueOf(df2.format(output)));
                        pa.setText(String.valueOf(df2.format(output)));
                    }
                }else {
                    el.setText("0");
                    pa.setText("0");
                    answer.setText("0");
                }

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
                el.setText(editNum.getText());
                if (!(input == 0)) {
                    Double output = (((chitAmount * 3) / 100 + chitAmount) - input) / 12;
                    answer.setText(String.valueOf(df2.format(output)));
                    pa.setText(String.valueOf(df2.format(output)));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+editNum.getText().toString().equals("0"));
                if(!editNum.getText().toString().equals("0")&&editNum.getText().length()!=0) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Chit: "+ch.getText().toString()+"\nElam: "+el.getText().toString()+" \nPayable: "+pa.getText().toString());
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);

                }else {
                    Toast.makeText(getContext(),"Please enter elam amount",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;

    }



//add items into spinner dynamically


//get the selected dropdown list value

}

