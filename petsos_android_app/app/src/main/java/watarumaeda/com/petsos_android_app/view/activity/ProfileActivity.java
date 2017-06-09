package watarumaeda.com.petsos_android_app.view.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import watarumaeda.com.petsos_android_app.R;
import watarumaeda.com.petsos_android_app.common.PetCallback;
import watarumaeda.com.petsos_android_app.common.PetImageCallback;
import watarumaeda.com.petsos_android_app.common.PetsCallback;
import watarumaeda.com.petsos_android_app.model.Pet;
import watarumaeda.com.petsos_android_app.service.Service;
import watarumaeda.com.petsos_android_app.view.adapter.TimelineAdapter;

public class ProfileActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Set data
        setData();

        // Add actions
        addActions();
    }

    // Data
    private String getID()
    {
        Bundle b = getIntent().getExtras();
        if (b != null) return (String)b.get(TimelineAdapter.EXTRA_ID);
        return "";
    }

    // UI
    private void setData()
    {
        // Components
        final TextView mTxtvName = (TextView) findViewById(R.id.name);
        final TextView mTxtvAge = (TextView) findViewById(R.id.txtv_sammary);
        final ImageView mImgvPet = (ImageView) findViewById(R.id.pet_image);

        // Pet ID
        String id = getID();

        // Get pet data -> set to components
        Service.shared().getPet(id, new PetCallback() {
            @Override
            public void getPetCallback(Boolean success, Pet pet)
            {
            mTxtvName.setText(pet.name);
            mTxtvAge.setText(pet.age);

            // Download image
            Service.shared().getPetImage(pet.img_url, new PetImageCallback() {
                @Override
                public void getPetImageCallback(Boolean success, Bitmap image) {
                if (success)
                {
                    mImgvPet.setImageBitmap(image);
                }
                        }
            });
            }
        });
    }

    // Action
    private void addActions()
    {
        Button btnAbout = (Button)findViewById(R.id.btn_share_me);
        Button btnAdopt = (Button)findViewById(R.id.btn_adopt_me);
        btnAbout.setOnClickListener(new View.OnClickListener()  {
            @Override  public void onClick(View v) { callMailer(); }
        });
        btnAdopt.setOnClickListener(new View.OnClickListener()  {
            @Override  public void onClick(View v) { callMailer(); }
        });
    }

    // Maller
    private void callMailer()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:xxx@yyy.zzz"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your title");
        intent.putExtra(Intent.EXTRA_TEXT, "Message body");
        startActivity(Intent.createChooser(intent, null));
    }
}

