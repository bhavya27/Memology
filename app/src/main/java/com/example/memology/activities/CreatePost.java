package com.example.memology.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.memology.GlobalConstants;
import com.example.memology.R;
import com.example.memology.databinding.ActivityCreatePostBinding;
import com.example.memology.viewmodels.CreatePostViewModel;

import java.io.File;

public class CreatePost extends AppCompatActivity {

    private CreatePostViewModel model;
    Button overlayButton;
    ImageView selectedImageHoldedr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        CreatePostViewModel createPostViewModel = new ViewModelProvider(this).get(CreatePostViewModel.class);
        ActivityCreatePostBinding activityCreatePostBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_post);
        activityCreatePostBinding.setViewModel(createPostViewModel);
        activityCreatePostBinding.setLifecycleOwner(this);
        selectedImageHoldedr =(ImageView)findViewById(R.id.SelectedImage);
        overlayButton = (Button) findViewById(R.id.PopupOverlayButton_Clicked);
        overlayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Intent i = new Intent(CreatePost.this,FileSelectorPopup.class);
                createPostRegister.launch(i);
            }
        });
    }

    ActivityResultLauncher<Intent> createPostRegister = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    if(intent != null && intent.getBooleanExtra(GlobalConstants.SelectTemplate,false)){
                        Intent i = new Intent(CreatePost.this, Template_2x2.class);
                        showSelectedImage.launch(i);
                    }
                }
            });

    ActivityResultLauncher<Intent> showSelectedImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    if(intent != null){
                        File imagePath = (File)intent.getExtras().get(GlobalConstants.FileUrl);
                        selectedImageHoldedr.setVisibility(View.VISIBLE);
                        overlayButton.setVisibility(View.GONE);
                        selectedImageHoldedr.setImageURI(Uri.fromFile(imagePath));
                    }
                }
            });


}