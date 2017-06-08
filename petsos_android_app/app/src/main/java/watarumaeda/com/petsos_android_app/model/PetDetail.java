package watarumaeda.com.petsos_android_app.model;

public class PetDetail
{
    public String id;
    public String img_url;
    public String about;
    public String location;
    public String contact;

    // Parse json data to petDrtail data class
    public static PetDetail createFromJson()
    {
        // TODO: Parse process..

        return  new PetDetail();
    }
}
