class myData<E> implements Data<E>{
    private E el;
    private int likes;
    /**
     * RI:  Elem!=NULL AND Likes>=0
     * 
     * AF: <E Elem, int Likes>
     */


    public myData(E element)
    {
        this.el=element;
        this.likes=0;
    }

    // Cambia il valore di el
    /**
     * 
     * @param el != null
     * @throws NullPointerException
     * @effects post(this.El) = el
     * 
     */
    public void updateData(E el)throws NullPointerException
    {
        if(el==null)
        {
            throw new NullPointerException();
        }
        else
            this.el=el;

    }

    // Prende il valore di element
    /**
     * 
     * @return copy(this.El)
     */
    public E getData()
    {
        return this.el;
    }

    // Stampa il valore di element
    /**
     * @effects Stampa il valore <El,likes>
     */
    public void Display()
    {
        System.out.println("Post: ");
        System.out.println(this.el);
        System.out.print("Likes del post:");
        System.out.println(this.likes);
        
    }

    //restituisce il numero di likes
    /**
     * 
     * @return likes
     */
    public int getLikes()
    {
        return this.likes;
    }

    //imposta i like ad un valore dato in input
    /**
     * 
     * @param n t.c. n>0 AND n>likes
     * @throws IllegalArgumentException if n<likes
     * 
     */
    public void setLikes(int n) throws IllegalArgumentException
    {
        if(n<this.likes)
            throw new IllegalArgumentException("Numero dei like minore del precedente");
        this.likes=n;
    }

}