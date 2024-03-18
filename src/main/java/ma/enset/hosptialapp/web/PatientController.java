package ma.enset.hosptialapp.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.hosptialapp.entities.Patient;
import ma.enset.hosptialapp.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

   private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw)
    {

        Page<Patient> patientList = patientRepository.findByNomContains(kw,PageRequest.of(page,size));
        //we'll store data in Model
        model.addAttribute("patientList" ,patientList.getContent());
      //on va ajouter les pages au modele qui est un tableau qui contient le nbr de total de pages
        model.addAttribute("pages",new int[patientList.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw); //il va etre afficher à la vue
        return "patients"; //we'll return a view of patients.html that we'll create it in templates
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "keyword",defaultValue = "") String keyword
            ,@RequestParam(name = "page",defaultValue = "0") int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }


    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> ListPatient(){
        return patientRepository.findAll();
    }

    @GetMapping("/formPatient")
    public  String formPatient(Model model){
    model.addAttribute("patient",new Patient());
     return "formPatient";
    }
    @PostMapping(path = "/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        System.out.println(bindingResult);
        if(bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword"+keyword;
        //stocker le patient dans la base de donnees
        //apres le save on va rester dans la meme page
    }


    @GetMapping("/editPatient")
    public  String editPatient(Model model,Long id,String keyword,int page) {
        Patient patient = patientRepository.findById(id).get();
        if (patient == null) throw new RuntimeException("Patient introuvable");
    model.addAttribute("patient",patient);
    model.addAttribute("page",page);
    model.addAttribute("keyword",keyword);
    return "editPatient";
    }
}
