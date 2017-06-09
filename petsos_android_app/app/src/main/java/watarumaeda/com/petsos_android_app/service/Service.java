package watarumaeda.com.petsos_android_app.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

import watarumaeda.com.petsos_android_app.common.PetCallback;
import watarumaeda.com.petsos_android_app.common.PetDetailsCallback;
import watarumaeda.com.petsos_android_app.common.PetImageCallback;
import watarumaeda.com.petsos_android_app.common.PetsCallback;
import watarumaeda.com.petsos_android_app.model.Pet;
import watarumaeda.com.petsos_android_app.model.PetDetail;

public class Service
{
    // Singleton
    private static final Service instance = new Service();
    public static Service shared() {
        return instance;
    }

    // Initialization
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://petsos-377e0.appspot.com");

    // Database
    public void postPet(Pet p) { mDatabase.child("pets/" + p.id).setValue(p.toMap()); }
    public void postPetDetail(PetDetail p)  { mDatabase.child("petDetails/" + p.id).setValue(p.toMap()); }
    public void getPets(final PetsCallback callback)
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

    public void getPet(String id, final PetCallback callback)
    {
        mDatabase.child("pets/" + id).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        Pet p = dataSnapshot.getValue(Pet.class);
                        callback.getPetCallback(true, p);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("getUser:onCancelled", databaseError.toException());
                        callback.getPetCallback(false, new Pet());
                    }
                }
        );
    }

    public void getPetDetails(final PetDetailsCallback callback)
    {
        mDatabase.child("petDetails").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        ArrayList<PetDetail> petDetails = new ArrayList<PetDetail>();
                        for (DataSnapshot child : dataSnapshot.getChildren())  {
                            Log.d("Child", child.getValue().toString());
                            petDetails.add(child.getValue(PetDetail.class));
                        }
                        callback.getPetDetailssCallback(true, petDetails);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        callback.getPetDetailssCallback(false, new ArrayList<PetDetail>());
                    }
                }
        );
    }

    // Storage
    public void getPetImage(String img_name, final PetImageCallback callback)
    {
        StorageReference imageRef = storageRef.child(img_name);

        // Download image
        final long ONE_MEGABYTE = 1024 * 1024;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                if (bytes != null) {
                    Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    callback.getPetImageCallback(true, image);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                callback.getPetImageCallback(false, Bitmap.createBitmap(0,0,Bitmap.Config.ARGB_8888));
            }
        });
    }
}