package watarumaeda.com.petsos_android_app.view.activity;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import watarumaeda.com.petsos_android_app.R;
import watarumaeda.com.petsos_android_app.view.adapter.TimelineAdapter;

public class ProfileActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        /*

       HERE:
       we are catching the intent with age and name, but not the photo.
       BTW in acvitity_profile.xml I removed the source and passed just empty id pet_image.
         */
        Intent intent = getIntent();
        String name = intent.getStringExtra(TimelineAdapter.EXTRA_NAME);
        String age = intent.getStringExtra(TimelineAdapter.EXTRA_AGE);

       TextView mNameTextView = (TextView) findViewById(R.id.name);
        mNameTextView.setText(name);
        TextView mAgeTextView = (TextView) findViewById(R.id.txtv_sammary);
        mAgeTextView.setText(age);


        // Add actions
        addActions();
    }

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

