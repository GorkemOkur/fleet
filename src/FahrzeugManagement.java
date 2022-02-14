/**
 * @author <Þükrü Görkem Okur>
 * a01001474:
 */


import java.util.List;

public class FahrzeugManagement {
	private FahrzeugDAO fdao;
	
	public FahrzeugManagement(String fileName) {
		this.fdao = new SerializedFahrzeugDAO(fileName);
	}

	public List<Fahrzeug> getFahrzeugList(){
		return fdao.getFahrzeugList();
	}

	public Fahrzeug getFahrzeugbyId(int id){
		return fdao.getFahrzeugbyId(id);
	}
	
	public void speichereFahrzeug(Fahrzeug fahrzeug){
		fdao.speichereFahrzeug(fahrzeug);
	}

	public void loescheFahrzeug(int id){
		fdao.loescheFahrzeug(id);
	}
	
}
