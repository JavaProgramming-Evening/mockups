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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ModelActivity extends AppCompatActivity {

    String[] model = {"model_1.png", "model_1.png", "model_1.png", "model_1.png", "model_1.png"};
    int carPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        List<CarModel> carModelList = new ArrayList<>();
        CarModel carModel;
        for(int i = 0; i < model.length; i++) {
            carModel = new CarModel(model[i]);
            carModelList.add(carModel);
        }

        ModelAdapter modelAdapter = new ModelAdapter(carModelList);
        recyclerView.setAdapter(modelAdapter);

        modelAdapter.setModelAdapterClickListener(new ModelAdapterClickListener() {
            @Override
            public void onCarModelClick(int carIndex) {
                carPosition = carIndex;
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CarModel carModel1 = carModelList.get(position);

                Toast.makeText(getApplicationContext(), carModel.getId() + " is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
    }

   /* public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ModelActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ModelActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }*/

    public void openTyping(View view){
        //Toast.makeText((getApplicationContext()), name.getText().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), TypingActivity.class);
        intent.putExtra("car_position", carPosition);
        startActivity(intent);
    }

}
