package watarumaeda.com.petsos_android_app.view.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

import watarumaeda.com.petsos_android_app.R;
import watarumaeda.com.petsos_android_app.common.PetImageUploadCallback;
import watarumaeda.com.petsos_android_app.model.Pet;
import watarumaeda.com.petsos_android_app.model.PetDetail;
import watarumaeda.com.petsos_android_app.service.Service;
import watarumaeda.com.petsos_android_app.view.fragment.MissingDialogFlagment;

import static android.app.Activity.RESULT_OK;

public class PostActivity extends AppCompatActivity
{
    private static int RESULT_LOAD_IMG = 1;

    private ImageButton mButton;
    private EditText mNameEditText;
    private ImageView mPetImgv;

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

    private Button mBtnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.animaton_button);
        setContentView(R.layout.activity_post);


        //-----------add photo button + animation----------------
        mPetImgv = (ImageView) findViewById(R.id.pet_imgv);
        mButton = (ImageButton) findViewById(R.id.plus_button);
        mButton.setAnimation(myAnim);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(myAnim);

                // Show images from library
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
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
        mSexMale = (RadioButton) findViewById(R.id.checkbox_male);
        mSexMale.setChecked(true);
        mSexMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mSexFemale = (RadioButton) findViewById(R.id.checkbox_female);
        mSexFemale.setOnClickListener(new View.OnClickListener() {
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
        mOKRadioButton.setChecked(true);
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


        // ----- Post pet -------
        mBtnPost = (Button) findViewById(R.id.btn_post);
        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canPost()) upload();
            }
        });
    }

    private void upload()
    {
        final String key = UUID.randomUUID().toString();    // GUID
        Bitmap imgPet = getImage();                         // Pet image bitmap

        // Update pet image
        Service.shared().postPetImage(imgPet, key, new PetImageUploadCallback() {
            @Override
            public void didUpload(Boolean success, String img_url) {
                if (success)
                {
                    // Uoload pet and petDetails
                    Pet pet = getPet(key);
                    PetDetail petDetail = getPetDetail(key);
                    Service.shared().postPet(pet);
                    Service.shared().postPetDetail(petDetail);

                    // Show dialog
                    DialogFragment newFragment = new MissingDialogFlagment("Updated", "your pet informaion updated");
                    newFragment.show(getFragmentManager(), "post_timeline: succeed");
                }
                else
                {
                    DialogFragment newFragment = new MissingDialogFlagment("Error", "Failed to upload data. Please try again");
                    newFragment.show(getFragmentManager(), "failed_upload");
                }
            }
        });
    }

    private Boolean canPost()
    {
        if (mNameEditText.getText().length() == 0) {
            DialogFragment newFragment = new MissingDialogFlagment("Data missing", "Please fill name field.");
            newFragment.show(getFragmentManager(), "missing_name");
            return false;
        }
        if (mAgeEditText.getText().length() == 0) {
            DialogFragment newFragment = new MissingDialogFlagment("Data missing", "Please fill age field.");
            newFragment.show(getFragmentManager(), "missing_age");
            return false;
        }
        if (mDescriptionEditText.getText().length() == 0) {
            DialogFragment newFragment = new MissingDialogFlagment("Data missing", "Please fill description field.");
            newFragment.show(getFragmentManager(), "missing_description");
            return false;
        }

        return true;
    }

    private Pet getPet(String key)
    {
        Pet p = new Pet();
        p.id = key;
        p.img_url = key + ".jpg";
        p.name = mNameEditText.getText().toString();
        p.breed = "Hound";
        p.age = mAgeEditText.getText().toString();
        return  p;
    }

    private PetDetail getPetDetail(String key)
    {
        PetDetail p = new PetDetail();
        p.id = key;
        p.img_url = key + ".jpg";
        p.about = mDescriptionEditText.getText().toString();
        p.sex = 0;
        p.owner_type = 0;
        p.other_pets = true;
        p.contact = "xxx@gmail.com";
        return  p;
    }

    private Bitmap getImage()
    {
        mPetImgv.setDrawingCacheEnabled(true);
        mPetImgv.buildDrawingCache();
        return mPetImgv.getDrawingCache();
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                mPetImgv.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(PostActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(PostActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}
