#use "main.ml";;

let e = 
    Let("Magazzino", 
        Dictionary
            (Assoc("mele", CstInt(430), 
                Assoc("banane", CstInt(312), 
                    Assoc("arance", CstInt(525),
                        Assoc("pere", CstInt(217), Empty))))),
    InsertDict("kiwi", CstInt(300), Den("Magazzino")));;

let e2 = 
    Let("Magazzino",
        Dictionary
            (Assoc("mele", CstInt(430), 
                Assoc("banane", CstInt(312), 
                    Assoc("arance", CstInt(525), 
                        Assoc("pere", CstInt(217), Empty))))),
    DeleteDict("pere", Den("Magazzino")));;

let e3 = 
    Let("Magazzino", 
        Dictionary
            (Assoc("mele", CstInt(430), 
                Assoc("banane", CstInt(312), 
                    Assoc("arance", CstInt(525), 
                        Assoc("pere", CstInt(217), Empty))))),
    HashKey("banane", Den("Magazzino")));;

let e4 = 
    Let("Magazzino", 
        Dictionary(Assoc("mele", CstInt(430), 
            Assoc("banane", CstInt(312), 
                Assoc("arance", CstInt(525), 
                    Assoc("pere", CstInt(217), Empty))))),
    Iterate(Fun("x", Sum(Den("x"), CstInt(1))), Den("Magazzino")));;

let e5 = 
    Let("Magazzino", 
		Dictionary
			(Assoc("mele", CstInt(430), 
				Assoc("banane", CstInt(312), 
					Assoc("arance", CstInt(525), 
						Assoc("pere", CstInt(217), Empty))))),
		Fold
			(FunArgs(
				Ide("x", Ide("y", Empty)), 
				Sum(Sum(Den("x"), Den("y")), CstInt(1))), 
			Den("Magazzino")));;

let e6 = 
    Let("Magazzino", 
            Dictionary
				(Assoc("mele", CstInt(430), 
					Assoc("banane", CstInt(312), 
						Assoc("arance", CstInt(525), 
							Assoc("pere", CstInt(217), Empty))))),
            Filter(
                Ide("mele", Ide("pere", Empty)),
                Den("Magazzino")));;

(* Valutazione delle espressioni nell'ambiente vuoto *)
eval e (emptyenv(String("Unbound value")));;
eval e2 (emptyenv(String("Unbound value")));;
eval e3 (emptyenv(String("Unbound value")));;
eval e4 (emptyenv(String("Unbound value")));;
eval e5 (emptyenv(String("Unbound value")));;
eval e6 (emptyenv(String("Unbound value")));;
