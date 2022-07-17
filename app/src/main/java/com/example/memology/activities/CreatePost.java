package com.example.memology.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memology.DB.DBHolder;
import com.example.memology.DB.DatabaseHandler;
import com.example.memology.GlobalConstants;
import com.example.memology.R;
import com.example.memology.databinding.ActivityCreatePostBinding;
import com.example.memology.models.Memes;
import com.example.memology.viewmodels.CreatePostViewModel;

import java.io.File;
import java.util.List;

public class CreatePost extends AppCompatActivity implements  View.OnClickListener {

    Button overlayButton,savePostButton;
    ImageView selectedImageHoldedr;
    CreatePostViewModel createPostViewModel;
    Uri imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        createPostViewModel = new ViewModelProvider(this).get(CreatePostViewModel.class);
        ActivityCreatePostBinding activityCreatePostBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_post);
        activityCreatePostBinding.setViewModel(createPostViewModel);
        activityCreatePostBinding.setLifecycleOwner(this);
        selectedImageHoldedr =(ImageView)findViewById(R.id.SelectedImage);
        overlayButton = (Button) findViewById(R.id.PopupOverlayButton_Clicked);
        savePostButton = (Button)findViewById(R.id.saveLayoutButton);
        savePostButton.setOnClickListener(this);
        overlayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Intent i = new Intent(CreatePost.this,FileSelectorPopup.class);
                createPostRegister.launch(i);
            }
        });
    }

    private NotificationCompat.Builder showNotification(){
        return new NotificationCompat.Builder(this, GlobalConstants.PostCreationNotificationChannelID)
                .setSmallIcon(R.drawable.ic_menu_gallery)
                .setContentTitle("Post created succesfully")
                .setContentText("Your post is available now")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveLayoutButton:
                try {
                    DatabaseHandler db = DBHolder.dbHandler;
                    db.postMemes(new Memes(createPostViewModel.title, imagePath, null));
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                    notificationManager.notify(1,showNotification().build());
                    Toast.makeText(this,"Successfully posted", Toast.LENGTH_SHORT ).show();
                    finish();
                }catch (Exception e){
                    Toast.makeText(this,"Error while posting", Toast.LENGTH_SHORT ).show();
                }
                break;
        }

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
                        File image = (File)intent.getExtras().get(GlobalConstants.FileUrl);
                        selectedImageHoldedr.setVisibility(View.VISIBLE);
                        overlayButton.setVisibility(View.GONE);
                        imagePath = Uri.fromFile(image);
                        selectedImageHoldedr.setImageURI(imagePath);
                    }
                }
            });



}