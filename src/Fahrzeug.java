/**
 * @author Şükrü Görkem Okur
 * a01001474:
 */


import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class Fahrzeug implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int Id;
	private String marke;
	private String modell;
	private int baujahr;
	private double grundpreis;
	
	
	
	public Fahrzeug(int id, String marke, String modell, int baujahr, double grundpreis) {
		setId(id);
		setmarke(marke);
		setmodell(modell);
		setbaujahr(baujahr);
		setgrundpreis(grundpreis);
	}
	
	public int getId() { return Id; }
	public String getmarke() { return marke; }
	public String getmodell() { return modell; }
	public int getbaujahr() { return baujahr; }
	public double getgrundpreis() { return grundpreis; }

	
	public void setId(int id) { 
			if(id > 0) {this.Id = id; }
			else {throw new IllegalArgumentException(" Parameter ungueltig.");}
		}
	
	public void setmarke(String marke) { 
			if(marke.length() > 0) { this.marke = marke; }
			else {throw new IllegalArgumentException(" Parameter ungueltig.");}
		}
	
	public void setmodell(String modell) { 
			if(modell.length() > 0) { this.modell = modell; }
			else {throw new IllegalArgumentException(" Parameter ungueltig.");} 
		}
	
	public void setbaujahr(int baujahr) { 
			if(baujahr <= getToday()) { this.baujahr = baujahr; }
			else {throw new IllegalArgumentException("Error: Baujahr ungueltig.");}
		}
	
	public void setgrundpreis(double grundpreis) { 
			if(grundpreis > 0) { this.grundpreis = grundpreis; }
			else {throw new IllegalArgumentException(" Grundpreis ungueltig.");}
		}

	public int getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int today = Integer.parseInt( sdf.format(new Date()) );
		return today;
	}
	
	public int getAlter() { return getToday() - this.baujahr; }
	
	public abstract int getRabatt();
	
	public double getPreis() {
		double rabatt = (this.grundpreis / 100)*getRabatt();
		return (this.grundpreis - rabatt);
	}
	
	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}		
}
