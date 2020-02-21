package in.beyonitysoftwares.chit_final_12;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.wafflecopter.multicontactpicker.ContactResult;
import com.wafflecopter.multicontactpicker.MultiContactPicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class addChit extends AppCompatActivity {
    Calendar myCalendar;
    EditText datePicker;
    Spinner setchitAmount;
    String SelectedValue;
    ArrayList<String> contactsName;
    ArrayAdapter contactsadapter;
    List<ContactResult> results;
    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_chit);
        String[] amounts = {getResources().getString(R.string.fifty),getResources().getString(R.string.seventyfive),getResources().getString(R.string.onelakh),getResources().getString(R.string.twolakh),getResources().getString(R.string.threeolakh),getResources().getString(R.string.fourlakh)};
        contactsName = new ArrayList<>();
       contactsadapter = new ArrayAdapter<String>(this,
                    R.layout.contacts_list, contactsName);
            ListView listView = (ListView) findViewById(R.id.addedmemeberslist);
            listView.setAdapter(contactsadapter);
        SelectedValue = getString(R.string.fifty);
        setchitAmount = (Spinner) findViewById(R.id.selectchitamount);
        ArrayAdapter<String> chitAd = new ArrayAdapter(getApplicationContext(),R.layout.spinner_text2,amounts);
        setchitAmount.setAdapter(chitAd);
        setchitAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedValue = chitAd.getItem(position);
                Toast.makeText(addChit.this, "selected item = "+SelectedValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        myCalendar = Calendar.getInstance();
        datePicker = (EditText) findViewById(R.id.datePicker);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(addChit.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datePicker.setText(sdf.format(myCalendar.getTime()));
    }

    public void addcontacts(View view){

            MultiContactPicker.Builder contactBuilder = new MultiContactPicker.Builder(addChit.this);


            String[] PERMISSIONS = {android.Manifest.permission.READ_CONTACTS};
            if (!hasPermissions(getApplicationContext(), PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) addChit.this, PERMISSIONS, 1 );
            } else {


                new MultiContactPicker.Builder(addChit.this) //Activity/fragment context
                        .hideScrollbar(false) //Optional - default: false
                        .showTrack(true) //Optional - default: true
                        .searchIconColor(Color.WHITE) //Option - default: White
                        .handleColor(ContextCompat.getColor(addChit.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                        .bubbleColor(ContextCompat.getColor(addChit.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                        .bubbleTextColor(Color.WHITE) //Optional - default: White
                        .showPickerForResult(2);
            }



        }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            if(resultCode == RESULT_OK) {
                results = MultiContactPicker.obtainResult(data);
                for(ContactResult c : results){
                    contactsName.add(c.getDisplayName());
                    }
                    contactsadapter.notifyDataSetChanged();
                }
                Log.d("MyTag", results.get(0).getDisplayName());
            } else if(resultCode == RESULT_CANCELED){
                System.out.println("User closed the picker without selecting items.");
            }
        }
    


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new MultiContactPicker.Builder(addChit.this) //Activity/fragment context
                            .hideScrollbar(false) //Optional - default: false
                            .showTrack(true) //Optional - default: true
                            .searchIconColor(Color.WHITE) //Option - default: White
                            .handleColor(ContextCompat.getColor(addChit.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                            .bubbleColor(ContextCompat.getColor(addChit.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                            .bubbleTextColor(Color.WHITE) //Optional - default: White
                            .showPickerForResult(2);
                } else {
                    Toast.makeText(getApplicationContext(), "The app was not allowed to read your contact", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private static boolean hasPermissions(Context context, String... permissions) {

            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }

        }
        return true;
    }
}
