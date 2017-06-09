package watarumaeda.com.petsos_android_app.common;

import java.util.ArrayList;

import watarumaeda.com.petsos_android_app.model.PetDetail;

public interface PetDetailsCallback {
    void getPetDetailssCallback(Boolean success, ArrayList<PetDetail> petDetails);
}
