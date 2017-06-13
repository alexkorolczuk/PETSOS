package watarumaeda.com.petsos_android_app.view.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import watarumaeda.com.petsos_android_app.model.PetDetail;
import watarumaeda.com.petsos_android_app.service.Service;
import watarumaeda.com.petsos_android_app.model.Pet;
import watarumaeda.com.petsos_android_app.R;
import watarumaeda.com.petsos_android_app.common.PetsCallback;
import watarumaeda.com.petsos_android_app.view.adapter.TimelineAdapter;

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
        Service.shared().getPets(new PetsCallback() {
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

        // Add listner
        ImageButton imgBtn = (ImageButton) findViewById(R.id.btn_add_pet);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Show add pet screen
            }
        });
    }
}
