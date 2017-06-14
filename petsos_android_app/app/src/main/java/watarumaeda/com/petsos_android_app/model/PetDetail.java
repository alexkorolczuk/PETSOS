package watarumaeda.com.petsos_android_app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import watarumaeda.com.petsos_android_app.service.Service;

public class PetDetail
{
    public String id;
    public String img_url;
    public String about;
    public int sex;
    public int owner_type;
    public Boolean other_pets;
    public int size_type;
    public String contact;

    public PetDetail() {}
    public PetDetail(String id, String img_url, String about, int sex, int owner_type, Boolean other_pets, int size_type, String contact)
    {
        this.id = id;
        this.img_url = img_url;
        this.about = about;
        this.sex = sex;
        this.owner_type = owner_type;
        this.other_pets = other_pets;
        this.size_type = size_type;
        this.contact = contact;
    }

    // Parse json data to petDrtail data class
//    public static ArrayList<PetDetail> getTestPetsDetail()
//    {
//        ArrayList<PetDetail> pets = new ArrayList<PetDetail>();
//        pets.add(new PetDetail("1", "cat1.jpg", "Hartworm negative! vacconated & dewormed!", 1, 0, true, 1, "abc@gmail.com"));
//        pets.add(new PetDetail("2", "cat2.jpg", "Hartworm negative! vacconated & dewormed!", 0, 1, false, 0, "cde@gmail.com"));
//        pets.add(new PetDetail("3", "cat3.jpg", "Hartworm negative! vacconated & dewormed!", 0, 1, true, 1, "fgh@gmail.com"));
//        pets.add(new PetDetail("4", "cat4.jpg", "Hartworm negative! vacconated & dewormed!", 1, 0, false, 1, "ijk@gmail.com"));
//        pets.add(new PetDetail("5", "cat5.jpg", "Hartworm negative! vacconated & dewormed!", 0, 1, true, 0, "lmn@gmail.com"));
//        pets.add(new PetDetail("6", "cat6.jpg", "Hartworm negative! vacconated & dewormed!", 1, 1, false, 1, "ogp@gmail.com"));
//        for (PetDetail p : pets) Service.shared().postPetDetail(p);
//        return  pets;
//    }

    // Supporting function
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("id", id);
                put("img_url", img_url);
                put("about", about);
                put("sex", sex);
                put("owner_type", owner_type);
                put("other_pets", other_pets);
                put("size", size_type);
                put("contact", contact);
            }
        };

        return map;
    }

}
