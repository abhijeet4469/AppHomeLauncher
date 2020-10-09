package com.acs.abhijeetp_tesseract.App;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import com.acs.abhijeetp_tesseract.R;

// import SDK Module
import com.acs.sdk.AppInformation;
import com.acs.sdk.ModalAppInfo;

public class MainActivity extends AppCompatActivity implements RecyclerOnItemClickListener {

    private AdapterAppList adapter;
    private SwipeRefreshLayout swipeRefresh;
    private List<ModalAppInfo> listAllApp;
    private List<ModalAppInfo> listAllAppMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.main_activity_name));

        init();
        loadAppData();
    }

    private void init(){

        listAllApp = new ArrayList<>();
        listAllAppMain = new ArrayList<>();

        // initialize Adapter
        adapter = new AdapterAppList(listAllApp, this);

        // Apply Recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        // Apply Swipe Refresh
        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadAppData();
            }
        });

        // Apply SearchView
        SearchView searchView = findViewById(R.id.searchBar);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {

                listAllApp.clear();
                for (int i=0; i<listAllAppMain.size(); i++){
                    if (listAllAppMain.get(i).getAppName().toLowerCase().contains(newText.toLowerCase())){
                        listAllApp.add(listAllAppMain.get(i));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    private void loadAppData(){
        swipeRefresh.setRefreshing(true);
        listAllAppMain = AppInformation.getInstance().getInstalledApps(getApplicationContext());
        listAllApp.addAll(listAllAppMain);
        adapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }



    @Override
    public void onItemClick(View view, int position) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(listAllApp.get(position).getPackages());
        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Could not open this app", Toast.LENGTH_SHORT).show();
        }
    }

}