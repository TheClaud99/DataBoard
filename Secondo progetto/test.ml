let e = Let("Magazzino", Dictionary(Assoc("mele", CstInt(430), Assoc("banane", CstInt(312), Empty))),
    InsertDict("kiwi", CstInt(300), Den("Magazzino"))
    );;

let e2 = Let("Magazzino", Dictionary(Assoc("mele", CstInt(430), Assoc("banane", CstInt(312), Empty))),
    DeleteDict("mele", Den("Magazzino"))
    );;

let e3 = Let("Magazzino", Dictionary(Assoc("mele", CstInt(430), Assoc("banane", CstInt(312), Empty))),
    HashKey("banane", Den("Magazzino"))
    );;

let e3 = Let("Magazzino", Dictionary(Assoc("mele", CstInt(430), Assoc("banane", CstInt(312), Empty))),
    Iterate(Fun("x", Sum(Den("x"), CstInt(1))), Den("Magazzino"))
    );;

let e4 = Let("Magazzino", 
    Dictionary(Assoc("mele", CstInt(430), Assoc("banane", CstInt(312), Assoc("arance", CstInt(525), Assoc("pere", CstInt(217), Empty))))),
        Fold(
            FunArgs(
                Ide("x", Ide("y", Empty)), 
                Sum(Sum(Den("x"), Den("y")), CstInt(1))), 
            Den("Magazzino"))
        );;


eval e [];;
eval e2 [];;
eval e3 [];;
eval e4 [];;