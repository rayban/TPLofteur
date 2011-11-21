import java.awt.Color;
import java.util.Vector;


public class Lapin extends NeuNeu {

	//Attributs
	
	//Methodes
	Lapin(Loft loft,int x, int y) {
		super(loft,x,y);
		this.color = new Color(0, 0, 255);
		alimentation = new Vector<String>();
		alimentation.add("carotte");
		alimentation.add("coca");
	}
	
	public void seDeplacer() {
		
		int init = 0;
		if (!reproduit) {
			double var = 0;
			double min = 0;
			Case caseVisee = new Case();
			for (Element element : loft.getElement()) {
				if (loft.NeuNeus.contains(element.getClass().getName())) {//Si c'est un NeuNeu
					if (element.getClass().equals(this.getClass()) && element!=this && !((NeuNeu)element).reproduit) { //Les lapins ne se reproduisent qu'entre eux
						var = (double) Math.sqrt((double)(element.Case.x-this.Case.x)*(element.Case.x-this.Case.x) + (element.Case.y-this.Case.y)*(element.Case.y-this.Case.y));
						if (var<=min || init==0) {
							caseVisee = element.Case;
							min = var;
							init = 1;
						}
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
		}
		if ((!reproduit && init==0) || reproduit) { //Un Lapin qui ne peut se reproduire ou qui s'est déjà reproduit adopte le comportement d'un erratique
			super.seDeplacer();
			
		}
		
	}
	
	public void donneNaissance() {
		loft.add(new Lapin(loft, (int)(Math.random()*(Saison1.tailleLoft-1)),(int)(Math.random()*(Saison1.tailleLoft-1))));
	}
}