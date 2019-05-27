package com.example.lucasftecnico.exemplosmaterialdesign.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasftecnico.exemplosmaterialdesign.R;
import com.example.lucasftecnico.exemplosmaterialdesign.interfaces.RecycleViewOnClickListenerHack;
import com.example.lucasftecnico.exemplosmaterialdesign.models.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MeuViewHoldder> {
    private List<Car> carList;
    private LayoutInflater layoutInflater;
    private RecycleViewOnClickListenerHack recycleViewOnClickListenerHack;

    public CarAdapter(Context context, List<Car> cars) {
        carList = cars;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MeuViewHoldder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i("LOG", "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.item_car, viewGroup, false);
        MeuViewHoldder viewHoldder = new MeuViewHoldder(view);
        return viewHoldder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHoldder meuViewHoldder, int i) {
        Log.i("LOG", "onBindViewHolder");
        meuViewHoldder.ivCar.setImageResource(carList.get(i).getPhoto());
        meuViewHoldder.tvModel.setText(carList.get(i).getModel());
        meuViewHoldder.tvBrand.setText(carList.get(i).getBrand());
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public void addListItem(Car car, final int position) {
        carList.add(car);

        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                notifyItemInserted(position);
            }
        };

        handler.post(r);

        //notifyItemInserted(position);
    }

    public void removeListItem(int position) {
        carList.remove(position);
        notifyItemRemoved(position);
    }

    public void setRecycleViewOnClickListenerHack(RecycleViewOnClickListenerHack hack) {
        recycleViewOnClickListenerHack = hack;
    }

    public class MeuViewHoldder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivCar;
        public TextView tvModel;
        public TextView tvBrand;

        public MeuViewHoldder(@NonNull View itemView) {
            super(itemView);

            ivCar = (ImageView) itemView.findViewById(R.id.iv_car);
            tvModel = (TextView) itemView.findViewById(R.id.tv_model);
            tvBrand = (TextView) itemView.findViewById(R.id.tv_brand);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recycleViewOnClickListenerHack != null) {
                recycleViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }
    }
}
