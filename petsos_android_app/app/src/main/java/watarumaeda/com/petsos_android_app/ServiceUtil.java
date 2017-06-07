package watarumaeda.com.petsos_android_app;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import static watarumaeda.com.petsos_android_app.R.drawable.cat1;

interface PetsCallback {
    void getPetsCallback(Boolean success, ArrayList<Pet> pets);
}

public class ServiceUtil
{
    // Singleton
    private static final ServiceUtil instance = new ServiceUtil();
    public static ServiceUtil shared() {
        return instance;
    }

    // Initialization
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://petsos-377e0.appspot.com");

    // Database
    public void pushPetToDatabase(Pet p)
    {
        mDatabase.child("pets/" + p.id).setValue(p.toMap());
    }
    public void getPet(final PetsCallback callback)
    {
        mDatabase.child("pets").addListenerForSingleValueEvent(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    ArrayList<Pet> pets = new ArrayList<Pet>();
                    for (DataSnapshot child : dataSnapshot.getChildren())  {
                        Log.d("Child", child.getValue().toString());
                        pets.add(child.getValue(Pet.class));
                    }
                    callback.getPetsCallback(true, pets);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("getUser:onCancelled", databaseError.toException());
                    callback.getPetsCallback(false, new ArrayList<Pet>());
                }
            }
        );
    }
}
