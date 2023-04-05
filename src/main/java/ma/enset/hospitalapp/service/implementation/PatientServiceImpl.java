package ma.enset.hospitalapp.service.implementation;

import lombok.extern.slf4j.Slf4j;
import ma.enset.hospitalapp.entities.Patient;
import ma.enset.hospitalapp.repository.PatientRepository;
import ma.enset.hospitalapp.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }


    @Override
    public void deleteById(Long id) {
        log.info("dans la méthodes deleteById");
        repository.deleteById(id);
        log.info("Patient supprimé");
    }

    @Override
    public void save(Patient patient) {
        log.info("dans la méthode save");
        try{
            repository.save(patient);
            log.info("patient enregistré");
        }catch (Exception e){
            log.info("patient non enregistré : "+e.getMessage());
        }
    }

    @Override
    public void update(Patient patient) {
        log.info("dans la méthode update");
        Patient p = repository.findById(patient.getId()).orElse(null);
        if(p == null){
            log.error("le patient avec l'id : "+patient.getId()+" , que vous voulez modifier n'a pas été trouvé");
        }else{
            p.setNom(patient.getNom());
            p.setScore(patient.getScore());
            p.setMalade(patient.isMalade());
            p.setDateNaissance(patient.getDateNaissance());
            repository.save(p);
            log.info("le patient "+ p.getId()+" _ a été mise à jour");
        }
    }

    @Override
    public Patient findById(Long id) {
        log.info("dans la méthode findById");
        Patient patient = repository.findById(id).orElse(null);
        if(patient == null){
            log.info("patient non trouvé ! ");
            return null;
        }else{
            log.info("patient trouvé");
            return patient;
        }
    }

    @Override
    public Page<Patient> findByNomContains(String kw, int page, int size) {
        log.info("dans la méthode findByNameContains");
        Page<Patient> patients = repository.findByNomContains(kw, PageRequest.of(page,size));
        log.info("Liste des patients Page = "+page+" . taille = "+size);
        return patients;
    }
}
