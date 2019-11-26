import java.util.Iterator;

public class App {
    public static void main(String[] args) {
        DataBoard board = new Board<Data<?>>("garde");
        String category1 = "Category1";
        String category2 = "Category2";
        Data post1 = new myData<String>("Buongiornissimo caffe");
        Data post2 = new myData<String>("Buonasera");
        board.createCategory(category1, "garde");
        board.createCategory(category2, "garde");
        post2.insertLike();
        post1.insertLike();
        post1.insertLike();
        board.put("garde", post1, category1);
        board.put("garde", post2, category2);
        Iterator<Data> iter = board.getIterator("garde");

        while(iter.hasNext())
        {
            iter.next().Display();
        }
    }
}