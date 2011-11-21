import java.awt.Color;
import java.util.Vector;


public class Cannibale extends Vorace {

	//Attributs
	
	//Methodes
	Cannibale(Loft loft,int x, int y) {
		super(loft,x,y);
		this.color = new Color(255, 0, 0);
		alimentation = new Vector<String>();
		alimentation.add("carotte");
		alimentation.add("jambon");
		alimentation.add("coca");
		alimentation.add("Vorace");
		alimentation.add("Erratique");
		alimentation.add("Lapin");
		alimentation.add("Cannibale");
	}
	
	public void donneNaissance() {
		loft.add(new Cannibale(loft, (int)(Math.random()*(Saison1.tailleLoft-1)),(int)(Math.random()*(Saison1.tailleLoft-1))));
	}
}
