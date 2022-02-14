/**
 * @author Şükrü Görkem Okur
 * a01001474:
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SerializedFahrzeugDAO implements FahrzeugDAO {
	
	private String filename;
	private File file;
	private List<Fahrzeug> fahrzeuge;

	private ObjectInputStream reader;
	private ObjectOutputStream writer;

	private void speicherList(List<Fahrzeug> fahrzeuge){
		try {
			 writer = new ObjectOutputStream(new FileOutputStream(this.file));
			 writer.writeObject(fahrzeuge);
			 writer.close();
		} catch (Exception e) {
			System.out.println("Fehler bei Serialisierung: "+e.toString());
			System.exit(1);
		}
	}
	
	public SerializedFahrzeugDAO(String filename) {
		this.filename = filename;
		this.file = new File(this.filename);
			if (!file.exists()){
			    try {
					file.createNewFile();
				}catch(IOException e){e.printStackTrace();}
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fahrzeug> getFahrzeugList() {
		try {
			FileInputStream f = new FileInputStream(file);
			if(this.file.exists() && f.available()  > 0) {
				try {
					reader = new ObjectInputStream(f);
					this.fahrzeuge =  (ArrayList<Fahrzeug>) reader.readObject();
					reader.close();
				} catch (Exception e) {
					System.out.println("Fehler bei Deserialisierung: "+ e.getMessage() );
					System.exit(1);
				}
				
			}else {
				fahrzeuge = new ArrayList<Fahrzeug>();
			}
		} catch (IOException e) {
			System.out.println("Fehler bei Deserialisierung: "+ e.getMessage() );
			System.exit(1);
		}
		
		return this.fahrzeuge;
	}

	@Override
	public Fahrzeug getFahrzeugbyId(int id) {
		Fahrzeug fh=null;
		boolean ex = false;
		fahrzeuge = getFahrzeugList();
		
			for(Iterator<Fahrzeug> it = fahrzeuge.iterator(); it.hasNext() ;) {
				fh = it.next();
				if(fh.getId() == id){
					ex=true;
					break;
				}
			}
		if(ex) {
			return fh;
		}else {return null;}
	}

	@Override
	public void speichereFahrzeug(Fahrzeug fahrzeug) {
			fahrzeuge=this.getFahrzeugList();
			boolean isEx=false;
			for(Fahrzeug fh : fahrzeuge) {
				if(fh.getId() == fahrzeug.getId()) {isEx=true;break;}
			}
			if(!isEx) {
				fahrzeuge.add(fahrzeug);
				speicherList(fahrzeuge);
			}else if(isEx){throw new IllegalArgumentException("Error: Fahrzeug bereits vorhanden. "
					+ "(id=" + fahrzeug.getId() + ")" );}
	}

	@Override
	public void loescheFahrzeug(int id) {
		fahrzeuge = getFahrzeugList();
		for(Iterator<Fahrzeug> it = fahrzeuge.iterator(); it.hasNext(); ) {
			Fahrzeug fh = it.next();
			if(fh.getId() == id) {
				it.remove();
				speicherList(fahrzeuge);
			}
		}
	}

	
}

