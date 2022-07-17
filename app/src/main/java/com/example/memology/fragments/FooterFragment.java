package com.example.memology.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.memology.R;
import com.example.memology.activities.CreatePost;
import com.example.memology.activities.HomePage;

public class FooterFragment extends Fragment {
    private Button createPost;

    public FooterFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static FooterFragment newInstance() {
        return new FooterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_footer, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createPost =(Button) view.findViewById(R.id.CreatePostButton);
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchCreatePostIntent = new Intent(getActivity().getApplicationContext(), CreatePost.class);
                startActivity(launchCreatePostIntent);
            }
        });
    }
}