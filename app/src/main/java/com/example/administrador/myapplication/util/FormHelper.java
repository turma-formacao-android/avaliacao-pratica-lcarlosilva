package com.example.administrador.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

import com.example.administrador.myapplication.R;

public final class FormHelper {
    private FormHelper(){}


    public static boolean requireValidate(Context context, EditText... editTexts){
        Boolean validated = Boolean.TRUE;
        for(EditText editText : editTexts){
            if(editText.getText() == null || editText.getText().toString().trim().isEmpty()){
                editText.setError(context.getString(R.string.required_field));
                validated = Boolean.FALSE;
            }
        }
        return validated;
    }


}
