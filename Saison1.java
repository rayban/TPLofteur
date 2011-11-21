//package com.objet.lofteurs;

public class Saison1 {

	public static int nombreLofteurs = 200;
	public static int tailleLoft = 500; //taille du loft en nombre de case en largeur (le loft est carré par défaut)
	public static float proportionErratique = .40f;
	public static float proportionVorace = .25f;
	public static float proportionCannibale = 0.05f;
	public static float proportionLapin = .30f;
	
	public static int quantite_nourriture = 10;
	public static float proportion_carotte = .75f;
	public static float proportion_jambon = .25f;
	
	public static int quantite_boisson = 10;
	public static float proportion_coca = .75f;
	public static float proportion_whisky = .25f;
	
	public static int periodeReproduction = 300; //période au bout de laquelle un neuneu peut à nouveau se reproduire
	public static int energieMinimaleReprodution = 100; //énergie minimale pour se reproduire
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Saison1().primeTime();
	}
	
	public void primeTime() {
		ZoneGraphique zone = new ZoneGraphique("Mon premier loft");
		Loft loft = new Loft(tailleLoft, zone);
		//loft.remplissageAleatoire(0.1f);
		zone.ajouterObjet(loft);
		
		//ajout de la nourriture
		for (int i=0 ; i<quantite_nourriture ; i++) {
			double x = Math.random();
			int x1 = (int)(Math.random()*(tailleLoft))+1;
			int y1 = (int)(Math.random()*(tailleLoft))+1;
			if (x<proportion_carotte) {
				loft.add(new carotte(x1, y1, 100, 100));
			}
			else {
				loft.add(new jambon(x1, y1, 100, 100));
			}
		}
		
		//ajout des boissons
		for (int i=0 ; i<quantite_boisson ; i++) {
			double x = Math.random();
			int x1 = (int)(Math.random()*(tailleLoft))+1;
			int y1 = (int)(Math.random()*(tailleLoft))+1;
			if (x<proportion_coca) {
				loft.add(new coca(x1, y1, 100, 100));
			}
			else {
				loft.add(new whisky(x1, y1, 100, 100));
			}
		}
		
		for (int i=0 ; i<nombreLofteurs ; i++) {
			double x = Math.random();
			if (x<proportionLapin) {
				loft.add(new Lapin(loft, (int)(Math.random()*(tailleLoft-1)),(int)(Math.random()*(tailleLoft-1))));
			}
			else {
				x -= proportionLapin;
				if (x<proportionErratique) {
					loft.add(new Erratique(loft, (int)(Math.random()*(tailleLoft-1)),(int)(Math.random()*(tailleLoft-1))));
				}
				else {
					x -= proportionErratique;
					if (x<proportionCannibale) {
						loft.add(new Cannibale(loft, (int)(Math.random()*(tailleLoft-1)),(int)(Math.random()*(tailleLoft-1))));
					}
					
					else {
						x -= proportionVorace;
						if (x<proportionVorace) {
							loft.add(new Vorace(loft, (int)(Math.random()*(tailleLoft-1)),(int)(Math.random()*(tailleLoft-1))));
						}
					}
				}
			}
		}
		
		
		zone.rafraichir();
		
		loft.go();
	}

}
