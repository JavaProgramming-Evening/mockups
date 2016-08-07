package com.example.phalla.typingracer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.phalla.typingracer.R;

import java.util.List;

/**
 * Created by Sony on 27/06/2016.
 */
public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<CarModel> mModels;
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    private ModelAdapterClickListener modelAdapterClickListener;

    // Pass in the contact array into the constructor
    public ModelAdapter(List<CarModel> models) {
        mModels = models;
    }

    public void setModelAdapterClickListener(ModelAdapterClickListener modelAdapterClickListener) {
        this.modelAdapterClickListener = modelAdapterClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public CheckBox checkModel;
        public ImageView carModel;

        //public SharedPreferences sharedPreferences;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(Context context, View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            checkModel = (CheckBox) itemView.findViewById(R.id.modelID);
            carModel = (ImageView) itemView.findViewById(R.id.carMod);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // Get the data model based on position
        //final CarModel models = mModels.get(position);
        CarModel car = mModels.get(position);

        ImageView imageView = holder.carModel;
        imageView.setImageResource(car.getCar());

        if(position == 0 && holder.checkModel.isChecked()){
            lastChecked = holder.checkModel;
            lastCheckedPos = 0;
        }

        holder.checkModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;

                if(checkBox.isChecked()){
                    if(lastChecked != null){
                        lastChecked.setChecked(false);
                    }

//                    SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("Player",0);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putInt("model", position);
//                    editor.commit();

                    modelAdapterClickListener.onCarModelClick(position);

                    lastChecked = checkBox;
                    lastCheckedPos = position;
                } else {
                    lastChecked = null;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View modelView = inflater.inflate(R.layout.model_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context, modelView);
        return viewHolder;
    }
}
