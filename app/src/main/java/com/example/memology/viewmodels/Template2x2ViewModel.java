package com.example.memology.viewmodels;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.example.memology.BR;
import com.example.memology.enums.ImageTypes;

import org.jetbrains.annotations.NotNull;

public class Template2x2ViewModel extends BaseObservable implements DefaultLifecycleObserver {
    public int isFirstImageVisible = View.GONE;
    public int isSecondImageVisible = View.GONE;
    public Uri firstImageUri,secondImageUri;
    boolean isFirstUploaderActive,isSecondUploaderActive;
    private final ActivityResultRegistry mRegistry;
    private ActivityResultLauncher<Intent> mLauncher;
    String firstText,secondText = "Enter 2nd Text";

    public Template2x2ViewModel(@NotNull ActivityResultRegistry registry){
        mRegistry = registry;
    }

    //region Getter Setter Properties
    @Bindable
    public Uri getFirstImageUri() {
        return firstImageUri;
    }
    @Bindable
    public String getFirstText(){
        return firstText;
    }
    @Bindable
    public void setFirstText(String firstText){
        this.firstText = firstText;
        notifyPropertyChanged(BR.firstText);
    }
    @Bindable
    public String getSecondText(){
        return secondText;
    }
    @Bindable
    public void setSecondText(String secondText){
        this.secondText = secondText;
        notifyPropertyChanged(BR.secondText);
    }


    @Bindable
    public void setFirstImageUri(Uri firstImageUri) {
        this.firstImageUri = firstImageUri;
        if(firstImageUri == null || firstImageUri.toString().isEmpty()){
            setIsFirstImageVisible(View.GONE);
        }else{
            setIsFirstImageVisible(View.VISIBLE);
        }
        notifyPropertyChanged(BR.firstImageUri);
    }
    @Bindable
    public Uri getSecondImageUri() {
        return secondImageUri;
    }

    @Bindable
    public void setSecondImageUri(Uri secondImageUri) {
        this.secondImageUri = secondImageUri;
        if(secondImageUri == null || secondImageUri.toString().isEmpty()){
            setIsSecondImageVisible(View.GONE);
        }else{
            setIsSecondImageVisible(View.VISIBLE);
        }
        notifyPropertyChanged(BR.secondImageUri);
    }

    @Bindable
    public int getIsFirstImageVisible() {
        return isFirstImageVisible;
    }

    @Bindable
    public void setIsFirstImageVisible(int firstImageVisible) {
        isFirstImageVisible = firstImageVisible;
        notifyPropertyChanged(BR.isFirstImageVisible);
    }

    @Bindable
    public int getIsSecondImageVisible() {
        return isSecondImageVisible;
    }

    @Bindable
    public void setIsSecondImageVisible(int secondImageVisible) {
        isSecondImageVisible = secondImageVisible;
        notifyPropertyChanged(BR.isSecondImageVisible);
    }
    //endregion

    public void OnImageUploaderClicked(View view, ImageTypes imageTypes){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        if(imageTypes == ImageTypes.FirstImage){
            isFirstUploaderActive = true;
            isSecondUploaderActive = false;
        } else{
            isFirstUploaderActive = false;
            isSecondUploaderActive = true;
        }
        mLauncher.launch(galleryIntent);
    }

    public void onCreate(@NotNull LifecycleOwner owner){
        mLauncher = mRegistry.register("key",owner,
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        if(intent == null){
                            return;
                        }
                        if(isFirstUploaderActive){
                            setFirstImageUri(intent.getData());
                        }
                        else if(isSecondUploaderActive){
                            setSecondImageUri(intent.getData());
                        }
                    }
                });
    }



}
