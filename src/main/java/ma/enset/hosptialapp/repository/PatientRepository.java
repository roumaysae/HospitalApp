package ma.enset.hosptialapp.repository;

import ma.enset.hosptialapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

}
