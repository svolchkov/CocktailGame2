package com.example.sergeyv.cocktailgame;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.*;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    String[] monthNames;
    TextView cocktailName;
    NumberPicker letterPicker;
    NumberPicker monthPicker;
    ImageView image;
    final String[] adjectives = new String[]{"Arabian","Bloody","Caribbean","Dark",
            "Eastern","Fruity","Golden","Hot","Indian","Juicy","Kinky","Lemon",
            "Minty","Neon","Orange","Peachy","Quirky","Russian","Sweet","Turkish",
            "Ultimate","Venomous","White","Xtreme","Yellow","Zappy"};
    final String[] nouns = new String[]{"Margarita","Daiquiri","Mojito","Fizz","Zombie","Lady","Smash","Mule",
            "Hurricane","Explosion","Highball","Sunrise"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // add icon to action bar
        //getActionBar().setDisplayShowHomeEnabled(true)
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.jc_launcher);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.imageView2);
        //image.requestFocus();
        cocktailName = (TextView) findViewById(R.id.cocktailName);



        //alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
//        alphabet = new String[]{"Kozya","Badya","Vasya"};
//        letterPicker.setDisplayedValues( alphabet );
//        letterPicker.setFormatter(new AlphabetFormatter());
//        letterPicker.setMinValue(0);
//        letterPicker.setMaxValue(2);
//        letterPicker.setWrapSelectorWheel(true);
//        letterPicker.setValue(0);

//        try {
//            Method method = letterPicker.getClass().getDeclaredMethod("changeValueByOne", boolean.class);
//            method.setAccessible(true);
//            method.invoke(letterPicker, true);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }

        //letterPicker.set

        // This to set month names depending on locale settings
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.getDefault());
        monthNames = dfs.getMonths();

        letterPicker = (NumberPicker) findViewById(R.id.letterPicker);
        letterPicker.setMinValue(0);
        letterPicker.setMaxValue(25);
        letterPicker.setDisplayedValues( new String[] {"A","B","C","D","E","F","G","H","I","J","K","L",
                                                        "M", "N","O","P","Q","R","S","T","U","V","W","X","Y","Z"} );


        monthPicker = (NumberPicker) findViewById(R.id.monthPicker);
        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(11);
        monthPicker.setDisplayedValues( monthNames );


        image.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                // TODO Auto-generated method stub
//				Toast.makeText(v.getContext(),"Button clicked",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(
                        MainActivity.this,
                        RecipeScreenActivity.class
                );
                startActivity(intent);
            }
        });

        letterPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                updateCocktailName();
            }
        });

        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                updateCocktailName();
                updateImageAndTextColor();
            }
        });

        letterPicker.setValue(0);

//        for (int i = 0; i < letterPicker.getChildCount(); i++) {
//            View child = letterPicker.getChildAt(i);
//            if (child instanceof ImageButton) {
//               // ((ImageButton) child).setScaleType(ImageView.ScaleType.FIT_START);
//                ((ImageButton) child).setLayoutParams(new LinearLayout.LayoutParams(200, 200));
//                ((ImageButton) child).setImageResource(R.drawable.up);
//                ((ImageButton) child).setScaleType(ImageView.ScaleType.FIT_XY);
//            //    ((Button) child).setText("*");
//            }
//            //child.setGravity(Gravity.CENTER_HORIZONTAL);
//        }

        if (savedInstanceState != null) {
            letterPicker.setValue(savedInstanceState.getInt("letter", 0));
            monthPicker.setValue(savedInstanceState.getInt("month", 0));
        }
        // the below code fixes a bug with NumberPicker
        // when the screen is rotated, the NumberPicker that has focus
        //ends up blank. We add the value back to it using a workaround
        try{
            Field f = NumberPicker.class.getDeclaredField("mInputText");
            f.setAccessible(true);
            final EditText inputText = (EditText) f.get(this.letterPicker);

            //inputText.setText(letter);
            inputText.addTextChangedListener(new TextWatcher() {


                @Override
                public void afterTextChanged(Editable s) {}

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    if(s.length() == 0){
                        int letterInt = letterPicker.getValue();
                        String letter = letterPicker.getDisplayedValues()[letterInt];
                        inputText.setText(letter);
                    }

                }
            });
        }	catch (NoSuchFieldException e) {
            e.printStackTrace();
        }   catch (IllegalAccessException e){
            e.printStackTrace();
        }

        updateCocktailName();
        updateImageAndTextColor();
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("letter", letterPicker.getValue());
        outState.putInt("month", monthPicker.getValue());
    }


        private void updateCocktailName(){
            int letterInt = this.letterPicker.getValue();
            String letter = this.letterPicker.getDisplayedValues()[letterInt];
            int monthInt = this.monthPicker.getValue();
            String month = this.monthPicker.getDisplayedValues()[monthInt];
            this.cocktailName.setText(adjectives[letterInt] + " " + nouns[monthInt]);

    }

    private void updateImageAndTextColor(){
        int monthInt = this.monthPicker.getValue() + 1;
        String imageName = "image_" + monthInt;
        int resId = MainActivity.this.getResources().getIdentifier(imageName, "drawable", MainActivity.this.getPackageName());
        this.image.setImageResource(resId);
        // this code sets text color and background
        //monthInt = monthInt % 4 + 1;
        String textColorName = "text" + monthInt;
        String backColorName = "back" + monthInt;
        //int textColorId = MainActivity.this.getResources().getIdentifier(textColorName, "color", MainActivity.this.getPackageName());
        int textColorId = ContextCompat.getColor(this, getResources().getIdentifier(textColorName, "color", getPackageName()));
        int backColorId = MainActivity.this.getResources().getIdentifier(backColorName, "color", MainActivity.this.getPackageName());
        //TextViewCompat.setTextAppearance(this.cocktailName, textColorId);
        this.cocktailName.setTextColor(textColorId);
        this.cocktailName.setBackgroundResource(backColorId);
    }


    private class AlphabetFormatter implements NumberPicker.Formatter {

        @Override
        public String format(int value) {
            return alphabet[value];
        }
    }
}
