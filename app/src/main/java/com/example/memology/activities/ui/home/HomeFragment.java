package com.example.memology.activities.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memology.DB.DBHolder;
import com.example.memology.DB.DatabaseHandler;
import com.example.memology.R;
import com.example.memology.databinding.FragmentHomeBinding;
import com.example.memology.models.Memes;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView memesRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        memesRecyclerView = (RecyclerView) root.findViewById(R.id.memesRecyclerView);
        List<Memes> memesList = getData();
        MemeListAdapter adapter = new MemeListAdapter(memesList);
        memesRecyclerView.setAdapter(adapter);
        memesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }

    private List<Memes> getData(){
        DatabaseHandler db= DBHolder.dbHandler;
        return db.getAllMemes();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}