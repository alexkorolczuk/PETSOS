package watarumaeda.com.petsos_android_app.common;

import java.util.ArrayList;

import watarumaeda.com.petsos_android_app.model.Pet;

public interface PetsCallback {
    void getPetsCallback(Boolean success, ArrayList<Pet> pets);
}
