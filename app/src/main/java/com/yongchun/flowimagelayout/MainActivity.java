package com.yongchun.flowimagelayout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private ListView imageListView;
    private TopicAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    public void initView(){
        imageListView = (ListView)findViewById(R.id.image_list);
        adapter = new TopicAdapter();
        imageListView.setAdapter(adapter);

        adapter.addTopic(new Topic(R.mipmap.image_user, "Nemo", "The Android Clock App Clinic",
                Arrays.asList(new Integer[]{R.mipmap.image1})));
        adapter.addTopic(new Topic(R.mipmap.image_user,"Nemo","The Android Clock App Clinic",
                Arrays.asList(new Integer[]{R.mipmap.image1,R.mipmap.image2})));
        adapter.addTopic(new Topic(R.mipmap.image_user,"Nemo","The Android Clock App Clinic",
                Arrays.asList(new Integer[]{R.mipmap.image1,R.mipmap.image2,R.mipmap.image3})));
        adapter.addTopic(new Topic(R.mipmap.image_user,"Nemo","The Android Clock App Clinic",
                Arrays.asList(new Integer[]{R.mipmap.image1,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4})));
        adapter.addTopic(new Topic(R.mipmap.image_user,"Nemo","The Android Clock App Clinic",
                Arrays.asList(new Integer[]{R.mipmap.image1,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image5})));
        adapter.addTopic(new Topic(R.mipmap.image_user,"Nemo","The Android Clock App Clinic",
                Arrays.asList(new Integer[]{R.mipmap.image1,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image5,R.mipmap.image6})));
        adapter.addTopic(new Topic(R.mipmap.image_user,"Nemo","The Android Clock App Clinic",
                Arrays.asList(new Integer[]{R.mipmap.image1,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image5,R.mipmap.image6,R.mipmap.image8})));
        adapter.addTopic(new Topic(R.mipmap.image_user,"Nemo","The Android Clock App Clinic",
                Arrays.asList(new Integer[]{R.mipmap.image1,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image5,R.mipmap.image6,R.mipmap.image8,R.mipmap.image9})));
        adapter.addTopic(new Topic(R.mipmap.image_user,"Nemo","The Android Clock App Clinic",
                Arrays.asList(new Integer[]{R.mipmap.image1,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image5,R.mipmap.image6,R.mipmap.image8,R.mipmap.image9,R.mipmap.image2})));
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
