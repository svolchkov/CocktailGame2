package com.example.sergeyv.cocktailgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecipeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_screen);
        //Button redButton = (Button)findViewById(R.id.homeButton);
//        redButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //Toast.makeText(v.getContext(),"Button clicked",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(
//                        RecipeScreenActivity.this,
//                        MainActivity.class
//                );
//                startActivity(intent);
//            }
//        });
    }
}
