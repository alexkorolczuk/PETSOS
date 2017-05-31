package watarumaeda.com.petsos_android_app;

import android.graphics.drawable.Drawable;
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

class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder>
{
    ArrayList<String> mName;
    ArrayList<String> mAge;
    ArrayList<Drawable> android_image_urls;

    //constructor, ze
    public TimelineAdapter(ArrayList<String> mName, ArrayList<String> mAge, ArrayList<Drawable> android)
    {
        this.mName = mName;
        this.mAge = mAge;
        this.android_image_urls = android;
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
        holder.mName.setText(mName.get(position));
        holder.mAge.setText(mAge.get(position));
        holder.android_image_urls.setImageDrawable(android_image_urls.get(position));
    }

    @Override
    public int getItemCount()
    {
        //return mName.size() + mAge.size() + android_image_urls.size();
        if (mAge.get(0) != null && mName != null && android_image_urls != null) {
            return mName.size();
        } else {
            return 0;
        }
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
        }
    }
}
