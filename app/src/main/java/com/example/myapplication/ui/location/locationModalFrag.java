package com.example.myapplication.ui.location;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.myapplication.R;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;




public class locationModalFrag extends BottomSheetDialogFragment {



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_store_list, container, false);
        return view;
    }


}
