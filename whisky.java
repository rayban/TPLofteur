import java.awt.Color;


public class whisky extends Boisson {

	//Attributs
	
	//Methodes
	public whisky(int x, int y, int valeurEnergetique, int quantite) {
		super(x, y, valeurEnergetique, quantite);
		this.color = new Color(30, 30, 30);
	}
	
}
