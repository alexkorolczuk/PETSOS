package watarumaeda.com.petsos_android_app;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    private ArrayList <String> mName;
    private ArrayList <String> mAge;
    private ArrayList<Drawable> android_image_urls;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    // Controller
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();  // Set data
        initViews(); // initialize views

        // debug to check if everything is calling and working:
//        for (int i = 0; i <mName.size() ; i++) {
//            Log.d(TAG, "onCreate" + mName.get(i));
//        }
    }

    // Data
    private void setData()
    {
        mName = new ArrayList<>();
        mAge =  new ArrayList<>();

        //instead of objects with names - just sample data with name + number
        for (int i = 0; i < 6 ; i++) {
            mName.add("Name of the dog" + i);
        }

        for (int i = 0; i < 6; i++) {
            mAge.add("X" + i);
        }

        // Image array
        android_image_urls = new  ArrayList<Drawable>();
        android_image_urls.add(getDrawable(R.drawable.cat1));
        android_image_urls.add(getDrawable(R.drawable.cat2));
        android_image_urls.add(getDrawable(R.drawable.cat3));
        android_image_urls.add(getDrawable(R.drawable.cat5));
        android_image_urls.add(getDrawable(R.drawable.cat6));
        android_image_urls.add(getDrawable(R.drawable.cat7));
    }

    // View
    private void initViews()
    {
        // recyclerView initialization
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new TimelineAdapter(mName, mAge, android_image_urls);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
