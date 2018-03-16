package in.beyonitysoftwares.chit_final;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import in.beyonitysoftwares.chit_final.pageAdapters.FragmentPageAdapter;

public class MainActivity extends AppCompatActivity {
    ViewPager vp = (ViewPager) findViewById(R.id.vp);


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    vp.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    vp.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    vp.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //init
        CalcFragment cf = new CalcFragment();
        ChitFragment lf = new ChitFragment();
        CalculatorFragment clf = new CalculatorFragment();
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());
        vp = (ViewPager) findViewById(R.id.vp);

        adapter.addFragment(cf,"");
        adapter.addFragment(lf, "");
        adapter.addFragment(clf, "");

        vp.setAdapter(adapter);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            if (position==0) {
                navigation.setSelected(0);
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
