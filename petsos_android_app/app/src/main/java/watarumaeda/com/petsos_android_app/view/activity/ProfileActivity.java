package watarumaeda.com.petsos_android_app.view.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import watarumaeda.com.petsos_android_app.R;
import watarumaeda.com.petsos_android_app.common.PetCallback;
import watarumaeda.com.petsos_android_app.common.PetDetailCallback;
import watarumaeda.com.petsos_android_app.common.PetDetailsCallback;
import watarumaeda.com.petsos_android_app.common.PetImageCallback;
import watarumaeda.com.petsos_android_app.common.PetsCallback;
import watarumaeda.com.petsos_android_app.model.Pet;
import watarumaeda.com.petsos_android_app.model.PetDetail;
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
        // Pet ID
        String id = getID();

        // Summaru components
        final TextView mTxtvName = (TextView) findViewById(R.id.name);
        final TextView mTxtvAge = (TextView) findViewById(R.id.txtv_sammary);
        final ImageView mImgvPet = (ImageView) findViewById(R.id.pet_image);

        // Get pet data -> set to components
        Service.shared().getPet(id, new PetCallback() {
            @Override
            public void getPetCallback(Boolean success, Pet pet)
            {
            mTxtvName.setText(pet.name);
            mTxtvAge.setText(pet.breed + ", " + pet.age);

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

        // Detail components
        final TextView mTxtvAbout = (TextView) findViewById(R.id.about);
        final TextView mTxtvSex = (TextView) findViewById(R.id.sex);
        final TextView mTxtvOwnerType = (TextView) findViewById(R.id.owner_type);
        final TextView mTxtvOtherPets = (TextView) findViewById(R.id.other_pets);
        final TextView mTxtvSize = (TextView) findViewById(R.id.size);
        final TextView mTxtvContact = (TextView) findViewById(R.id.contact);

        Typeface typeFace = Typeface.createFromAsset((getAssets()), "font/Amatic-Bold.ttf");
        mTxtvName.setTypeface(typeFace);
        mTxtvAge.setTypeface(typeFace);
        mTxtvSex.setTypeface(typeFace);
        mTxtvOwnerType.setTypeface(typeFace);
        mTxtvOtherPets.setTypeface(typeFace);
        mTxtvSize.setTypeface(typeFace);
        mTxtvContact.setTypeface(typeFace);

        // Get pet summary data -> set to components
        Service.shared().getPetDetail(id, new PetDetailCallback() {
            @Override
            public void getPetDetailCallback(Boolean success, PetDetail pd) {
                if (success)
                {
                    mTxtvAbout.setText(String.valueOf(pd.about));
                    mTxtvSex.setText(pd.sex == 0 ? "Male" : "Femail");
                    mTxtvOwnerType.setText(pd.owner_type == 0 ? "Shelter" : "Rescue");
                    mTxtvOtherPets.setText(pd.other_pets ? "OK" : "NO");
                    mTxtvSize.setText(pd.size_type == 0 ? "Small" : "Medium");
                }
            }
        });
    }

    // Action
    private void addActions()
    {

        Button btnAdopt = (Button)findViewById(R.id.btn_adopt_me);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.animaton_button);
        Typeface typeFace = Typeface.createFromAsset((getAssets()), "font/Amatic-Bold.ttf");
        btnAdopt.setTypeface(typeFace);
        btnAdopt.setAnimation(myAnim);
        btnAdopt.setOnClickListener(new View.OnClickListener()  {
            @Override  public void onClick(View v) { callMailer();
                v.startAnimation(myAnim);  }
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

