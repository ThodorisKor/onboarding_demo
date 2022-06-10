package com.example.onboarding_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public ViewPager slidepager;
    public SlideAdapter slideAdapter;
    public Button nextslide;
    public Button prevslide;
    public int currentslide;
    public LinearLayout mdot;
    public TextView[] dots;
    public FinishFragment finishFragment;
    public EditText uname;
    public EditText upassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidepager = (ViewPager) findViewById(R.id.viewpager);
        mdot = (LinearLayout) findViewById(R.id.lin);
        nextslide = (Button) findViewById(R.id.next);
        prevslide = (Button) findViewById(R.id.prev);
        slideAdapter = new SlideAdapter(this);
        slidepager.setAdapter(slideAdapter);
        slidepager.setPageTransformer(true,new ZoomOutPageTransformer());

        adddotsindicator(0);
        slidepager.addOnPageChangeListener(viewListener);

        nextslide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(currentslide + " --> " + dots.length);
               /* if(currentslide==dots.length-1){
                    uname = (EditText) findViewById(R.id.name);
                    upassword = (EditText) findViewById(R.id.password);
                    System.out.println("here " + uname.getText().toString());
                    if(TextUtils.isEmpty(uname.getText().toString()) || TextUtils.isEmpty(upassword.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Empty Fields are not Allowed!",Toast.LENGTH_SHORT).show();
                        }else{
                        setContentView(R.layout.finish_layout);
                    }
                }*/
                if(currentslide==dots.length-1){
                    setContentView(R.layout.finish_layout);
                    Button bt = (Button) findViewById(R.id.next);
                    bt.setVisibility(View.INVISIBLE);
                }
                slidepager.setCurrentItem(currentslide + 1);
            }
        });

        prevslide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidepager.setCurrentItem(currentslide-1);
            }
        });
    }

    public void adddotsindicator(int pos){
        dots = new TextView[3];
        mdot.removeAllViews();

        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transparentwhite));

            mdot.addView(dots[i]);
        }
        if(dots.length>0){
            dots[pos].setTextColor(getResources().getColor(R.color.white));
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            adddotsindicator(position);
            currentslide=position;
            if(position==0){
                nextslide.setEnabled(true);
                prevslide.setEnabled(false);
                prevslide.setVisibility(View.INVISIBLE);
                nextslide.setText("Next");
                prevslide.setText("");
            }else if(position==dots.length-1){
                nextslide.setEnabled(true);
                prevslide.setEnabled(true);
                prevslide.setVisibility(View.VISIBLE);
                nextslide.setText("Finish");
                prevslide.setText("Back");
            }else{
                nextslide.setEnabled(true);
                prevslide.setEnabled(true);
                prevslide.setVisibility(View.VISIBLE);
                nextslide.setText("Next");
                prevslide.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}