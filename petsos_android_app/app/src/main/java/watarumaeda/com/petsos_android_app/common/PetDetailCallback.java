package watarumaeda.com.petsos_android_app.common;

import watarumaeda.com.petsos_android_app.model.PetDetail;

public interface PetDetailCallback {
    void getPetDetailCallback(Boolean success, PetDetail pd);
}
