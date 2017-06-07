package watarumaeda.com.petsos_android_app;

import java.util.HashMap;
import java.util.Map;

public class Pet
{
    public String id;
    public String img_url;
    public String name;
    public String breed;
    public String age;

    // Constractor
    public Pet() {}
    public Pet(String id,  String img_url,  String name,  String breed, String age)
    {
        this.id = id;
        this.img_url = img_url;
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    // Supporting function
    public Map<String, String> toMap()
    {
        Map<String, String> map = new HashMap<String, String>() {
            {
                put("id", id);
                put("img_url", img_url);
                put("name", name);
                put("breed", breed);
                put("age", age);
            }
        };

        return map;
    }
}
