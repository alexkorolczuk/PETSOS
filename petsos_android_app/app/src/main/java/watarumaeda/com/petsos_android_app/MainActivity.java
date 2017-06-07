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

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView initialization
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Fetch pets data from database
        ServiceUtil.shared().getPet(new PetsCallback() {
            @Override
            public void getPetsCallback(Boolean success, ArrayList<Pet> pets)
            {
                if (success)
                {
                    // Set data
                    mAdapter = new TimelineAdapter(pets);
                    mRecyclerView.setAdapter(mAdapter);
                }
                else
                {
                    // TODO: Show error message
                }
            }
        });
    }
}
