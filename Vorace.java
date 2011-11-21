import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Vector;


public class Vorace extends Erratique {

	//Attributs
	
	//Methodes
	Vorace(Loft loft,int x, int y) {
		super(loft,x,y);
		this.color = new Color(0, 255, 0);
		alimentation = new Vector<String>();
		alimentation.add("carotte");
		alimentation.add("jambon");
		alimentation.add("coca");
		alimentation.add("whisky");
	}
	
	public void seDeplacer() {
		double var = 0;
		double min = 0;
		int init = 0;
		Case caseVisee = new Case();
		for (Element element : loft.getElement()) {
			if (this.alimentation.contains(element.getClass().getName()) && element!=this) {// Si la nourriture rentre dans le régime du Neuneu
				var = (double) Math.sqrt((double)(element.Case.x-this.Case.x)*(element.Case.x-this.Case.x) + (element.Case.y-this.Case.y)*(element.Case.y-this.Case.y));
				if (var<min || init==0) {
					caseVisee = element.Case;
					min = var;
					init = 1;
				}
			}
			
		}
		if (init==1) {
			double pente = 0;
			if ((caseVisee.x-Case.x)!=0) {
				pente = (double) (caseVisee.y-Case.y)/(caseVisee.x-Case.x);
			}
			if (pente!=0) {
				if (pente<0 && caseVisee.y > Case.y) {
					Case.x--;
					Case.y++;
				}
				else if (pente<0 && caseVisee.y < Case.y) {
					Case.x++;
					Case.y--;
				}
				else if (pente>0 && caseVisee.y > Case.y) {
					Case.x++;
					Case.y++;
				}
				else if (pente>0 && caseVisee.y < Case.y) {
					Case.x--;
					Case.y--;
				}
			}
			else if (pente==0 && caseVisee.y==Case.y) {
				if (caseVisee.x>Case.x) {
					Case.x++;
				}
				else if (caseVisee.x<Case.x) {
					Case.x--;
				}
			}
			else if (pente==0 && caseVisee.x==Case.x){
				if (caseVisee.y>Case.y) {
					Case.y++;
				}
				else if (caseVisee.y<Case.y) {
					Case.y--;
				}
			}
		}
		else {
			super.seDeplacer(); //Un vorace qui ne trouve plus de nourriture adopte le comportement d'un erratique
		}
	}
	
	public void donneNaissance() {
		loft.add(new Vorace(loft, (int)(Math.random()*(Saison1.tailleLoft-1)),(int)(Math.random()*(Saison1.tailleLoft-1))));
	}
}
