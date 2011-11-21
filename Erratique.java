import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;


public class Erratique extends NeuNeu {

	//Attributs
	
	//Methodes
	Erratique(Loft loft,int x, int y) {
		super(loft,x,y);
		this.color = new Color(255, 255, 255);
		alimentation = new Vector<String>();
		alimentation.add("carotte");
		alimentation.add("jambon");
		alimentation.add("coca");
	}
	
	public void donneNaissance() {
		loft.add(new Erratique(loft, (int)(Math.random()*(Saison1.tailleLoft-1)),(int)(Math.random()*(Saison1.tailleLoft-1))));
	}
}
