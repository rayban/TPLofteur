//package com.objet.lofteurs;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * un panneau de dessin pour le loft
 * 
 * @author moreau
 *
 */
class LoftPanel extends JPanel {
	/**
	 * référence sur la liste des objets à dessiner
	 */
	private Vector<ObjetDessinable> listeObjets;
	
	/**
	 * constructeur
	 * 
	 * @param listeObjets référence sur la liste des objets (gérée par la ZoneGraphique)
	 */
	public LoftPanel(Vector<ObjetDessinable> listeObjets) {
		this.listeObjets = listeObjets;
	}
	
	/**
	 * on redéfinit la méthode paint() : elle se contente d'appeler les méthodes
	 * dessinerObjet() de la liste d'objets dessinables
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		synchronized (listeObjets) {
			// on redessine tout
			for (ObjetDessinable x : listeObjets) {
				x.dessinerObjet(g);
			}
			
		}
		
	}
}
