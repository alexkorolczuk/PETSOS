package com.example.aleksandrakorolczuk1.ui_helper;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton mButton;
    private EditText mNameEditText;



    private RadioGroup mSexRadioGroup;
    private RadioButton mSexFemale;
    private RadioButton mSexMale;
    private RadioGroup mOKOTHERPETSRadiGroup;
    private RadioButton mOKRadioButton;
    private RadioButton mNOTOKRadioButton;



    private EditText mAgeEditText;
    private Spinner mOwnerTypeSpinner;
    private Spinner mSizeSpinner;
    private EditText mDescriptionEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.animaton_button);
        setContentView(R.layout.activity_main);

        //-----------add photo button+ animation----------------
        mButton = (ImageButton) findViewById(R.id.plus_button);
        mButton.setAnimation(myAnim);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(myAnim);
            }
        });




        //-----------edit name----------------
        mNameEditText = (EditText) findViewById(R.id.add_name_edit);
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });


        //-----------check female or male----------------

        mSexRadioGroup = (RadioGroup) findViewById(R.id.radioSex);

        mSexFemale = (RadioButton) findViewById(R.id.checkbox_female);
        mSexFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSexMale = (RadioButton) findViewById(R.id.checkbox_male);
        mSexMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mSexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                Log.d("onCheckedChanged", Integer.toString(isChecked));
            }
        });





        //-----------edit age----------------
        mAgeEditText = (EditText) findViewById(R.id.add_age_edit);
        mAgeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        //-----------choose ownertype SPINNER----------------

        Spinner mOwnerTypeSpinner = (Spinner) findViewById(R.id.spinner_owner_type);
        ArrayAdapter<CharSequence> adapter_owner = ArrayAdapter.createFromResource(this,
                R.array.array_owner_types, android.R.layout.simple_spinner_item);
        adapter_owner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mOwnerTypeSpinner.setAdapter(adapter_owner);

        //-----------choose size SPINNER----------------

        Spinner mSizeSpinner = (Spinner) findViewById(R.id.spinner_size);
        ArrayAdapter<CharSequence> adapter_size = ArrayAdapter.createFromResource(this,
                R.array.array_pet_sizes, android.R.layout.simple_spinner_item);
        adapter_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSizeSpinner.setAdapter(adapter_size);

        //-----------check OK it other pets----------------

        mOKOTHERPETSRadiGroup = (RadioGroup) findViewById(R.id.radioOwner);

        mOKRadioButton = (RadioButton) findViewById(R.id.radio_OK);
        mOKRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mNOTOKRadioButton = (RadioButton) findViewById(R.id.radio_NOTOK);
        mNOTOKRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mOKOTHERPETSRadiGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                Log.d("onCheckedChanged", Integer.toString(isChecked));
            }
        });



        //-----------edit description----------------


        mDescriptionEditText = (EditText) findViewById(R.id.description_edit);
        mDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });





    }


}

