package com.yongchun.flowimagelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView imageListView;
    private TopicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView() {
        imageListView = (ListView) findViewById(R.id.image_list);
        adapter = new TopicAdapter();
        imageListView.setAdapter(adapter);


        for (int i = 0; i < 9; i++) {
            List<Integer> imageId = new ArrayList<>();
            for (int k = 1; k < i + 2; k++) {
                imageId.add(getResources().getIdentifier("image"+k,"mipmap",getPackageName()));
            }
            adapter.addTopic(new Topic(R.mipmap.image_user, "Nemo", "Don't put off today's work till tomorrow. ",imageId));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
