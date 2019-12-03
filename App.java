import java.util.Iterator;
import java.util.List;

public class App {
    private static DataBoard<Data<?>> board;

    public static void main(String[] args) {

        String password = "garde";

        board = new Board<Data<?>>("garde");
    }

    private static void addPost(String password, Data<?> post, String category) {
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

    private static void removePost(String password, Data<?> post, String category) {
        try {
            board.remove(password, post);
        } catch (InvalidDataException e) {
            System.out.println("Dato non presente nella categoria");
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
        }
    }
    
    private static Data<?> getPost(String password, Data<?> post) {
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

    private static List<Data<?>> getDataCategory(String password, String category) {
        List<Data<?>> dataCategory = null;
        
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

    private static Iterator<Data<?>> getIterator(String password, String category) {
        Iterator<Data<?>> iterator = null;
        
        try {
            iterator = board.getIterator(password);
        } catch (InvalidPasswordException e) {
            System.out.println("Password errata");
            return null;
        }

        return iterator;
    }

    private static void insertLike(String friend, Data<?> dato) {        
        try {
            board.insertLike(friend, dato);
        } catch (InvalidDataException e) {
            System.out.println("Dato non presente nella categoria");
        } catch (InvalidFriendException e) {
            System.out.println(friend + "inesistente");
        }
    }

    private static Iterator<Data<?>> getFriendIterator(String friend) {
        Iterator<Data<?>> iterator = null;
        
        try {
            iterator = board.getFriendIterator(friend);
        } catch (InvalidFriendException e) {
            System.out.println(friend + "inesistente");
        }

        return iterator;
    }
}
