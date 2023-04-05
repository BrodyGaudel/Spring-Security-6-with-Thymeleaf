package ma.enset.hospitalapp.service;

import ma.enset.hospitalapp.entities.Patient;
import org.springframework.data.domain.Page;

public interface PatientService {
    void deleteById(Long id);
    void save(Patient patient);
    void update(Patient patient);
    Patient findById(Long id);
    Page<Patient>  findByNomContains(String kw, int page, int size);
}
