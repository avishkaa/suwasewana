package lk.suwasewana.asset.sampleCollectingTube.controller;


import lk.suwasewana.asset.sampleCollectingTube.entity.SampleCollectingTube;
import lk.suwasewana.asset.sampleCollectingTube.service.SampleCollectingTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/sampleCollectingTube")
public class SampleCollectingTubeController {
    private final SampleCollectingTubeService sampleCollectingTubeService;

    @Autowired
    public SampleCollectingTubeController(SampleCollectingTubeService sampleCollectingTubeService) {
        this.sampleCollectingTubeService = sampleCollectingTubeService;
    }

    @GetMapping
    public String sampleCollectingTubePage(Model model) {
        model.addAttribute("sampleCollectingTubes", sampleCollectingTubeService.findAll());
        return "sampleCollectingTube/sampleCollectingTube";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("sampleCollectingTube", sampleCollectingTubeService.findById(id));
        model.addAttribute("addStatus", false);
        return "sampleCollectingTube/addSampleCollectingTube";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("addStatus", true);
        model.addAttribute("sampleCollectingTube", new SampleCollectingTube());
        return "sampleCollectingTube/addSampleCollectingTube";
    }

    // Above method support to send data to front end - All List, update, edit
    //Bellow method support to do back end function save, delete, update, search

    @PostMapping(value = {"/save", "/update"})
    public String addSampleCollectingTube(@Valid @ModelAttribute SampleCollectingTube sampleCollectingTube, BindingResult result, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("addStatus", false);
            model.addAttribute("sampleCollectingTube", sampleCollectingTube);
            return "sampleCollectingTube/addSampleCollectingTube";
        }
        sampleCollectingTubeService.persist(sampleCollectingTube);
        return "redirect:/sampleCollectingTube";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id) {
        sampleCollectingTubeService.delete(id);
        return "redirect:/sampleCollectingTube";
    }


}
