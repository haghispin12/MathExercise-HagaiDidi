package com.example.mathexercise_hagaididi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FruitsActivity extends AppCompatActivity {
    private RecyclerView rcShowUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        rcShowUsers =findViewById(R.id.rcShowUsers);
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("banana",R.drawable.banana));
        fruits.add(new Fruit("apple",R.drawable.apple));
        fruits.add(new Fruit("fru",R.drawable.fru));
        fruits.add(new Fruit("orange",R.drawable.orange));
        fruits.add(new Fruit("grapes",R.drawable.grapes));
        fruits.add(new Fruit("lemon",R.drawable.lemon));
         MyFruitsAdapter myFruitsAdapter = new MyFruitsAdapter(fruits, new MyFruitsAdapter.OnItemClickListener() {
             @Override
             public void onitemClick(Fruit item) {
                 Toast.makeText(FruitsActivity.this,item.getName(),Toast.LENGTH_SHORT).show();
             }
         });
         rcShowUsers.setLayoutManager(new LinearLayoutManager(this));
         rcShowUsers.setAdapter(myFruitsAdapter);
         rcShowUsers.setHasFixedSize(true);
    }
}