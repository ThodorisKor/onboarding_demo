package com.example.onboarding_demo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.w3c.dom.Text;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    Spinner spin;
    LinearLayout linearLayout;

    public Button nextslide;
    public Button prevslide;

    public SlideAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images={
            R.drawable.iconi,
            R.drawable.iconii,
            R.drawable.iconiii
    };

    public String[] slide_headings={
            "Youniversity",
            "",
            ""
    };

    public String[] slide_descs={
            "Hello student! Welcome to the app. Check your academic progress now!\n",
            "Choose your institution",
            "Enter your: (user name/ password)"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImage = (ImageView) view.findViewById(R.id.imageView);
        TextView slideheading = (TextView) view.findViewById(R.id.textView2);
        TextView slideText = (TextView) view.findViewById(R.id.textView);
        spin = (Spinner) view.findViewById(R.id.spinner);
        linearLayout = (LinearLayout) view.findViewById(R.id.linear);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.universities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spin.setAdapter(adapter);
        spin.setVisibility(View.INVISIBLE);
        System.out.println(position);
        /**
         * if statement , checks on which card is selected , if the second card is selected,
         * we make the spinner(with values) visible to the user,
         * the result is , that we have a spinner only on the second card!
         */
        if(slide_descs[position]=="Choose your institution"){
            spin.setVisibility(View.VISIBLE);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int n, long l) {
                    switch (n){
                        case 0:
                            //slide_images[2] =  R.drawable.ihulogo;
                            slideImage.setImageResource(R.drawable.ihulogo);
                            break;
                        case 1:
                            slideImage.setImageResource(R.drawable.authlogo);
                            //slide_images[2] =R.drawable.authlogo;
                            break;
                        case 2:
                            slideImage.setImageResource(R.drawable.aegeanlogo);
                            //slide_images[2] = R.drawable.aegeanlogo;
                            break;
                        case 3:
                            slideImage.setImageResource(R.drawable.uomlogo);
                            //slide_images[2] = R.drawable.uomlogo;
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    slide_images[2] = R.drawable.iconiii;
                }
            });
        }else{
            spin.setVisibility(View.INVISIBLE);
        }
        if(slide_descs[position]=="Enter your: (user name/ password)"){
            linearLayout.setVisibility(View.VISIBLE);
        }else{
            linearLayout.setVisibility(View.INVISIBLE);
            slideheading.setText(slide_headings[position]);
        }
        slideImage.setImageResource(slide_images[position]);
        slideText.setText(slide_descs[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
