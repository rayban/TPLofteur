import java.awt.Color;
import java.awt.Graphics;


public abstract class Nourriture extends Element implements ObjetDessinable{

	
	//Attributs
	protected int valeurEnergetique;
	protected int quantite;
	protected Color color;
	
	//Methodes	
	
	public Nourriture(int x, int y, int valeurEnergetique, int quantite) {
		Case = new Case(x, y);
		this.valeurEnergetique = valeurEnergetique;
		this.quantite = quantite;
	}
	
	public Nourriture(int valeurEnergetique, int quantite) {
		this.valeurEnergetique = valeurEnergetique;
		this.quantite = quantite;
	}
	
	public void dessinerObjet(Graphics g) {
		g.setColor(color);
		g.drawRect(Case.x, Case.y, 1, 1);
	}
}
