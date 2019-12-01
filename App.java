import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        DataBoard<Data<?>> board = new Board<Data<?>>("garde");
        String category1 = "Category1";
        String category2 = "Category2";
        Data<String> post1 = new myData<String>("Buongiornissimo caffe");
        Data<String> post2 = new myData<String>("Buonasera");
        Data<Integer> post3 = new myData<Integer>(3);
        board.createCategory(category1, "garde");
        board.createCategory(category2, "garde");
        board.addFriend(category1, "garde", "iliu");
        
        board.put("garde", post1, category1);
        board.put("garde", post3, category1);
        board.put("garde", post2, category2);

        board.insertLike("iliu", post1);
        board.insertLike("iliu", post2);
        board.insertLike("iliu", post1);

        Data<?> post4 = board.get("garde", post1);
        board.put("garde", post4, category1);
        board.insertLike("iliu", post4);
        Iterator<Data<?>> iter = board.getFriendIterator("iliu");

        while(iter.hasNext())
        {
            iter.next().Display();
        }

        board.removeFriend(category1, "garde", "iliu");

        // iter = board.getFriendIterator("iliu"); // should thorw InvalidFriendException
    }
}