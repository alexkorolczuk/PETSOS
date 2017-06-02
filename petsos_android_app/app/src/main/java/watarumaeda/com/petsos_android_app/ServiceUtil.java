package watarumaeda.com.petsos_android_app;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceUtil
{
    // Singleton
    private static final ServiceUtil instance = new ServiceUtil();
    public static ServiceUtil shared() {
        return instance;
    }

    // initialize_database_ref
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public void push()
    {
        mDatabase.child("pet").setValue("Hello world");
    }
}
