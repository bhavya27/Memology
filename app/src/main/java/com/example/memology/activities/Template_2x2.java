package com.example.memology.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.memology.GlobalConstants;
import com.example.memology.databinding.Template2x2Binding;
import com.example.memology.viewmodels.Template2x2ViewModel;
import com.example.memology.R;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

public class Template_2x2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Template2x2Binding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.template_2x2);
        Template2x2ViewModel mainActivityViewModel = new Template2x2ViewModel(getActivityResultRegistry());
        activityMainBinding.setViewModel(mainActivityViewModel);
        getLifecycle().addObserver(mainActivityViewModel);
        Button saveLayout = (Button) findViewById(R.id.saveLayoutButton);
        saveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridLayout content = (GridLayout) findViewById(R.id.TemplateLayout);
                content.setDrawingCacheEnabled(true);
                Bitmap bitmap = content.getDrawingCache();
                Context context = Template_2x2.this;

                String fileName = "Meme_" + new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Calendar.getInstance().getTime())+".jpg";
                File f = new File(context.getFilesDir(), fileName);
                try {
                    try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
                        bitmap.compress(Bitmap.CompressFormat.PNG,10,fos);
                    }
                }catch (Exception ex){
                    Toast.makeText(context,ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
                for (String file:context.fileList()) {
                    if(file.equals(fileName)){
                        Toast.makeText(context,"Image stored successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra(GlobalConstants.FileUrl, f);
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                }
            }

        });
    }


}