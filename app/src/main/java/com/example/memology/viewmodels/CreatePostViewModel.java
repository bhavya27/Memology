package com.example.memology.viewmodels;

import android.app.Application;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.memology.BR;

public class CreatePostViewModel extends AndroidViewModel implements Observable, DefaultLifecycleObserver {
    //region Observable callbacks
    private final PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    void notifyChange(){
        callbacks.notifyCallbacks(this,0,null);
    }

    void notifyPropertyChanged(int fieldId) {
        callbacks.notifyCallbacks(this, fieldId, null);
    }
    //endregion

    public String title;
    public int titleLength = 0;
    public CreatePostViewModel(@NonNull Application application) {
        super(application);
    }

    @Bindable
    public String getTitle(){
        return title;
    }

    @Bindable
    public void setTitle(String title){
        this.title = title;
        notifyPropertyChanged(BR.titleLength);
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public int getTitleLength(){
        if(title != null && !title.isEmpty()){
            titleLength = title.length();
        }
        return titleLength;
    }

    @Bindable
    public void setTitleLength(int titleLength){
        this.titleLength = titleLength;
        notifyPropertyChanged(BR.titleLength);
    }

    public void TitleEntered(){
        if(getTitle() != null)
        {
            setTitleLength(title.length());
        }
        else
        {
            setTitleLength(0);
        }
    }




}
