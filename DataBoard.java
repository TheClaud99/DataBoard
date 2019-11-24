import java.util.Iterator;
import java.util.List;

public interface DataBoard<E extends Data<T>, T> {
    /**
     * 
     * Overview:    contenitore di oggetti generici che estendono il tipo di dato Data. Ogni
     *              dato presente nella bacheca ha associato la categoria del dato.
     * 
     * TE:          <password, elems = { el_0, ..., el_i, ..., el_numCategories() }> con
     *                  forall i = 1, ..., numCategories | el_i = <categoryName, dataSet, friends> con
     *                      categoryName != null    
     *                      dataSet = getDataCategory(password, categoryName)
     *                      friends = { friend_0, ..., friend_k, ..., firend_numFriends(el_i.categoryName()) } con friend_k != null forall k = 0, ..., numFriends(el_i.categoryName())
     *              password != null
     */

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
    public void createCategory(String Category, String passw) throws NullPointerException;
    
    // Rimuove l’identità una categoria di dati
    /**
     * 
     * @param Category t.c. Category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = Category)
     * @param passw t.c. password = this.passw
     * @modifies this.elems
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @throws InvalidPasswordException if passw != this.password
     * @effects post(this.elems) = pre(this.elems) \ el_i
     */
    public void removeCategory(String Category, String passw);

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
    public void addFriend(String Category, String passw, String friend);

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
     * @throws InvalidPasswordException if passw != this.password
     * @throws DuplicateDataException if exist h = 1, ...., numData(categoria) | data = el_i.data[h]
     * @effects post(this.el_i.data) = pre(this.el_i.dataSet) U dato
     */
    public boolean put(String passw, E dato, String categoria);
    
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
    public E get(String passw, E dato);

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
    public E remove(String passw, E dato);

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
    public List<E> getDataCategory(String passw, String Category);
    
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
    public Iterator<E> getIterator(String passw);

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
    public void insertLike(String friend, E dato);

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
    public Iterator<E> getFriendIterator(String friend);

    /**
     * 
     * @return #elems
     */
    public int numCategories();

    /**
     * 
     * @param category t.c. category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = category)
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != category)
     * @return #el_i.friends
     */
    public int numFriends(String category);

    /**
     * 
     * @param category t.c. category != null && (exist i = 1, ...., numCategories() | el_i.categoryName = category)
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != category)
     * @return #el_i.dataSet
     */
    public int numData(String Category);
}