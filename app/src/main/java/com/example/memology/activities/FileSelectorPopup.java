package com.example.memology.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowAnimationFrameStats;
import android.view.WindowManager;
import android.widget.Button;

import com.example.memology.GlobalConstants;
import com.example.memology.R;
import com.example.memology.databinding.Template2x2Binding;

public class FileSelectorPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_selector_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        getWindow().setLayout(width,(int)(height*.25));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);

        Button existingTemplates = (Button) findViewById(R.id.selectExistingTemplatesClicked);
        existingTemplates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(GlobalConstants.SelectTemplate,true);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}