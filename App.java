import java.util.Iterator;
import java.util.List;

public class App {
    private static DataBoard<myData<?>> board;

    public static void main(String[] args) {

        String password = "garde";

        /************************ /
        /      Prima batteria     / 
        /         di test         / 
        /*************************/
        System.out.println();
        System.out.println("Prima batteria di test:");
        board = new Board<myData<?>>("garde");
        test();


        /************************ /
        /     Seconda batteria    / 
        /         di test         / 
        /*************************/
        System.out.println();
        System.out.println("Seconda batteria di test:");
        board = new Board2<myData<?>>("garde");
        test();
    }

    private static void test() {
        // Posts
        myData<String> post1 = new myData<String>("Garde iliu");
        myData<String> post2 = new myData<String>("Buongiorno");
        myData<String> post3 = new myData<String>("Oggi è bel tempo");

        // Freinds
        String friend1 = "iliu";
        String friend2 = "Mario";

        createCategory("garde", "animali");
        createCategory("garde", "tempo");
        addFriend("garde", friend1, "animali");
        addFriend("garde", friend1, "tempo");
        addFriend("garde", friend2, "animali");
        addPost("garde", post1, "animali");
        addPost("garde", post2, "animali");
        addPost("garde", post1, "tempo");
        addPost("garde", post3, "tempo");
        insertLike(friend1, post1);
        insertLike(friend1, post2);
        insertLike("Mario", post2);

        Iterator<myData<?>> iterator =  getIterator("garde");
        System.out.println("Tutti i post in bacheca");
        while(iterator.hasNext()) {
            iterator.next().Display();
        }

        System.out.println("Post visibili all'amico " + friend1);
        iterator = getFriendIterator(friend1);
        while(iterator.hasNext()) {
            iterator.next().Display();
        }

        System.out.println("Post visibili all'amico " + friend2);
        iterator = getFriendIterator(friend2);
        while(iterator.hasNext()) {
            iterator.next().Display();
        }
    }

    private static void addPost(String password, myData<?> post, String category) {
        try {
            board.put(password, post, category);
        } catch (DuplicateDataException e) {
            System.out.println("Dato gia' presente nella categoria");
        } catch (InvalidCategoryExcetpion e) {
            System.out.println(category + " non esistente");
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
        }
    }

    private static void removePost(String password, myData<?> post, String category) {
        try {
            board.remove(password, post);
        } catch (InvalidDataException e) {
            System.out.println("Dato non presente nella categoria");
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
        }
    }
    
    private static Data<?> getPost(String password, myData<?> post) {
        Data<?> dato = null;
        
        try {
            dato = board.get(password, post);
        } catch (InvalidDataException e) {
            System.out.println("Dato non presente nella categoria");
            return null;
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
            return null;
        }

        return dato;
    }

    private static void createCategory(String password, String categoryName) {
        try {
            board.createCategory(categoryName, password);
        } catch (ExistingCategoryException e) {
            System.out.println(categoryName + " gia' esistente");
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
        }
    }

    private static void removeCategory(String password, String category) {
        try {
            board.removeCategory(category, password);
        } catch (InvalidCategoryExcetpion e) {
            System.out.println(category + " non esistente");
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
        }
    }

    private static void addFriend(String password, String friend, String category) {
        try {
            board.addFriend(category, password, friend);
        } catch (InvalidCategoryExcetpion e) {
            System.out.println(category + " non esistente");
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
        } catch (ExistingFriendException e) {
            System.out.println(friend + "gia' presente in" + category);
        }
    }

    private static void removeFriend(String password, String friend, String category) {
        try {
            board.removeFriend(category, password, friend);
        } catch (InvalidCategoryExcetpion e) {
            System.out.println(category + " non esistente");
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
        } catch (InvalidFriendException e) {
            System.out.println(friend + "non presente nella categoria" + category);
        }
    }

    private static List<myData<?>> getDataCategory(String password, String category) {
        List<myData<?>> dataCategory = null;
        
        try {
            dataCategory = board.getDataCategory(password, category);
        } catch (InvalidCategoryExcetpion e) {
            System.out.println(category + " non esistente");
            return null;
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
            return null;
        }

        return dataCategory;
    }

    private static Iterator<myData<?>> getIterator(String password) {
        Iterator<myData<?>> iterator = null;
        
        try {
            iterator = board.getIterator(password);
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
            return null;
        }

        return iterator;
    }

    private static void insertLike(String friend, myData<?> dato) {        
        try {
            board.insertLike(friend, dato);
        } catch (InvalidDataException e) {
            System.out.println("Dato non presente nella categoria");
        } catch (InvalidFriendException e) {
            System.out.println(friend + "inesistente");
        }
    }

    private static Iterator<myData<?>> getFriendIterator(String friend) {
        Iterator<myData<?>> iterator = null;
        
        try {
            iterator = board.getFriendIterator(friend);
        } catch (InvalidFriendException e) {
            System.out.println(friend + "inesistente");
        }

        return iterator;
    }
}
