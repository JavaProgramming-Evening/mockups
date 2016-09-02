package com.example.phalla.typingracer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ModelActivity extends AppCompatActivity {

    String[] model = {"model_1", "model_2", "model_3", "model_4", "model_5"};
    int carPosition;
    ModelAdapter modelAdapter;
    List<CarModel> carModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        CarModel carModel;
        for(int i = 0; i < model.length; i++) {
            int idImage = getResources().getIdentifier("@drawable/" + model[i], null, getPackageName());
            boolean isSelected = false;
            if(i==0){
                isSelected = true;
            }
            carModel = new CarModel(idImage, isSelected);
            carModelList.add(carModel);
        }

        modelAdapter = new ModelAdapter(carModelList);
        recyclerView.setAdapter(modelAdapter);

        //this.setCarSelected(0);

        modelAdapter.setModelAdapterClickListener(new ModelAdapterClickListener() {
            @Override
            public void onCarModelClick(int carIndex) {
                carPosition = carIndex;
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    public void openTyping(View view){
        Intent intent = new Intent(getApplicationContext(), TypingActivity.class);
        intent.putExtra("car_position", carPosition);
        startActivity(intent);
    }

    /*private void setCarSelected(int position){

        modelAdapter.notifyDataSetChanged();
    }*/
}
