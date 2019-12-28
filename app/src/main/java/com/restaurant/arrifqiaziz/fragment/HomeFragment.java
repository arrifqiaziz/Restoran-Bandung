package com.restaurant.arrifqiaziz.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.restaurant.arrifqiaziz.R;
import com.restaurant.arrifqiaziz.adapter.recyclerview.RestaurantAdapter;
import com.restaurant.arrifqiaziz.data.Session;
import com.restaurant.arrifqiaziz.model.restaurant.Restaurant;
import com.restaurant.arrifqiaziz.model.restaurant.RestaurantResponse;

import static com.restaurant.arrifqiaziz.data.Constant.LIST_RESTAURANT;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Unbinder unbinder;

    Session session;

    RestaurantAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, v);

        Log.e("LOAD", "TRUE");

        session = new Session(getActivity());

        initRecyclerview();

        return v;
    }

    private void initRecyclerview() {
        adapter = new RestaurantAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.hasFixedSize();

        adapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        });

        loadItems();
    }

    private void loadItems() {
        AndroidNetworking.get(LIST_RESTAURANT)
                .build()
                .getAsObject(RestaurantResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        if (response instanceof RestaurantResponse) {
                            List<Restaurant> list = ((RestaurantResponse) response).getData();
                            adapter.swap(list);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getActivity(), "Mohon maaf kesalahan teknis !", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
