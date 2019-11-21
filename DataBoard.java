import java.util.Iterator;
import java.util.List;

public interface DataBoard<E extends Data<?>> {
    /**
     * 
     * Overview:    contenitore di oggetti generici che estendono il tipo di dato Data. Ogni
     *              dato presente nella bacheca ha associato la categoria del dato.
     * 
     * TE:          <password, elems = { el_0, ..., el_i, ..., el_numCategories() }> con
     *                  el_i = <categoryName, dataSet, friends> con
     *                      categoryName != null    
     *                      dataSet = data_0, ..., data_j, ...., data_numData(el_i.categoryName) lista ordinata
     *                      friends = { friend_0, ..., friend_k, ..., firend_numFriends(el_i.categoryName()) }
     *              password != null
     */

    /**
     * Crea l’identità una categoria di dati
     * 
     * @param Category t.c. Category != null && (forall i = 1, ...., numCategories() | el_i.categoryName != Category)
     * @param passw t.c. password = passw
     * @modifies this.elems
     * @throws NullPointerException se Category = null
     * @throws ExistingCategoryException if (exist i = 1, ..., numCategories() | el_i.categoryName = Category)
     * @effects post(this.elems) = pre(this.el_i) U <Category, null, null>
     */
    public void createCategory(String Category, String passw) throws NullPointerException;
    
    // Rimuove l’identità una categoria di dati
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param passw t.c. password = this.passw
     * @modifies this.elems
     * @throws InvalidArgumentExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @effects post(this.elems) = pre(this.elems) \ el_i
     */
    public void removeCategory(String Category, String passw);

    // Aggiunge un amico ad una categoria di dati
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param passw t.c. password = this.passw
     * @modifies this.el_i.friends
     * @throws InvalidArgumentExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @effects post(this.el_i.friends) = pre(this.el_i.friends) U friend
     */
    public void addFriend(String Category, String passw, String friend);

    // rimuove un amico ad una categoria di dati
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param friend t.c. friend != null and (exist j = 1, ...., numFriendns(Category) | el_i.friend[j] = friend)
     * @param passw t.c. password = this.passw
     * @modifies this.el_i.friends
     * @throws InvalidArgumentExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @effects post(this.el_i.friends) = pre(this.el_i.friends) \ friend
     */
    public void removeFriend(String Category, String passw, String friend);
    
    // Inserisce un dato in bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param dato t.c. dato != null
     * @param passw t.c. password = this.passw
     * @modifies this.el_i.data
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @effects post(this.el_i.data) = pre(this.el_i.dataSet) U dato
     */
    public boolean put(String passw, E dato, String categoria);
    
    // Ottiene una copia del del dato in bacheca
    // se vengono rispettati i controlli di identità
    public E get(String passw, E dato);

    // Rimuove il dato dalla bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param dato t.c. dato != null
     * @param passw t.c. password = this.password
     * @modifies this.el_i.data
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @effects post(this.el_i.data) = pre(this.el_i.dataSet) \ dato
     */
    public E remove(String passw, E dato);

    // Crea la lista dei dati in bacheca su una determinata categoria
    // se vengono rispettati i controlli di identità
    public List<E> getDataCategory(String passw, String Category);
    
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca ordinati rispetto al numero di like.
    public Iterator<E> getIterator(String passw);

    // Aggiunge un like a un dato
    void insertLike(String friend, E data);

    // Legge un dato condiviso
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca condivisi.
    public Iterator<E> getFriendIterator(String friend);

    public int numCategories();
    public int numFriends(String Category);
    public int numData(String Category);
}