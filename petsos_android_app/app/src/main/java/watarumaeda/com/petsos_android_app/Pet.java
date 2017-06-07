package watarumaeda.com.petsos_android_app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Pet
{
    public String id;
    public String img_url;
    public String name;
    public String breed;
    public String age;

    // Parse json data to pet data class
    public static Pet toPet(Map<String, String> map)
    {
        Pet p = new Pet();
        p.id = map.get("id");
        p.img_url = map.get("img_url");
        p.name = map.get("name");
        p.breed = map.get("breed");
        p.age = map.get("age ");

        return p;
    }

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
