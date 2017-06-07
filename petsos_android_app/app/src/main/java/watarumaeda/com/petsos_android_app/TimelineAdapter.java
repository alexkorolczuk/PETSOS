package watarumaeda.com.petsos_android_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aleksandrakorolczuk1 on 2017-05-30.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder>
{
    private ArrayList<Pet> pets;
    public static final String EXTRA_NAME = "com.watarumaeda.com.petsos_android_app.NAME";
    public static final String EXTRA_AGE = "com.watarumaeda.com.petsos_android_app.AGE";
    public static final String EXTRA_PHOTO = "com.watarumaeda.com.petsos_android_app.PHOTO";

    //constructor, ze
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
    public void onBindViewHolder(TimelineAdapter.ViewHolder holder, int position)
    {
        //hey, viewholder - ustawianie textu w holderze i loop przez nasz ciag danych
        holder.mName.setText(pets.get(position).name);
        holder.mAge.setText(pets.get(position).age);
        holder.android_image_urls.setImageResource(R.drawable.place_holder);
    }

    @Override
    public int getItemCount()
    {
        return pets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mName;
        public TextView mAge;
        public ImageView android_image_urls;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.dog_name);
            mAge = (TextView) itemView.findViewById(R.id.dog_age);
            android_image_urls = (ImageView) itemView.findViewById(R.id.catimage);

            /* HERE
            So in my single viewHolder I'm getting 2 single strings with name and           age
            and passing them as EXTRAS to our ProfileActivity. It doesnt work with images though.
            Operating on bitmaps is not the best way for us coz It's gonna be really slow...
            Saving id of the current clicked photo and passing just id? or source? I dont know:(

             */
            android_image_urls.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    String name = mName.getText().toString();
                    String age = mAge.getText().toString();
                   // Drawable photo = android_image_urls.getDrawable();
                   // Intent intentWithImg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    intent.putExtra(EXTRA_NAME, name);
                    intent.putExtra(EXTRA_AGE, age);
                    v.getContext().startActivity(intent);
        }});
}}}