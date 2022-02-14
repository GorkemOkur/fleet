/**
 * @author Şükrü Görkem Okur
 * a01001474:
 */


import java.text.DecimalFormat;

public class Pkw extends Fahrzeug {
	private static final long serialVersionUID = 1L;
	private DecimalFormat df = getDecimalFormat();
	
	private int prufjahr;
	
	public Pkw(int id, String marke, String modell, int baujahr, double grundpreis,int prufjahr) {
		super(id, marke, modell, baujahr, grundpreis);
		setPrufjahr(prufjahr);
	}
	
	public int getPrufjahr() {return prufjahr;}

	public void setPrufjahr(int prufjahr) {
		if(getToday() >= prufjahr) { this.prufjahr = prufjahr; }
		else {throw new IllegalArgumentException(" Grundpreis ungueltig.");}
	}

	@Override
	public int getRabatt() {
		int pruf = getToday() - this.prufjahr;
		int rabatt = (pruf * 5)+(pruf*2);
		if( rabatt < 15 ) {
			return rabatt;
		}else {
			return 15;
		}
	}
	
	@Override
	public String toString() {
		return "Typ:         PKW"+System.lineSeparator()+
				"Id:          " + this.getId()+System.lineSeparator()+
				"Marke:       " + this.getmarke()+System.lineSeparator()+
				"Modell:      " + this.getmodell()+System.lineSeparator()+
				"Baujahr:     " + this.getbaujahr()+System.lineSeparator()+
				"Grundpreis:  " + df.format(this.getgrundpreis())+System.lineSeparator()+
				"Servicejahr: " + this.getPrufjahr()+System.lineSeparator()+
				"Preis:       " +df.format( this.getPreis())+System.lineSeparator();
	}
	
}
