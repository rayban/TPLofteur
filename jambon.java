import java.awt.Color;


public class jambon extends Nourriture {

	
	//Attributs
	
	//Methodes
	public jambon(int x, int y,int valeurEnergetique, int quantite) {
		super(x, y, valeurEnergetique, quantite);
		this.color = new Color(200, 100, 100);
	}
	
}