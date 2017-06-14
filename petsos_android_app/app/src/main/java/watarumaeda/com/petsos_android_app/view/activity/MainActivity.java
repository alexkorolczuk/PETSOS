package watarumaeda.com.petsos_android_app.view.activity;

import android.Manifest;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;

import watarumaeda.com.petsos_android_app.model.PetDetail;
import watarumaeda.com.petsos_android_app.service.Service;
import watarumaeda.com.petsos_android_app.model.Pet;
import watarumaeda.com.petsos_android_app.R;
import watarumaeda.com.petsos_android_app.common.PetsCallback;
import watarumaeda.com.petsos_android_app.view.adapter.TimelineAdapter;
import watarumaeda.com.petsos_android_app.view.fragment.MissingDialogFlagment;

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

        // Show timeline
        setTimelineData();

        // Add listner
        ImageButton imgBtn = (ImageButton) findViewById(R.id.btn_add_pet);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Go to post screen
                Intent intent = new Intent(v.getContext(), PostActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTimelineData();
    }


    private void setTimelineData()
    {
        // Fetch pets data from database
        Service.shared().getPets(new PetsCallback() {
            @Override
            public void getPetsCallback(Boolean success, ArrayList<Pet> pets)
            {
                if (success)
                {
                    // Set data
                    Collections.reverse(pets);
                    mAdapter = new TimelineAdapter(pets);
                    mRecyclerView.setAdapter(mAdapter);
                }
                else
                {
                    // TODO: Show error message
                    DialogFragment newFragment = new MissingDialogFlagment("Unable to get data", "Please make sure your network and try again");
                    newFragment.show(getFragmentManager(), "get_timeline: failed");
                }
            }
        });
    }
}
