package ma.enset.hosptialapp.web;

import lombok.AllArgsConstructor;
import ma.enset.hosptialapp.entities.Patient;
import ma.enset.hosptialapp.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page ,@RequestParam(name = "size",defaultValue = "4") int size){
        Page<Patient> patientList = patientRepository.findAll(PageRequest.of(page,size));
        //we'll store data in Model
        model.addAttribute("patientList" ,patientList.getContent());
      //on va ajouter les pages au modele qui est un tableau qui contient le nbr de total de pages
        model.addAttribute("pages",new int[patientList.getTotalPages()]);

        return "patients"; //we'll return a view of patients.html that we'll create it in templates
    }
}
