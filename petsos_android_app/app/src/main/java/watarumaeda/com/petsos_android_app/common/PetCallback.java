package watarumaeda.com.petsos_android_app.common;

import watarumaeda.com.petsos_android_app.model.Pet;

public interface PetCallback {
    void getPetCallback(Boolean success, Pet pet);
}
