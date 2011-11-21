import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

import javax.lang.model.util.Elements;

public abstract class NeuNeu extends Element implements ObjetDessinable{
	
	//Attributs
	protected boolean reproduit; //indique si un neuneu peut à nouveau se reproduire
	protected int temps_reproduction;//indique le dernier tour lors duquel le NeuNeu s'est reproduit
	protected Loft loft;
	protected Vector<String> alimentation;

	//Methodes
	NeuNeu(Loft loft, int x, int y) {
		this.reproduit = false;
		this.loft = loft;
		this.energie = 1000;
		Case = new Case(x, y);
		Case.x = x;
		Case.y = y;
	}
	
	
	public void seDeplacer() {
		// Comportement erratique par défaut
		//génère la direction de déplacement
		int x = (int)(Math.random() * (4-1)) + 1;
		int y = (int)(Math.random() * (4-1)) + 1;
		
		if (x==1) {
			x=-1;
		}
		else if (x==2) {
			x=0;
		}
		else if (x==3) {
			x=1;
		}
		else {
			x = 0;
		}
		
		if (y==1) {
			y=-1;
		}
		else if (y==2) {
			y=0;
		}
		else if (y==3) {
			y=1;
		}
		else {
			y=0;
		}
			
		if (this.Case.x+x>0 && this.Case.x+x<Saison1.tailleLoft) {
			this.Case.x = this.Case.x+x;
		}
		
		if (this.Case.y+y>0 && this.Case.y+y<Saison1.tailleLoft) {
			this.Case.y = this.Case.y+y;
		}
	}
	
	public void manger() {
		Vector<Element> element = new Vector(loft.getElement());
		for (int i=0;i<element.size();i++) {
			if (this.alimentation.contains(element.elementAt(i).getClass().getName())) {// Si la nourriture rentre dans le régime du Neuneu
				if (element.elementAt(i).Case.x==Case.x && element.elementAt(i).Case.y==Case.y && element.elementAt(i)!=this) {//Si le neuneu est sur une cas où il y a de la nourriture
					if (!loft.NeuNeus.contains(element.elementAt(i).getClass().getName())) {//Si c'est une nourriture
						((Nourriture)element.elementAt(i)).quantite--;
					}
					else {//Si c'est un NeuNeu
						loft.zone.supprimerObjet(element.elementAt(i));
						loft.Elements.remove(element.elementAt(i));
					}
					
					energie = energie + element.elementAt(i).energie;
				}
			}
		}
	}

	
	public void seReproduire() {
		Vector<Element> Elements = loft.getElement();
		
		for (int i=0; i<Elements.size();i++) {//Pour tous les éléments
			if (loft.NeuNeus.contains(Elements.elementAt(i).getClass().getName())) {//Si c'est un NeuNeu
				if (Elements.elementAt(i).Case.x==this.Case.x && Elements.elementAt(i).Case.y==this.Case.y && Elements.elementAt(i)!=this && Elements.elementAt(i).getClass().equals(this.getClass()) ) {
					if (!((NeuNeu)Elements.elementAt(i)).reproduit && !this.reproduit) {
						if (Elements.elementAt(i).energie>Saison1.energieMinimaleReprodution && this.energie>Saison1.energieMinimaleReprodution) {// Les neuneus doivent avoir une énergie minimale pour se reproduire
							((NeuNeu)Elements.elementAt(i)).donneNaissance();
							Elements.elementAt(i).energie = Elements.elementAt(i).energie - 50;
							this.energie = this.energie - 50;
						}
						this.temps_reproduction = loft.tour;
						((NeuNeu)Elements.elementAt(i)).temps_reproduction = loft.tour;
						
						this.reproduit = true;
						((NeuNeu)Elements.elementAt(i)).reproduit = true;
					}
				}
			}
		}
		
	}
	
	public void allinOne () {//pour appeler toutes les fonctions vitales d'un coup
		seDeplacer();
		manger();
		seReproduire();
	}
	
	public void donneNaissance() {
		
	}
	
	public void dessinerObjet(Graphics g) {
		g.setColor(this.color);
		g.drawRect(Case.x, Case.y, 2, 2);
	}
	
	
	
}
