public interface Data<E> {
    
    /**
     * Overview:    dato di un qualsiasi tipo sul quale può essere applicato il metodo display
     * 
     * TE:          <element, category, likes>
     */

    public void updateData(E el);

    public E getData();

    public void Display();
}
