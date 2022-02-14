import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Şükrü Görkem Okur
 * a01001474:
 */


public class FahrzeugClient {
	
	public static void main(String[] args) {
		
		List<Fahrzeug> fahrzeuge;
		DecimalFormat df = Fahrzeug.getDecimalFormat();
		String datei;
		String befehl;		
		
		if(args.length > 1){
			datei = args[0];
			befehl = args[1];
			
			try {
				FahrzeugManagement mgt = new FahrzeugManagement(datei);
				if(befehl.equalsIgnoreCase("show")){
					if(args.length > 2){
						int id = Integer.parseInt(args[2]);
						if(mgt.getFahrzeugbyId(id) != null){
							System.out.println(mgt.getFahrzeugbyId(id));
						}else {
							throw new IllegalArgumentException(" Fahrzeug nicht vorhanden. (id="+id+")");
						}
					}else{
						fahrzeuge = mgt.getFahrzeugList();
						for(Fahrzeug fh: fahrzeuge){
							System.out.println(fh);
						}
					}
				}else // ADD add pkw <id> <marke> <modell> <baujahr> <grundpreis> <ueberpruefungsdatum>
					if(befehl.equalsIgnoreCase("add")){
					int id , baujahr , prufjahr;
					String marke , modell;
					double grundpreis;
//Parameter 'add pkw <id> <marke> <modell> <baujahr> <grundpreis> <ueberpruefungsdatum>'
//test.data add pkw 5 Tesla Model S 2016 65000 2016
					
					if(args[2].equalsIgnoreCase("pkw") && args.length == 9  ){
						id = Integer.parseInt(args[3]);
						marke = args[4];
						modell = args[5];
						baujahr = Integer.parseInt(args[6]);
						grundpreis = Double.parseDouble(args[7]);
						prufjahr = Integer.parseInt(args[8]);
						
						Pkw fahrzeug = new Pkw(id, marke, modell, baujahr, grundpreis, prufjahr);
						mgt.speichereFahrzeug(fahrzeug);
						
						
						
					}else if(args[2].equalsIgnoreCase("lkw") && args.length == 8){
						id = Integer.parseInt(args[3]);
						marke = args[4];
						modell = args[5];
						baujahr = Integer.parseInt(args[6]);
						grundpreis = Double.parseDouble(args[7]);
						
						Lkw fahrzeug = new Lkw(id, marke, modell, baujahr, grundpreis);
						mgt.speichereFahrzeug(fahrzeug);
						
					}else{throw new IllegalArgumentException("Error: Parameter ungueltig.");}
				}else if(befehl == "del" && args.length > 3){
					int id = Integer.parseInt(args[3]);
					mgt.loescheFahrzeug(id);
					
				}else //	COUNT
					if(befehl.equalsIgnoreCase("count")){
						fahrzeuge = mgt.getFahrzeugList();
					if(args.length > 2){
						if(args[2].equalsIgnoreCase("pkw")){
							System.out.println(countOfCars(f -> f instanceof Pkw,fahrzeuge));//PLC-3 folio seite 59.
						}else if(args[2].equalsIgnoreCase("lkw")){
							System.out.println(countOfCars(f -> f instanceof Lkw,fahrzeuge));//PLC-3 folio seite 59.
						}else{
							throw new IllegalArgumentException("Error: Parameter ungueltig.");
						}
					}else{
						System.out.println(countOfCars(f -> f instanceof Fahrzeug,fahrzeuge));
					}

				}else 
					if(befehl.equalsIgnoreCase("meanprice")){
						fahrzeuge = mgt.getFahrzeugList();
						double meanprice=0;
						for(Fahrzeug fh : fahrzeuge){
							meanprice+=fh.getPreis();
						}
						System.out.println(df.format(meanprice/fahrzeuge.size()));
						
				}else if(befehl.equalsIgnoreCase("oldest")){
					fahrzeuge = mgt.getFahrzeugList();
					Fahrzeug old = fahrzeuge.get(0);
					List<Fahrzeug> oldest = new ArrayList<Fahrzeug>() ;
					//find to oldest
					for(Fahrzeug fh : fahrzeuge){
						if(old.getAlter() < fh.getAlter()){
							old = fh;
						}
					}
					// add if anyone same old with oldest
					for(Fahrzeug fh : fahrzeuge){
						if(old.getAlter() == fh.getAlter()) {
							oldest.add(fh);
						}
					}
					
					for(Fahrzeug fh : oldest){
						System.out.println("Id: "+fh.getId());
					}
				}else if(befehl.equalsIgnoreCase("del")){
					int id = Integer.parseInt(args[2]);
					if( args[2] != null ){
						if( mgt.getFahrzeugbyId(id) != null) {
						mgt.loescheFahrzeug(id); 
						}else {throw new IllegalArgumentException("Error: Fahrzeug nicht vorhanden. (id="+id+")");}
					}else {
						throw new IllegalArgumentException("Error: Parameter ungueltig.");
					}
					
				}
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage()  );
				//System.exit(1);
			}
		}
		
	}
	//test.data add pkw 5 Tesla Model S 2016 65000 2016
	//PLC-3 folio seite 59.
	public static int countOfCars(Predicate<Fahrzeug> p , List<Fahrzeug> fahrzeuge){
		int count=0;
		for(Fahrzeug fh:fahrzeuge){
			if(p.test(fh)){count++;}
		}
		return count;
	}
}
