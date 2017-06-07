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
    void getPetsData(Boolean success, ArrayList<Pet> pets);
}

public class ServiceUtil
{
    // Singleton
    private static final ServiceUtil instance = new ServiceUtil();
    public static ServiceUtil shared() {
        return instance;
    }

    // Initialize
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://petsos-377e0.appspot.com");

    public void pushPetToDatabase(Pet p)
    {
        mDatabase.child("pets/" + p.id).setValue(p.toMap());
    }

//    public void getPetData()
//    {
//        mDatabase.child("pets").addListenerForSingleValueEvent(
//            new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    // Get user value
//                    Pet pet = dataSnapshot.getValue(Pet.class);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Log.w("getUser:onCancelled", databaseError.toException());
//                }
//        });
//    }

    public void getPetData(final PetsCallback callback)
    {
        mDatabase.child("pets").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
//                        Pet pet = dataSnapshot.getValue(Pet.class);
                        Log.d("aaaaa", dataSnapshot.getValue().toString());
//                        System.out.print(pet);

                        callback.getPetsData(true, new ArrayList<Pet>());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("getUser:onCancelled", databaseError.toException());
                        callback.getPetsData(false, new ArrayList<Pet>());
                    }
                });
    }

    public ArrayList<Pet> getTestData()
    {
        ArrayList<Pet> arr = new ArrayList<Pet>();

        Pet p1 = new Pet();
        p1.id = "1";
        p1.img_url = "cat1.jpg";
        p1.name = "Bob";
        p1.breed = "Affenpinscher";
        p1.age = "5";
        pushPetToDatabase(p1);
        arr.add(p1);

        Pet p2 = new Pet();
        p2.id = "2";
        p2.img_url = "cat2.jpg";
        p2.name = "Sum";
        p2.breed = "Balinese";
        p2.age = "6";
        pushPetToDatabase(p2);
        arr.add(p2);

        Pet p3 = new Pet();
        p3.id = "3";
        p3.img_url = "cat3.jpg";
        p3.name = "Sum";
        p3.breed = "Chartreux";
        p3.age = "7";
        pushPetToDatabase(p3);
        arr.add(p3);

        Pet p4 = new Pet();
        p4.id = "4";
        p4.img_url = "cat4.jpg";
        p4.name = "Kate";
        p4.breed = "Devon Rex";
        p4.age = "8";
        pushPetToDatabase(p4);
        arr.add(p4);

        Pet p5 = new Pet();
        p5.id = "5";
        p5.img_url = "cat5.jpg";
        p5.name = "Missel";
        p5.breed = "Egyptian Mau";
        p5.age = "9";
        pushPetToDatabase(p5);
        arr.add(p5);

        Pet p6 = new Pet();
        p6.id = "6";
        p6.img_url = "cat6.jpg";
        p6.name = "Kitty";
        p6.breed = "Havana Brown";
        p6.age = "10";
        arr.add(p6);

        return arr;
    }
}
