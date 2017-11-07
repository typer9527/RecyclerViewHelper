package com.yl.recyclerviewhelper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Fruit[] fruits = {new Fruit("Apple", R.drawable.apple),
            new Fruit("Banana", R.drawable.banana), new Fruit("Cherry", R.drawable.cherry),
            new Fruit("Grape", R.drawable.grape), new Fruit("Orange", R.drawable.orange),
            new Fruit("Peach", R.drawable.peach), new Fruit("Tomato", R.drawable.tomato)};
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitsAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new FruitsAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        refreshFruits();
        adapter.notifyDataSetChanged();
        // 利用SwipeRefreshLayout实现刷新数据
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        // 利用OnScrollListener实现加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!(recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE)
                        && !recyclerView.canScrollVertically(1)) {
                    loadMore();
                }
            }
        });
    }

    private void loadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fruitList.addAll(getMoreFruits());
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void refresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshFruits();
                            adapter.notifyDataSetChanged();
                            refreshLayout.setRefreshing(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void refreshFruits() {
        fruitList.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private List<Fruit> getMoreFruits() {
        List<Fruit> moreFruits = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            moreFruits.add(fruits[index]);
        }
        return moreFruits;
    }
}
