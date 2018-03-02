package com.example.rishabh.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ImageViewClickListener {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private CountryList countries;
    private CompositeDisposable mcompositeDisposable;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcompositeDisposable = new CompositeDisposable();
        recyclerView = findViewById(R.id.recyler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        fetchCountryList();
    }

    private void fetchCountryList() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        mcompositeDisposable.add(apiInterface.getCountryInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleError(Throwable throwable) {
        Log.d(TAG,throwable.getMessage());
    }

    private void handleResponse(CountryList countryList) {
        countries = countryList;
        adapter = new RecyclerAdapter(countries,this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onImageViewClicked(int position) {
        Intent intent = new Intent(this,FullScreenActivity.class);
        intent.putExtra("imageUrl" , countries.getWorldpopulation().get(position).getFlag());
        startActivity(intent);
    }
}
