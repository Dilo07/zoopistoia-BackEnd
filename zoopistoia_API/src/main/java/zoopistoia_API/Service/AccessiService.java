package zoopistoia_API.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zoopistoia_API.Model.Accesso;
import zoopistoia_API.Model.Recinzione;
import zoopistoia_API.Repository.AccessiRepository;

@Service
public class AccessiService {
	
	@Autowired
	public AccessiRepository accessiRepository;
	 
	public Timestamp timestamp; 

	public List<Accesso> getAllAccessi() {
		return accessiRepository.findAll();
	}

	public boolean Ingresso(Accesso accesso) {
		timestamp = new Timestamp(System.currentTimeMillis());
		try {
			accesso.setData_ingresso(timestamp);
			accessiRepository.save(accesso);
			return true;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}	
	}
	
	public boolean Uscita(Accesso accesso) {
		timestamp = new Timestamp(System.currentTimeMillis());
		// mi salvo su una variabile di tipo Accesso l'indirizzo dell'oggetto con id getId() passato nel body(json)
		Accesso app = accessiRepository.getOne(accesso.getId());
		if(app.getData_ingresso()!= null) {
			// setto la data ingresso
			accesso.setData_ingresso(app.getData_ingresso());
			// setto la data uscita
			accesso.setData_uscita(timestamp);
			accessiRepository.save(accesso);
			return true;
		}
		else {
			return false;
		}
	}

	public Accesso getDipinRec(Integer id,Timestamp datein,Timestamp dateout) {
		return this.accessiRepository.getDipinRec(id,datein,dateout);
	}
	
	public Accesso getRecforDip(Integer id,Timestamp datein,Timestamp dateout) {
		return this.accessiRepository.getRecforDip(id,datein,dateout);
	}

	public void deleteAccesso(Integer id) {
		accessiRepository.deleteById(id);
	}
}
