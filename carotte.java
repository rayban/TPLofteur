import java.awt.Color;


public class carotte extends Nourriture {

	
	//Attributs
	
	//Methodes
	public carotte(int x, int y, int valeurEnergetique, int quantite) {
		super(x, y, valeurEnergetique, quantite);
		this.color = new Color(255, 69, 0);
	}
	
}