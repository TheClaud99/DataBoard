import java.util.List;

public class Category<E extends Data> {

    private String categoryName;
    private List<E> dataSet;
    private List<String> friends;

    public Category(String categoryName)
    {
        this.categoryName = categoryName;
        this.dataSet = (List<E>) new Object();
        this.friends = (List<String>) new Object();
    }

    public String getCategoryName()
    {
        return this.categoryName;
    }

    public List<E> getData()
    {
        return dataSet;
    }

    public List<String> getFriends()
    {
        return friends;
    }

    public void addFriend(String friend) throws ExistingFriendException {
        if(this.friends.contains(friend)) throw new ExistingFriendException();
        this.friends.add(friend);
    }

    public void addData(E data) throws DuplicateDataException {
        if(this.dataSet.contains(data)) throw new DuplicateDataException();
        this.dataSet.add(data);
    }

    public int numData() {
        return this.dataSet.size();
    }

    public int numFriends() {
        return this.friends.size();
    }

    public void removeDataIfExists(E data) {
        this.dataSet.remove(data);
    }
}