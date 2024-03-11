package ma.enset.hosptialapp;

import ma.enset.hosptialapp.entities.Patient;
import ma.enset.hosptialapp.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class HosptialAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HosptialAppApplication.class, args);
    }


    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            // Your code here
            Patient patient = Patient.builder()
                    .nom("Roumaysae el amrani")
                    .malade(false)
                    .score(120)
                    .dateNaissance(new Date())
                    .build();
            patientRepository.save(patient);
            patientRepository.save(new Patient(null,"hafsa",new Date(),false,200));
            patientRepository.save(new Patient(null,"mohammed",new Date(),true,30));
            patientRepository.save(new Patient(null,"akram",new Date(),true,80));
        };
    }
}
