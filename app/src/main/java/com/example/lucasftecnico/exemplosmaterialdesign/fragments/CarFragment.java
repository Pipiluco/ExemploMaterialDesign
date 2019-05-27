package com.example.lucasftecnico.exemplosmaterialdesign.fragments;

import android.content.Context;
import android.gesture.Gesture;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lucasftecnico.exemplosmaterialdesign.MainActivity;
import com.example.lucasftecnico.exemplosmaterialdesign.R;
import com.example.lucasftecnico.exemplosmaterialdesign.adapters.CarAdapter;
import com.example.lucasftecnico.exemplosmaterialdesign.interfaces.RecycleViewOnClickListenerHack;
import com.example.lucasftecnico.exemplosmaterialdesign.models.Car;

import java.util.List;


public class CarFragment extends Fragment implements RecycleViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<Car> carList;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvLista);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();

                if (carList.size() == layoutManager.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Car> listAux = ((MainActivity) getActivity()).getSetCarList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), carList.size());
                    }
                }
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        carList = ((MainActivity) getActivity()).getSetCarList(10);
        CarAdapter adapter = new CarAdapter(getActivity(), carList);
        adapter.setRecycleViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Posição: " + position, Toast.LENGTH_SHORT).show();
        CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Posição: " + position, Toast.LENGTH_SHORT).show();
        CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);
    }


    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener{
        private Context context;
        private GestureDetector gestureDetector;
        private RecycleViewOnClickListenerHack recycleViewOnClickListenerHack;

        public RecyclerViewTouchListener(Context contexto, RecyclerView recyclerView, RecycleViewOnClickListenerHack hack){
            context = contexto;
            recycleViewOnClickListenerHack = hack;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener()){
                public void onLongPress(MotionEvent event){

                }
            };
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean b) {

        }
    }
}
