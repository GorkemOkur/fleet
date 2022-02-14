/**
 * @author <Þükrü Görkem Okur>
 * a01001474:
 */


import java.text.DecimalFormat;

public class Lkw extends Fahrzeug {
	private static final long serialVersionUID = 1L;
	private DecimalFormat df = getDecimalFormat();
	
	public Lkw(int id, String marke, String modell, int baujahr, double grundpreis) {
		super(id, marke, modell, baujahr, grundpreis);
	}

	@Override
	public int getRabatt() {
		int rabatt = getAlter() * 5;
		if( rabatt < 20) {
			return rabatt;
		}else {
			return 20;
		}
	}

	@Override
	public String toString() {
		return "Typ:         LKW"+System.lineSeparator()+
				"Id:          " + this.getId()+System.lineSeparator()+
				"Marke:       " + this.getmarke()+System.lineSeparator()+
				"Modell:      " + this.getmodell()+System.lineSeparator()+
				"Baujahr:     " + this.getbaujahr()+System.lineSeparator()+
				"Grundpreis:  " + df.format(this.getgrundpreis())+System.lineSeparator()+
				"Preis:       " + df.format(this.getPreis()) + System.lineSeparator();
	}

	
}
