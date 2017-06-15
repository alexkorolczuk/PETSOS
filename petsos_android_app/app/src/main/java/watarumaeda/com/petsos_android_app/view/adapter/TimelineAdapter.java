package watarumaeda.com.petsos_android_app.view.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import watarumaeda.com.petsos_android_app.service.Service;
import watarumaeda.com.petsos_android_app.model.Pet;
import watarumaeda.com.petsos_android_app.view.activity.MainActivity;
import watarumaeda.com.petsos_android_app.view.activity.ProfileActivity;
import watarumaeda.com.petsos_android_app.R;
import watarumaeda.com.petsos_android_app.common.PetImageCallback;

/**
 * Created by aleksandrakorolczuk1 on 2017-05-30.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder>
{
    private ArrayList<Pet> pets;
    public static final String EXTRA_ID = "com.watarumaeda.com.petsos_android_app.ID";

    //constructor
    public TimelineAdapter(ArrayList<Pet> pets)  {
        this.pets = pets;
    }

    @Override
    public TimelineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_card, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final TimelineAdapter.ViewHolder holder, int position)
    {
        //hey, viewholder - ustawianie textu w holderze i loop przez nasz ciag danych
        holder.mId = pets.get(position).id;
        holder.mName.setText(pets.get(position).name);
        holder.mAge.setText(pets.get(position).age);

        // Download image
        holder.mImgView.setImageResource(R.drawable.place_holder);
        Service.shared().getPetImage(pets.get(position).img_url, new PetImageCallback() {
            @Override
            public void getPetImageCallback(Boolean success, Bitmap image) {
                if (success)
                {
                    holder.mImgView.setImageBitmap(image);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return pets.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public String mId;
        public TextView mName;
        public TextView mAge;
        public ImageView mImgView;
        Typeface tf;
        public TextView mWordAge;


        public ViewHolder(View itemView)
        {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
            mAge = (TextView) itemView.findViewById(R.id.age);
            mWordAge = (TextView) itemView.findViewById(R.id.word_age);
            tf = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Amatic-Bold.ttf");
            this.mName.setTypeface(tf);
            this.mAge.setTypeface(tf);
            this.mWordAge.setTypeface(tf);

            mImgView = (ImageView) itemView.findViewById(R.id.img);
            mImgView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    intent.putExtra(EXTRA_ID, mId);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }


}