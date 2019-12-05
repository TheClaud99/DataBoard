import java.util.*;

public class Board<E extends Data<?>> implements DataBoard<E> {

    /**
     * 
     * Overview:    contenitore di oggetti generici che estendono il tipo di dato Data. Ogni
     *              dato presente nella bacheca ha associato la categoria del dato.
     * 
     *              categories = elements.values()
     * 
     * AF:          <password, { el_0, ..., el_dim }, dim> con
     *                  forall i = 0, ..., dim | 
     *                      el_i = <categories.get(i).getCategoryName(), categories.get(i).getData, categories.get(i).getFriends()>
     *                      categories.get(i).categoryName != null    
     * 
     * IR:          password != null && dim = categories.size() &&
     *              ( forall i = 1, ..., dim  |
     *                      categories.get(i).categoryName != null 
     *                      && ( forall j = 0, ..., dim | categories.get(i).getCategoryName() != categories.get(j).categoryName() ) )
     *                      && ( forall k, l = 0, ..., dim | k != l => categories.get(i).getFriend(k) != categories.get(i).getFriend(l) )
     *                      && ( forall m, n = 0, ..., dim | m != n => categories.get(i).getData(m) != categories.get(i).getData(n) ) )
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
     * @param Category t.c. !this.elements.containsKey(Category)
     * @param passw t.c. password = passw
     * @modifies this.elements
     * @throws NullPointerException if Category = null
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws ExistingCategoryException if this.elements.containsKey(Category)
     * @effects post(this.elements) = pre(this.el_i) U <Category, null, null>
     */
    public void createCategory(String Category, String passw) throws NullPointerException, InvalidPasswordException, ExistingCategoryException {
        if(Category == null) throw new NullPointerException();
        if(!this.password.equals(passw)) throw new InvalidPasswordException();
        if(this.elements.containsKey(Category)) throw new ExistingCategoryException();

        elements.put(Category, new Category<E>(Category));
        if(this.elements.containsKey(Category)) dim++;
    }
    
    // Rimuove l’identità una categoria di dati
    /**
     * 
     * @param Category t.c. this.elements.containsKey(Category)
     * @param passw t.c. this.password.equals(passw)
     * @modifies this.elements
     * @throws InvalidCategoryExcetpion !this.elements.containsKey(Category)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws NullPointerException if Category = null
     * @effects post(this.elements) = pre(this.elements) \ el_i
     */
    public void removeCategory(String Category, String passw) throws InvalidCategoryExcetpion, InvalidPasswordException, NullPointerException {
        if(Category == null) throw new NullPointerException();
        if(!this.elements.containsKey(Category)) throw new InvalidCategoryExcetpion();
        if(!this.password.equals(passw)) throw new InvalidPasswordException();

        elements.remove(Category);
    }

    // Aggiunge un amico ad una categoria di dati
    /**
     * 
     * @param Category t.c. this.elements.containsKey(Category)
     * @param passw t.c. password = this.passw
     * @param friend t.c. !this.elements.get(Category).contains(friend)
     * @modifies this.el_i.friends
     * @throws InvalidCategoryExcetpion if !this.elements.containsKey(Category)
     * @throws NullPointerException if Category = null
     * @throws ExistingFriendException if this.elements.get(Category).contains(friend)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @effects post(this.elements.get(Category).getFriends()) = pre(this.elements.get(Category).getFriends()) U friend
     */
    public void addFriend(String Category, String passw, String friend) throws InvalidCategoryExcetpion, ExistingFriendException, InvalidPasswordException, NullPointerException {
        if(Category == null) throw new NullPointerException();
        if(!this.elements.containsKey(Category)) throw new InvalidCategoryExcetpion();
        if(!this.password.equals(passw)) throw new InvalidPasswordException();

        this.elements.get(Category).addFriend(friend);
    }

    // rimuove un amico ad una categoria di dati
    /**
     * 
     * @param Category t.c. this.elements.containsKey(Category)
     * @param friend t.c. this.elements.get(Category).contains(friend)
     * @param passw t.c. password = this.passw
     * @modifies this.el_i.friends
     * @throws InvalidCategoryExcetpion if (forall j = 1, ..., numCategories() | el_j.categoryName != Category)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws InvalidFriendException if !this.elements.get(Category).contains(friend)
     * @throws NullPointerException if friend = null or Category = null
     * @effects post(this.el_i.friends) = pre(this.el_i.friends) \ friend
     */
    public void removeFriend(String Category, String passw, String friend) throws InvalidCategoryExcetpion, InvalidPasswordException, InvalidFriendException, NullPointerException  {
        if(!this.elements.containsKey(Category)) throw new InvalidCategoryExcetpion();
        if(!this.password.equals(passw)) throw new InvalidPasswordException();
        if(friend == null || Category == null) throw new NullPointerException();
        if(!this.elements.get(Category).removeFriend(friend)) throw new InvalidFriendException();
    }
    
    // Inserisce un dato in bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param Category t.c. this.elements.containsKey(categoria)
     * @param dato t.c. dato != null
     * @param passw t.c. password = this.passw
     * @modifies this.el_i.data
     * @throws InvalidCategoryExcetpion if !this.elements.containsKey(categoria)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws DuplicateDataException if this.elements.get(categoria).contains(dato)
     * @effects post(this.el_i.data) = pre(this.el_i.dataSet) U dato
     */
    public boolean put(String passw, E dato, String categoria) throws DuplicateDataException, InvalidCategoryExcetpion, DuplicateDataException, InvalidPasswordException {
        if(!this.elements.containsKey(categoria)) throw new InvalidCategoryExcetpion();
        if(!this.password.equals(passw)) throw new InvalidPasswordException();
        
        this.elements.get(categoria).addData(dato);

        return this.elements.get(categoria).contains(dato);
    }
    
    // Ottiene una copia del del dato in bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param passw this.password = passw
     * @param dato t.c. exist category = categories.get(1), ..., categories.get(dim) | this.elements.get(category).contains(dato)
     * @throws InvalidDataException if forall category = category.get(1), ..., category.get(dim) | !this.elements.get(category).contains(dato)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @return this.el_i.data[j]
     */
    public E get(String passw, E dato) throws InvalidDataException, InvalidPasswordException {
        if(!this.password.equals(passw)) throw new InvalidPasswordException();
        
        Collection<Category<E>> categories = this.elements.values();

        for(Category<E> category: categories) {
            if(category.contains(dato))
                return (E) dato.cloneData();
        }

        throw new InvalidDataException();
    }

    // Rimuove il dato dalla bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param dato t.c. exist category = category.get(1), ..., category.get(dim) | this.elements.get(category).contains(dato)
     * @param passw t.c. password = this.password
     * @modifies this.el_i.data
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws InvalidDataException if forall category = category.get(1), ..., category.get(dim) | !this.elements.get(category).contains(dato)
     * @effects post(this.el_i.data) = pre(this.el_i.dataSet) \ dato forall i = 1, ...., numCategories | ( exist j = 1, ..., numData(el_i.categoryName) | el_i.data[j] = dato )
     * @return this.el_i.data[j]
     */
    public E remove(String passw, E dato) throws InvalidDataException {
        boolean found = false;

        for(Category<E> category : this.elements.values()) {
            if(category.removeDataIfExists(dato)) found = true;
        }

        if(found) return dato;

        throw new InvalidDataException();
    }

    // Crea la lista dei dati in bacheca su una determinata categoria
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param Category this.elements.containsKey(Category)
     * @param passw t.c. password = this.passw
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws InvalidCategoryExcetpion !this.elements.containsKey(Category)
     * @throws NullPointerException if Category = null
     * @return { data[1], ..., data[numData(el_i.categoryName)] }
     */
    public List<E> getDataCategory(String passw, String Category) throws InvalidCategoryExcetpion, InvalidPasswordException {
        if(!this.password.equals(passw)) throw new InvalidPasswordException();
        if(Category == null) throw new NullPointerException();
        if(!this.elements.containsKey(Category)) throw new InvalidCategoryExcetpion();
        
        return this.elements.get(Category).getData();
    }
    
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca ordinati rispetto al numero di like.
    /**
     * 
     * @param passw t.c. password = this.passw
     * @modifies this.elems
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @return iteratore di data[iter_1], ...., data[iter_n], lista ordinata con
     *          n = numData(el_1.categoryName) + ... + numData(el_numCategories().categoryName) &&
     *          forall i = 1, ..., n | ( exist j = 1, ...., numCategories() | ( exist k = 1, ..., numData(el_j.categoryName) | el_j.data[k] = data[iter_i] ) ) &&
     *          (forall i,j = 1, ...., n | i < j => data[iter_i].likes < data[iter_j].likes)
     */
    public Iterator<E> getIterator(String passw) throws InvalidPasswordException {
        if(!this.password.equals(passw)) throw new InvalidPasswordException();

        SortedSet<E> bacheca = new TreeSet<E>(new SortByLikes());

        for(Category<E> category : this.elements.values())
            bacheca.addAll(category.getData());
                
        return Collections.unmodifiableSortedSet(bacheca).iterator();
    }

    // Aggiunge un like a un dato
    /**
     * 
     * @param friend t.c. exist category = categories.get(1), ..., categories.get(dim) | this.elements.get(category).contains(friend)
     * @param dato t.c. exist category = categories.get(1), ..., categories.get(dim) | (this.elements.get(category).contains(friend) && this.elements.get(category).contains(dato))
     * @throws InvalidFriendException if forall category = categories.get(1), ..., categories.get(dim) | !this.elements.get(category).contains(friend)
     * @throws InvalidDataException if forall category = categories.get(1), ..., categories.get(dim) | !(this.elements.get(category).contains(friend) && this.elements.get(category).contains(dato))
     * @modifies this.el_i.data[k].likes
     * @effects post(this.el_i.data[k].likes) = pre(this.el_i.data[k].likes) + 1
     */
    public void insertLike(String friend, E dato) throws InvalidFriendException, InvalidDataException, DuplicateLikeException {

        if(friend == null || dato == null) throw new NullPointerException();

        boolean foundFriend = false, foundPost = false;

        for(Category<E> category : this.elements.values()) {
            if(category.contains(friend)) {
                foundFriend = true;
                if(category.contains(dato)){
                    foundPost = true;
                    if(category.contains(dato) && category.contains(friend)) {
                        dato.insertLike(friend);
                        return;
                    }
                }
            }
            
        }

        if(!foundFriend) throw new InvalidFriendException();
        if(!foundPost) throw new InvalidDataException();
    }

    // Legge un dato condiviso
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca condivisi.
    /**
     * 
     * @param friend t.c. exist category = categories.get(1), ..., categories.get(dim) | this.elements.get(category).contains(friend) 
     * @throws InvalidFriendException if forall category = categories.get(1), ..., categories.get(dim) | !this.elements.get(category).contains(friend)
     * @throws NullPointerException if friend = null
     * @return iteratore di data[iter_1], ...., data[iter_n], lista ordinata con
     *          n = numData(el_cat_1.categoryName) + ... + numData(el_cat_m) &&
     *          m = #{ i | 0 < i < numCategories() && exist j = 1, ..., numFriends(el_i.categoryName t.c. el_i.friend[j] = friend) }
     *          forall i = 1, ..., n | ( exist j = 1, ...., numCategories() | ( exist k = 1, ..., numData(el_j.categoryName) | el_j.data[k] = data[iter_i] ) )
     */
    public Iterator<E> getFriendIterator(String friend) throws InvalidFriendException {
        if(friend == null) throw new NullPointerException();
        
        Set<E> bacheca = new HashSet<E>();

        for(Category<E> category : this.elements.values())
            if(category.getFriends().contains(friend)) bacheca.addAll(category.getData());

        if(bacheca.isEmpty()) throw new InvalidFriendException();

        return Collections.unmodifiableSet(bacheca).iterator();
    }

    /**
     * 
     * @return dim
     */
    public int numCategories() {
        return this.dim;
    }

    /**
     * 
     * @param category t.c. this.elements.containsKey(category)
     * @throws InvalidCategoryExcetpion if !this.elements.containsKey(category)
     * @return this.elements.get(category).numFriends()
     */
    public int numFriends(String category) throws InvalidCategoryExcetpion {
        if(!this.elements.containsKey(category)) throw new InvalidCategoryExcetpion();
        return this.elements.get(category).numFriends();
    }

    /**
     * 
     * @param category t.c. this.elements.containsKey(category)
     * @throws InvalidCategoryExcetpion if !this.elements.containsKey(category)
     * @return this.elements.get(category).numData()
     */
    public int numData(String category) throws InvalidCategoryExcetpion {
        if(!this.elements.containsKey(category)) throw new InvalidCategoryExcetpion();
        return this.elements.get(category).numData();
    }

}