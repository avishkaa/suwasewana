package lk.suwasewana.asset.consultation.controller;


import lk.suwasewana.asset.consultation.entity.Consultation;
import lk.suwasewana.asset.consultation.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {
    private final ConsultationService consultationService;

    @Autowired
    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }
    private String commonMethod(Model model, boolean addStatus, Consultation consultation){
        model.addAttribute("sampleCollectingTube", consultation);
        model.addAttribute("addStatus", addStatus);
        return "consultation/addConsultation";
    }
    @GetMapping
    public String consultationPage(Model model) {
        model.addAttribute("consultations", consultationService.findAll());
        return "consultation/consultation";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        return commonMethod(model,false, consultationService.findById(id));

    }

    @GetMapping("/add")
    public String form(Model model) {
        return commonMethod(model, true, new Consultation());
    }

    // Above method support to send data to front end - All List, update, edit
    //Bellow method support to do back end function save, delete, update, search

    @PostMapping(value = {"/save", "/update"})
    public String addConsultation(@Valid @ModelAttribute Consultation consultation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return commonMethod(model,false, consultation);
        }
        consultationService.persist(consultation);
        return "redirect:/consultation";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        consultationService.delete(id);
        return "redirect:/consultation";
    }


}
