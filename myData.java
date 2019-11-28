class myData<E> implements Data<E>{
    private E el;
    private int likes;
    /**
     * RI:  el != null && likes >= 0
     * 
     * AF: <el, likes>
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
        if(el==null) throw new NullPointerException();
        
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
     * @return this.likes
     */
    public int getLikes()
    {
        return this.likes;
    }

    //imposta i like ad un valore dato in input
    /**
     * 
     * @effects post(this.likes) = pre(this.likes) + 1
     */
    public void insertLike() throws IllegalArgumentException
    {
        this.likes++;
    }

    public Data<E> cloneData() {
        return new myData<E>(this.getData());
    }
}