import java.util.*;

public class Board2<E extends Data<?>> implements DataBoard<E> {

    /**
     * 
     * Overview:    contenitore di oggetti generici che estendono il tipo di dato Data. Ogni
     *              dato presente nella bacheca ha associato la categoria del dato.
     * 
     * 
     *              categories = dataset.keySet() = friends.keySet()
     * 
     * AF:          <password, { el_0, ..., el_dim }, dim> con
     *                  forall i = 0, ..., dim | 
     *                      el_i = <categories.get(i), dataSet.get(categories.get(i)), friends.get(categories.get(i))>
     *                      categories.get(i) != null
     * 
     * IR:          password != null && dim = categories.size() && 
     *              ( forall i = 1, ..., dim |
     *                      categories.get(i) != null 
     *                      && ( forall j = 0, ..., dim | categories.get(i) != categories.get(j) ) )
     *                      && ( forall k, l = 0, ..., dim | k != l => friends.get(categories.get(i)).get(k) != friends.get(categories.get(i)).get(l) )
     *                      && ( forall m, n = 0, ..., dim | m != n => dataSet.get(categories.get(i)).get(m) != dataSet.get(categories.get(i)).get(n) ) )
     *              && dataset.keySet() = friends.keySet()
     *                      
     */

    private int dim;
    private String password;

    private HashMap<String, List<E>> dataSet;
    private HashMap<String, List<String>> friends;


    public Board2(String password)
    {
        this.password = password;
        this.dim = 0;
        this.dataSet = new HashMap<String, List<E>>();
        this.friends = new HashMap<String, List<String>>();
    }

     /**
     * Crea l’identità una categoria di dati
     * 
     * @param Category t.c. !this.dataSet.containsKey(Category)
     * @param passw t.c. password = passw
     * @modifies this.elems
     * @throws NullPointerException if Category = null
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws ExistingCategoryException if this.dataSet.containsKey(Category)
     * @effects post(this.elems) = pre(this.el_i) U <Category, null, null>
     */
    public void createCategory(String Category, String passw) throws NullPointerException, InvalidPasswordException, ExistingCategoryException {
        if(Category == null) throw new NullPointerException();
        if(!this.password.equals(passw)) throw new InvalidPasswordException();
        if(this.dataSet.containsKey(Category)) throw new ExistingCategoryException();

        dataSet.put(Category, new ArrayList<E>());
        friends.put(Category, new ArrayList<String>());
        this.dim++;
    }
    
    // Rimuove l’identità una categoria di dati
    /**
     * 
     * @param Category t.c. this.dataSet.containsKey(Category)
     * @param passw t.c. password = this.passw
     * @modifies this.elems
     * @throws InvalidCategoryExcetpion if !this.dataSet.containsKey(Category)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws NullPointerException if Category = null
     * @effects post(this.elems) = pre(this.elems) \ el_i
     */
    public void removeCategory(String Category, String passw) throws InvalidCategoryExcetpion, InvalidPasswordException {
        if(Category == null) throw new NullPointerException();
        if(!this.dataSet.containsKey(Category)) throw new InvalidCategoryExcetpion();
        if(!this.password.equals(passw)) throw new InvalidPasswordException();

        this.dataSet.remove(Category);
        this.friends.remove(Category);
    }

    // Aggiunge un amico ad una categoria di dati
    /**
     * 
     * @param Category t.c. this.friends.containsKey(Category)
     * @param passw t.c. password = this.passw
     * @param friend t.c. !this.friends.get(Category).contains(friend)
     * @modifies this.el_i.friends
     * @throws InvalidCategoryExcetpion if !this.friends.containsKey(Category)
     * @throws NullPointerException if Category = null
     * @throws ExistingFriendException if this.friends.get(Category).contains(friend)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @effects post(this.el_i.friends) = pre(this.el_i.friends) U friend
     */
    public void addFriend(String Category, String passw, String friend) throws InvalidCategoryExcetpion, ExistingFriendException, InvalidPasswordException {
        if(Category == null) throw new NullPointerException();
        if(!this.friends.containsKey(Category)) throw new InvalidCategoryExcetpion();
        if(this.friends.get(Category).contains(friend)) throw new ExistingFriendException();
        if(!this.password.equals(passw)) throw new InvalidPasswordException();

        this.friends.get(Category).add(friend);
    }

    // rimuove un amico ad una categoria di dati
    /**
     * 
     * @param Category t.c. this.friends.containsKey(Category)
     * @param friend t.c. this.friends.get(Category).contains(friend)
     * @param passw t.c. password = this.passw
     * @modifies this.el_i.friends
     * @throws InvalidCategoryExcetpion if !this.friends.containsKey(Category)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws InvalidFriendException if !this.friends.get(Category).contains(friend)
     * @throws NullPointerException if friend = null or Category = null
     * @effects post(this.el_i.friends) = pre(this.el_i.friends) \ friend
     */
    public void removeFriend(String Category, String passw, String friend) throws InvalidCategoryExcetpion, InvalidFriendException, InvalidPasswordException {
        if(!this.friends.containsKey(Category)) throw new InvalidCategoryExcetpion();
		if(!this.password.equals(passw)) throw new InvalidPasswordException();
        if(friend == null || Category == null) throw new NullPointerException();
        if(!this.friends.get(Category).contains(friend)) throw new InvalidFriendException();

        this.friends.get(Category).remove(friend);
    }
    
    // Inserisce un dato in bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param Category t.c. this.dataSet.containsKey(categoria)
     * @param dato t.c. !this.dataSet.get(categoria).contains(dato)
     * @param passw t.c. password = this.passw
     * @modifies this.el_i.data
     * @throws InvalidCategoryExcetpion if !this.dataSet.containsKey(categoria)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws DuplicateDataException if this.dataSet.get(categoria).contains(dato)
     * @throws NullPointerException if categoria = null or dato = null
     * @effects post(this.el_i.data) = pre(this.el_i.dataSet) U dato
     */
    public boolean put(String passw, E dato, String categoria) throws DuplicateDataException, InvalidCategoryExcetpion, InvalidPasswordException {
        if(!this.dataSet.containsKey(categoria)) throw new InvalidCategoryExcetpion();
        if(!this.password.equals(passw)) throw new InvalidPasswordException();
        if(this.dataSet.get(categoria).contains(dato)) throw new DuplicateDataException();
        if(dato == null || categoria == null) throw new NullPointerException();

        this.dataSet.get(categoria).add(dato);
        return this.dataSet.get(categoria).contains(dato);
    }
    
    // Ottiene una copia del del dato in bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param passw this.password = passw
     * @param dato t.c. exist category = categories.get(1), ..., categories.get(dim) | this.dataSet.get(category).contains(dato)
     * @throws InvalidDataException if forall category = categories.get(1), ..., categories.get(dim) | !this.dataSet.get(category).contains(dato)
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @return this.el_i.data[j]
     */
    public E get(String passw, E dato) throws InvalidDataException, InvalidPasswordException {
        if(!this.password.equals(passw)) throw new InvalidPasswordException();

        Collection<String> categories = this.dataSet.keySet();

        for(String category : categories) {
            if(this.dataSet.get(category).contains(dato))
                return (E) dato.cloneData();
        }

        throw new InvalidDataException();
    }

    // Rimuove il dato dalla bacheca
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param dato t.c. exist category = categories.get(1), ..., categories.get(dim) | this.dataSet.get(category).contains(dato)
     * @param passw t.c. password = this.password
     * @modifies this.el_i.data
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws InvalidDataException if forall category = categories.get(1), ..., categories.get(dim) | !this.dataSet.get(category).contains(dato)
     * @effects post(this.el_i.data) = pre(this.el_i.dataSet) \ dato forall i = 1, ...., numCategories | ( exist j = 1, ..., numData(el_i.categoryName) | el_i.data[j] = dato )
     * @return this.el_i.data[j]
     */
    public E remove(String passw, E dato) throws InvalidDataException, InvalidPasswordException {
        if(!this.password.equals(passw)) throw new InvalidPasswordException();

        boolean found = false;

        for(String category : this.dataSet.keySet()) {
            this.dataSet.get(category).remove(dato);
            found = true;
        }

        if(found) return dato;

        throw new InvalidDataException();
    }

    // Crea la lista dei dati in bacheca su una determinata categoria
    // se vengono rispettati i controlli di identità
    /**
     * 
     * @param Category t.c. this.dataSet.containsKey(Category)
     * @param passw t.c. password = this.passw
     * @throws InvalidPasswordException if !this.password.equals(passw)
     * @throws InvalidCategoryExcetpion if !this.dataSet.containsKey(Category)
     * @throws NullPointerException if Category = null
     * @return { data[1], ..., data[numData(el_i.categoryName)] }
     */
    public List<E> getDataCategory(String passw, String Category) throws InvalidCategoryExcetpion, InvalidPasswordException {
        if(!this.password.equals(passw)) throw new InvalidPasswordException();
        if(Category == null) throw new NullPointerException();
        if(!this.dataSet.containsKey(Category)) throw new InvalidCategoryExcetpion();
        
        return this.dataSet.get(Category);
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
    public Iterator<E> getIterator(String passw) {

        SortedSet<E> bacheca = new TreeSet<E>(new SortByLikes());

        for(String category : this.dataSet.keySet())
            bacheca.addAll(this.dataSet.get(category));
                
        return Collections.unmodifiableSortedSet(bacheca).iterator();
    }

    // Aggiunge un like a un dato
    /**
     * 
     * @param friend t.c. exist category = categories.get(1), ..., categories.get(dim) | this.friends.get(category).contains(friend)
     * @param dato t.c. exist category = categories.get(1), ..., categories.get(dim) | (this.friends.get(category).contains(friend) && this.friends.get(category).contains(dato))
     * @throws InvalidFriendException if forall category = categories.get(1), ..., categories.get(dim) | !this.friends.get(category).contains(friend)
     * @throws InvalidDataException if forall category = categories.get(1), ..., categories.get(dim) | !(this.friends.get(category).contains(friend) && this.friends.get(category).contains(dato))
     * @modifies this.el_i.data[k].likes
     * @effects post(this.el_i.data[k].likes) = pre(this.el_i.data[k].likes) + 1
     */
    public void insertLike(String friend, E dato) throws InvalidFriendException, InvalidDataException {

        if(friend == null || dato == null) throw new NullPointerException();

        boolean foundFriend = false, foundPost = false;

        for(String category : this.dataSet.keySet()) {
            if(this.friends.get(category).contains(friend)) {
                foundFriend = true;
                if(this.dataSet.get(category).contains(dato)) {
                    foundPost = true;
                    try {
                        dato.insertLike(friend);
                    } catch(DuplicateLikeException e) {
                        System.out.println(friend + " ha gia' messo like al post");
                    }
                    return;
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
     * @param friend t.c. exist category = categories.get(1), ..., categories.get(dim) | this.friends.get(category).contains(friend)
     * @throws InvalidFriendException if forall category = categories.get(1), ..., categories.get(dim) | !this.friends.get(category).contains(friend)
     * @throws NullPointerException if friend = null
     * @return iteratore di data[iter_1], ...., data[iter_n], lista ordinata con
     *          n = numData(el_cat_1.categoryName) + ... + numData(el_cat_m) &&
     *          m = #{ i | 0 < i < numCategories() && exist j = 1, ..., numFriends(el_i.categoryName t.c. el_i.friend[j] = friend) }
     *          forall i = 1, ..., n | ( exist j = 1, ...., numCategories() | ( exist k = 1, ..., numData(el_j.categoryName) | el_j.data[k] = data[iter_i] ) )
     */
    public Iterator<E> getFriendIterator(String friend) throws InvalidFriendException {
        if(friend == null) throw new NullPointerException();
        
        Set<E> bacheca = new HashSet<E>();

        for(String category : this.dataSet.keySet())
            if(friends.get(category).contains(friend)) bacheca.addAll(this.dataSet.get(category));

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
     * @param category t.c. this.dataSet.containsKey(Category)
     * @throws InvalidCategoryExcetpion if !this.dataSet.containsKey(Category)
     * @return this.friends.get(category).size()
     */
    public int numFriends(String category) throws InvalidCategoryExcetpion {
        return this.friends.get(category).size();
    }

    /**
     * 
     * @param category t.c. this.dataSet.containsKey(Category)
     * @throws InvalidCategoryExcetpion if !this.dataSet.containsKey(Category)
     * @return this.dataSet.get(category).size()
     */
    public int numData(String category) throws InvalidCategoryExcetpion {
        return this.dataSet.get(category).size();
    }

}