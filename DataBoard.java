import java.util.Iterator;
import java.util.List;

public interface DataBoard<E extends Data> {
    /**
     * 
     * Overview:    contenitore di oggetti generici che estendono il tipo di dato Data. Ogni
     *              dato presente nella bacheca ha associato la categoria del dato.
     * 
     * TE:          
     */

    // Crea l’identità una categoria di dati
    public void createCategory(String Category, String passw);
    // Rimuove l’identità una categoria di dati
    public void removeCategory(String Category, String passw);
    // Aggiunge un amico ad una categoria di dati
    public void addFriend(String Category, String passw, String friend);
    // rimuove un amico ad una categoria di dati
    public void removeFriend(String Category, String passw, String friend);
    // Inserisce un dato in bacheca
    // se vengono rispettati i controlli di identità
    public boolean put(String passw, E dato, String categoria);
    // Ottiene una copia del del dato in bacheca
    // se vengono rispettati i controlli di identità
    public E get(String passw, E dato);
    // Rimuove il dato dalla bacheca
    // se vengono rispettati i controlli di identità
    public E remove(String passw, E dato);
    // Crea la lista dei dati in bacheca su una determinata categoria
    // se vengono rispettati i controlli di identità
    public List<E> getDataCategory(String passw, String Category);
    
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca ordinati rispetto al numero di like.
    public Iterator<E> getIterator(String passw);
    // Aggiunge un like a un dato
    void insertLike(String friend, E data)
    // Legge un dato condiviso
    // restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca condivisi.
    public Iterator<E> getFriendIterator(String friend);
    // … altre operazione da definire a scelta
}