import java.awt.Color;
import java.io.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

public class Loft implements ObjetDessinable{

	//Attributs
	protected ZoneGraphique zone;
	protected Vector<Element> Elements;
	protected Vector<String> NeuNeus;
	protected int tour;
	
	//Methodes
	Loft(int tailleLoft, ZoneGraphique zone) {
		this.zone = zone;
		Elements = new Vector<Element>();
		NeuNeus = new Vector<String>();
		NeuNeus.add("Erratique");
		NeuNeus.add("Vorace");
		NeuNeus.add("Lapin");
		NeuNeus.add("Cannibale");
		this.tour = 0;
	}
	
	public void add(Element element) {
		Elements.add(element);
		zone.ajouterObjet(element);
	}
	
	public void dessinerObjet(Graphics g) {
		for (int i=0;i<Saison1.tailleLoft+2;i++) {
			if (i%2==0) {
				g.setColor(new Color(100, 100, 100));
			}
			else {
				g.setColor(new Color(90, 90, 90));
			}
			g.drawLine(0, i, Saison1.tailleLoft+2, i);
		}
	}
	
	public void checkNourriture() {
			for (int i=0; i<Elements.size();i++) {
				if (!NeuNeus.contains(Elements.elementAt(i).getClass().getName())) {//Si l'élément considéré est une nourriture
					if (((Nourriture)Elements.elementAt(i)).quantite==0) {
						zone.supprimerObjet(Elements.elementAt(i));
						Elements.remove(i);
					}
				}
			}
	}
	
	public void affaiblirNeuNeu() {
		for (int i=0; i<Elements.size();i++) {
			if (NeuNeus.contains(Elements.elementAt(i).getClass().getName())) {//Si l'élement considéré est un Neuneu
				Elements.elementAt(i).energie--; //décrémente l'énergie du neuneu
				if (Elements.elementAt(i).energie==0) {
					zone.supprimerObjet(Elements.elementAt(i));
					Elements.remove(i);
				}
			}
		}
	}
	
	public void checkReproductibilite() { // Verifie si un neuneu peut à nouveau se reproduire
		for (int i=0, c=Elements.size(); i<c;i++) {
				if (NeuNeus.contains(Elements.elementAt(i).getClass().getName())) {//Si l'élement considéré est un Neuneu
					if (((NeuNeu)Elements.elementAt(i)).reproduit && (this.tour-((NeuNeu)Elements.elementAt(i)).temps_reproduction>Saison1.periodeReproduction)) {
						((NeuNeu)Elements.elementAt(i)).reproduit = false;
					}
				}
		}
		
	}

	public void go() {
		while (true) {
			for (int i=0;i<Elements.size();i++) {
				if (NeuNeus.contains(Elements.elementAt(i).getClass().getName())) {
					Elements.elementAt(i).allinOne(); //Fait faire tout ce qu'il peut faire à un neuneu lors d'un tour
					this.checkNourriture();
				}
			}
			this.affaiblirNeuNeu();
			this.checkReproductibilite();
			this.zone.rafraichir();
			
			try {
				Thread.sleep(10);
			}
			catch(Exception e)
			{
			System.out.println(e);
			}
			
			this.tour++;
		}
	}
	
	public Vector<Element> getElement() {
		return Elements;
	}

}
