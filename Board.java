import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Board<E extends Data> implements DataBoard<E> {

    /**
     * 
     * Overview:    contenitore di oggetti generici che estendono il tipo di dato Data. Ogni
     *              dato presente nella bacheca ha associato la categoria del dato.
     * 
     *              categories = elements.values()
     * 
     * AF:          <password, { el_0, ..., el_i, ..., el_categories.size() }> con
     *                  forall i = 0, ..., categories.size() | 
     *                      el_i = <categories.get(i).getCategoryName, categories.get(i).getData, categories.get(i).getFriends()>
     *                      categories.get(i).categoryName != null    
     * 
     * IR:          password != null && 
     *              ( forall i = 1, ..., categories.size()  |
     *                      categories.get(i).categoryName != null 
     *                      && ( forall j = 0, ..., categories.size() | categories.get(i).getCategoryName() != categories.get(i).categoryName() ) )
     *                      && ( forall k, l = 0, ..., categories.size() | k != l => categories.get(i).getFriend(k) != categories.get(i).getFriend(l) )
     *                      && ( forall m, n = 0, ..., categories.size() | m != n => categories.get(i).getData(m) != categories.get(i).getData(n) ) )
     *                      
     */

    private HashMap<String, Category<E>> elements;
    private int dim;
    private String password;


    public Board(String password)
    {
        this.password = password;
        this.dim = 0;
        this.elements = new HashMap<String, Category<E>>();
    }

     /**
     * Crea l’identità una categoria di dati
     * 
     * @param Category t.c. Category != null && (forall i = 1, ...., numCategories() | el_i.categoryName != Category)
     * @param passw t.c. password = passw
     * @modifies this.elems
     * @throws NullPointerException if Category = null
     * @throws InvalidPasswordException if passw != this.password
     * @throws ExistingCategoryException if (exist i = 1, ..., numCategories() | el_i.categoryName = Category)
     * @effects post(this.elems) = pre(this.el_i) U <Category, null, null>
     */
    public void createCategory(String Category, String passw) throws NullPointerException, InvalidPasswordException, ExistingCategoryException {
        if(Category == null) throw new NullPointerException();
        if(passw != this.password) throw new InvalidPasswordException();
        if(this.elements.get(Category) != null) throw new ExistingCategoryException();

        elements.put(Category, new Category<E>(Category));
        if(this.elements.containsKey(Category)) dim++;
    }
    
    // Rimuove l’identità una categoria di dati
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param passw t.c. password = this.passw
     * @modifies this.elems
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @throws InvalidPasswordException if passw != this.password
     * @throws NullPointerException if Category = null
     * @effects post(this.elems) = pre(this.elems) \ el_i
     */
    public void removeCategory(String Category, String passw) throws InvalidCategoryExcetpion, InvalidPasswordException {
        if(Category == null) throw new NullPointerException();
        if(this.elements.get(Category) == null) throw new InvalidCategoryExcetpion();
        if(passw != this.password) throw new InvalidPasswordException();

        elements.remove(Category);
    }

    // Aggiunge un amico ad una categoria di dati
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param passw t.c. password = this.passw
     * @param friend t.c. friend != null and (forall j = 1, ...., numFriendns(Category) | el_i.friend[j] != friend)
     * @modifies this.el_i.friends
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @throws ExistingFriendException if (exist j = 1, ...., numFriendns(Category) | el_i.friend[j] = friend)
     * @throws InvalidPasswordException if passw != this.password
     * @effects post(this.el_i.friends) = pre(this.el_i.friends) U friend
     */
    public void addFriend(String Category, String passw, String friend) throws InvalidCategoryExcetpion, ExistingFriendException, InvalidPasswordException {
        if(this.elements.get(Category) == null) throw new InvalidCategoryExcetpion();
        if(this.elements.get(Category).getFriends().contains(friend)) throw new ExistingFriendException();
        if(passw != this.password) throw new InvalidPasswordException();

        try {
            this.elements.get(Category).addFriend(friend);
        } catch(Exception e) {
            System.out.println("Amico gia' presnte");
        }
    }

    // rimuove un amico ad una categoria di dati
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param friend t.c. friend != null and (exist j = 1, ...., numFriendns(Category) | el_i.friend[j] = friend)
     * @param passw t.c. password = this.passw
     * @modifies this.el_i.friends
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @throws InvalidPasswordException if passw != this.password
     * @throws InvalidFriendException if (forall j = 1, ...., numFriendns(Category) | el_i.friend[j] != friend)
     * @effects post(this.el_i.friends) = pre(this.el_i.friends) \ friend
     */
    public void removeFriend(String Category, String passw, String friend) {}
    
    // Inserisce un dato in bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param dato t.c. dato != null
     * @param passw t.c. password = this.passw
     * @modifies this.el_i.data
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @throws InvalidPasswordException if passw != this.password
     * @throws DuplicateDataException if exist h = 1, ...., numData(categoria) | data = el_i.data[h]
     * @effects post(this.el_i.data) = pre(this.el_i.dataSet) U dato
     */
    public boolean put(String passw, E dato, String categoria) {
        return true;
    }
    
    // Ottiene una copia del del dato in bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param passw this.password = passw
     * @param dato t.c. exist i = 1, ...., numCategories | ( exist j = 1, ..., numData(el_i.categoryName) | el_i.data[j] = dato )
     * @throws InvalidDataException if forall k = 1, ...., numCategories | ( forall h = 1, ..., numData(el_i.categoryName) | el_k.data[h] != dato )
     * @throws InvalidPasswordException if passw != this.password
     * @return this.el_i.data[j]
     */
    public E get(String passw, E dato) {
        Collection<Category<E>> categories = this.elements.values();

        for(Category<E> category: categories) {
            for(E dataEl : category.getData()) {
                if(dato.equals(dataEl)) return dato;
            }
        }

        throw new InvalidDataException();
    }

    // Rimuove il dato dalla bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param dato t.c. exist i = 1, ...., numCategories | ( exist j = 1, ..., numData(el_i.categoryName) | el_i.data[j] = dato )
     * @param passw t.c. password = this.password
     * @modifies this.el_i.data
     * @throws InvalidPasswordException if passw != this.password
     * @throws InvalidDataException if forall k = 1, ...., numCategories | ( forall h = 1, ..., numData(el_k.categoryName) | el_k.data[h] != dato )
     * @effects post(this.el_i.data) = pre(this.el_i.dataSet) \ dato
     * @return this.el_i.data[j]
     */
    public E remove(String passw, E dato) {
        for(Category<E> category : this.elements.values()) {
            category.removeDataIfExists(dato);
            return dato;
        }

        throw new InvalidDataException();
    }

    // Crea la lista dei dati in bacheca su una determinata categoria
    // se vengono rispettati i controlli di identitàù
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param passw t.c. password = this.passw
     * @throws InvalidPasswordException if passw != this.password
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @return { data[1], ..., data[numData(el_i.categoryName)] }
     */
    public List<E> getDataCategory(String passw, String Category) {
        return null;
    }
    
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca ordinati rispetto al numero di like.
    /**
     * 
     * @param passw t.c. password = this.passw
     * @modifies this.elems
     * @throws InvalidPasswordException if passw != this.password
     * @return iteratore di data[iter_1], ...., data[iter_n], lista ordinata con
     *          n = numData(el_1.categoryName) + ... + numData(el_numCategories().categoryName) &&
     *          forall i = 1, ..., n | ( exist j = 1, ...., numCategories() | ( exist k = 1, ..., numData(el_j.categoryName) | el_j.data[k] = data[iter_i] ) ) &&
     *          (forall i,j = 1, ...., n | i < j => data[iter_i].likes < data[iter_j].likes)
     */
    public Iterator<E> getIterator(String passw) {
        return null;
    }

    // Aggiunge un like a un dato
    /**
     * 
     * @param friend t.c. exist i = 1, ..., numCategories | ( exist j = 1, ..., numFriends(el_i.categoryName) | friend = el_i.friend[j]) 
     * @param dato t.c. dato != null && (exist k = 1, ...., numData(el_i.categoryName) | el_i.data[k] = dato)
     * @throws InvalidFriendException if forall h = 1, ...., numCategories() | ( forall l = 1, ...., numFriends(el_h.categoryName) | el_h.friend[l] != friend)
     * @throws InvalidDataException if forall l = 1, ..., numData(el_i.categoryName) | el_k.data[l] != dato
     * @modifies this.el_i.data[k].likes
     * @effects post(this.el_i.data[k].likes) = pre(this.el_i.data[k].likes) + 1
     */
    public void insertLike(String friend, E dato) {}

    // Legge un dato condiviso
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca condivisi.
    /**
     * 
     * @param friend t.c. exist i = 1, ..., numCategories | ( exist j = 1, ..., numFriends(el_i.categoryName) | friend = el_i.friend[j]) 
     * @throws InvalidFriendException if forall h = 1, ...., numCategories() | ( forall l = 1, ...., numFriends(el_h.categoryName) | el_h.friend[l] != friend)
     * @return iteratore di data[iter_1], ...., data[iter_n], lista ordinata con
     *          n = numData(el_cat_1.categoryName) + ... + numData(el_cat_m) &&
     *          m = #{ i | 0 < i < numCategories() && exist j = 1, ..., numFriends(el_i.categoryName t.c. el_i.friend[j] = friend) }
     *          forall i = 1, ..., n | ( exist j = 1, ...., numCategories() | ( exist k = 1, ..., numData(el_j.categoryName) | el_j.data[k] = data[iter_i] ) )
     */
    public Iterator<E> getFriendIterator(String friend) {
        return null;
    }

    /**
     * 
     * @return #elems
     */
    public int numCategories() {
        return this.dim;
    }

    /**
     * 
     * @param category t.c. category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = category)
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != category)
     * @return #el_i.friends
     */
    public int numFriends(String category) {
        return this.elements.get(category).numFriends();
    }

    /**
     * 
     * @param category t.c. category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = category)
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != category)
     * @return #el_i.dataSet
     */
    public int numData(String category) {
        return this.elements.get(category).numData();
    }

}