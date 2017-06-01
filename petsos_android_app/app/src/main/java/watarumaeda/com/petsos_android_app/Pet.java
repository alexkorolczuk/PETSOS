package watarumaeda.com.petsos_android_app;

public class Pet
{
    public String id;
    public String img_url;
    public String name;
    public String breed;
    public String age;

    // Parse json data to pet data class
    public static Pet createFromJson()
    {
        // TODO: Parse process..

        return new Pet();
    }
}
