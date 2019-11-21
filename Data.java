public interface Data<E> {
    
    /**
     * Overview:    dato di un qualsiasi tipo sul quale può essere applicato il metodo display
     * 
     * TE:          <element, likes>, element != null and likes >= 0
     */

    // Cambia il valore di el
    /**
     * 
     * @param el, el != null
     * @throws NullPointerException
     * @effects post(this.element) = el
     * 
     */
    public void updateData(E el);

    // Prende il valore di element
    public E getData();

    // Restituisce il numero di like associati al datoA
    public int getLikes();

    // Stampa il valore di element
    public void Display();
}
