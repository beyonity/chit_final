package in.beyonitysoftwares.chit_final;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChitFragment extends Fragment {

    FloatingActionButton addchit;
    PopupWindow changeSortPopUp;
    public ChitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chit,container,false);

        addchit = (FloatingActionButton) view.findViewById(R.id.addchit);
        addchit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),addChit.class);
                startActivity(i);
                // Getting a reference to Close button, and close the popup when clicked.

            }
        });

        return view;
    }

}
