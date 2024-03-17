package ma.enset.hosptialapp.repository;

import ma.enset.hosptialapp.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient,Long> {
//pour ces deux methodes seront accepte on va utiliser pageable ainsi qu'on peut
//passer le numero de la page et le size
    Page<Patient> findByNomContains(String keyword,Pageable pageable); //recuperer la page
    // qui contain le nom de patient

    @Query("select p from Patient p where p.nom like :x") // en utilisant Hql
    Page<Patient> search(@Param("x") String keyword,Pageable pageable);
}
