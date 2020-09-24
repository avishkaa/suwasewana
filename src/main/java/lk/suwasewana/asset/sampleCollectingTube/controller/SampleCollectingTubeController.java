package lk.suwasewana.asset.sampleCollectingTube.controller;


import lk.suwasewana.asset.sampleCollectingTube.entity.SampleCollectingTube;
import lk.suwasewana.asset.sampleCollectingTube.service.SampleCollectingTubeService;
import org.ehcache.shadow.org.terracotta.statistics.SampledStatisticAdapter;
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

    private String commonMethod(Model model, boolean addStatus, SampleCollectingTube sampleCollectingTube){
        model.addAttribute("sampleCollectingTube", sampleCollectingTube);
        model.addAttribute("addStatus", addStatus);
        return "sampleCollectingTube/addSampleCollectingTube";
    }
    @GetMapping
    public String sampleCollectingTubePage(Model model) {
        model.addAttribute("sampleCollectingTubes", sampleCollectingTubeService.findAll());
        return "sampleCollectingTube/sampleCollectingTube";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        return commonMethod(model,false, sampleCollectingTubeService.findById(id));
    }

    @GetMapping("/add")
    public String form(Model model) {
        return commonMethod(model, true, new SampleCollectingTube());
    }

    // Above method support to send data to front end - All List, update, edit
    //Bellow method support to do back end function save, delete, update, search

    @PostMapping(value = {"/save", "/update"})
    public String addSampleCollectingTube(@Valid @ModelAttribute SampleCollectingTube sampleCollectingTube, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return commonMethod(model, true, sampleCollectingTube);
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
