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


(* Dizionario dei risulati di una gara sportiva. Le chiavi sono i nomi degli alteti e i valori il loro tempo corrispondente *)

(* let e = 
	Let("Gara", 
		Dictionary
			(Assoc("Marco", CstFloat(20.6), 
				Assoc("Mario", CstFloat(19.2), 
					Assoc("Luca", CstFloat(23.4), 
						Assoc("Giovanni", CstFloat(21.9), Empty))))),
    InsertDict("Claudio", CstFloat(19.0), Den("Gara")));;

let e2 = 
    Let("Gara", 
		Dictionary
			(Assoc("Marco", CstFloat(20.6), 
				Assoc("Mario", CstFloat(19.2), 
					Assoc("Luca", CstFloat(23.4), 
						Assoc("Giovanni", CstFloat(21.9), Empty))))),
    DeleteDict("Mario", Den("Gara")));;

let e3 = 
    Let("Gara", 
		Dictionary
			(Assoc("Marco", CstFloat(20.6), 
				Assoc("Mario", CstFloat(19.2), 
					Assoc("Luca", CstFloat(23.4), 
						Assoc("Giovanni", CstFloat(21.9), Empty))))),
    HashKey("Claudio", Den("Gara")));;

let e3 = 
    Let("Gara", 
		Dictionary
			(Assoc("Marco", CstFloat(20.6), 
				Assoc("Mario", CstFloat(19.2), 
					Assoc("Luca", CstFloat(23.4), 
						Assoc("Giovanni", CstFloat(21.9), Empty))))),
    Iterate(Fun("x", Sum(Den("x"), CstInt(1))), Den("Gara")));;

let e4 = 
    Let("Gara", 
		Dictionary
			(Assoc("Marco", CstFloat(20.6), 
				Assoc("Mario", CstFloat(19.2), 
					Assoc("Luca", CstFloat(23.4), 
						Assoc("Giovanni", CstFloat(21.9), Empty))))),
		Fold
			(FunArgs(
				Ide("x", Ide("y", Empty)), 
				Sum(Sum(Den("x"), Den("y")), CstInt(1))), 
			Den("Magazzino")));;

let e5 = 
    Let("Gara", 
		Dictionary
			(Assoc("Marco", CstFloat(20.6), 
				Assoc("Mario", CstFloat(19.2), 
					Assoc("Luca", CstFloat(23.4), 
						Assoc("Giovanni", CstFloat(21.9), Empty))))),
            Filter(
                Ide("mele", Ide("pere", Empty)),
                Den("Magazzino")));;

eval e (emptyenv(String("Unbound value")));;
eval e2 (emptyenv(String("Unbound value")));;
eval e3 (emptyenv(String("Unbound value")));;
eval e4 (emptyenv(String("Unbound value")));;
eval e5 (emptyenv(String("Unbound value")));; *)