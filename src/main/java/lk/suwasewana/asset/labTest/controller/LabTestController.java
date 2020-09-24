package lk.suwasewana.asset.labTest.controller;


import lk.suwasewana.asset.labTest.entity.Enum.Department;
import lk.suwasewana.asset.labTest.entity.Enum.LabtestDoneHere;
import lk.suwasewana.asset.labTest.entity.LabTest;
import lk.suwasewana.asset.labTest.service.LabTestService;
import lk.suwasewana.asset.labTestParameter.service.LabTestParameterService;
import lk.suwasewana.asset.sampleCollectingTube.service.SampleCollectingTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/labTest")
public class LabTestController {
    private final LabTestService labTestService;
    private final LabTestParameterService labTestParameterService;
    private final SampleCollectingTubeService sampleCollectingTubeService;

    @Autowired
    public LabTestController(LabTestService labTestService, LabTestParameterService labTestParameterService,
                             SampleCollectingTubeService sampleCollectingTubeService) {
        this.labTestService = labTestService;
        this.labTestParameterService = labTestParameterService;
        this.sampleCollectingTubeService = sampleCollectingTubeService;
    }

    private String commonMethod(Model model, LabTest labTest, boolean addState){
        model.addAttribute("labTest", labTest);
        model.addAttribute("addStatus", addState);
        model.addAttribute("department", Department.values());
        model.addAttribute("labTestDoneHere", LabtestDoneHere.values());
        model.addAttribute("labTestParameters", labTestParameterService.findAll());
        model.addAttribute("sampleCollectingTests", sampleCollectingTubeService.findAll());
        return "labTest/addLabTest";
    }

    @GetMapping
    public String laboratoryTestPage(Model model) {
        List<LabTest> labTests = labTestService.findAll();
        model.addAttribute("labTests", labTests);
        return "labTest/labTest";
    }

    @GetMapping("/{id}")
    public String laboratoryTestView(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("labTestDetail", labTestService.findById(id));
        return "labTest/labTest-detail";
    }

    @GetMapping("/edit/{id}")
    public String editLabTestFrom(@PathVariable("id") Integer id,Model model) {
        return commonMethod(model, labTestService.findById(id), false);
    }

    @GetMapping("/add")
    public String laboratoryTestAddFrom(Model model) {
        return commonMethod(model, new LabTest(), true);
    }

    // Above method support to send data to front end - All List, update, edit
    //Bellow method support to do back end function save, delete, update, search

    @PostMapping(value = {"/add","/update"})
    public String addLabTest(@Valid @ModelAttribute LabTest labTest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + ": " + error.getDefaultMessage());
            }
            return commonMethod(model, labTest, false);
        }
    if(labTest.getId() != null){
      labTestService.persist(labTest);
    }
        labTestService.persist(labTest);
        return "redirect:/labTest";
    }

    @GetMapping(value = "/remove/{id}")
    public String removeLabTest(@PathVariable Integer id) {
        labTestService.delete(id);
        return "redirect:/labTest";
    }

    @GetMapping(value = "/search")
    public String search(Model model, LabTest labTest) {
        model.addAttribute("labTestDetail", labTestService.search(labTest));
        return "labTest/labTest-detail";
    }
}
