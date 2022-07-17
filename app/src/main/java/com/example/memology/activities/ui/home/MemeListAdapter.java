package com.example.memology.activities.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memology.R;
import com.example.memology.models.Memes;

import java.util.List;

public class MemeListAdapter extends RecyclerView.Adapter<MemeListAdapter.ViewHolder> {
    private List<Memes> memesList;

    public MemeListAdapter(List<Memes> memes){
        this.memesList = memes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.meme_itemview,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Memes myMemesData = memesList.get(position);
        holder.memeTitleHolder.setText(myMemesData.getTitle());
        holder.memeImageHolder.setImageURI(myMemesData.getUri());
    }

    @Override
    public int getItemCount() {
        return memesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView memeImageHolder;
        public TextView memeTitleHolder;
        public LinearLayout memeItemHolder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            memeImageHolder = (ImageView) itemView.findViewById(R.id.imageView);
            memeTitleHolder = (TextView) itemView.findViewById(R.id.title);
            memeItemHolder = (LinearLayout) itemView.findViewById(R.id.memeItemView);
        }
    }
}
