package com.yl.recyclerviewhelper;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        FruitsAdapter adapter = new FruitsAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        refreshFruits();
        adapter.notifyDataSetChanged();
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
