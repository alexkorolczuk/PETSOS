package watarumaeda.com.petsos_android_app;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

