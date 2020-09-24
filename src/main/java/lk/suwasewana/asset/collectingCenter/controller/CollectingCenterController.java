package lk.suwasewana.asset.collectingCenter.controller;


import lk.suwasewana.asset.collectingCenter.entity.CollectingCenter;
import lk.suwasewana.asset.collectingCenter.entity.Enum.CollectingCenterStatus;
import lk.suwasewana.asset.collectingCenter.service.CollectingCenterService;
import lk.suwasewana.util.service.DateTimeAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/collectingCenter")
public class CollectingCenterController {
    private final CollectingCenterService collectingCenterService;
    private final DateTimeAgeService dateTimeAgeService;

    @Autowired
    public CollectingCenterController(CollectingCenterService collectingCenterService, DateTimeAgeService dateTimeAgeService) {
        this.collectingCenterService = collectingCenterService;
        this.dateTimeAgeService = dateTimeAgeService;
    }
private String commonMethod(Model model, CollectingCenter collectingCenter, boolean addState){
    model.addAttribute("collectingCenter", collectingCenter);
    model.addAttribute("addStatus", addState);
    model.addAttribute("collectingCenterStatus", CollectingCenterStatus.values());
    return "collectingCenter/addCollectingCenter";
}

    @GetMapping
    public String collectingCenterPage(Model model) {
        model.addAttribute("collectingCenters", collectingCenterService.findAll());
        return "collectingCenter/collectingCenter";
    }

    @GetMapping("/{id}")
    public String collectingCenterView(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("collectingCenterDetails", collectingCenterService.findById(id));
        return "collectingCenter/collectingCenter-detail";
    }

    @GetMapping("/edit/{id}")
    public String editCollectingCenterFrom(@PathVariable("id") Integer id, Model model) {
        return commonMethod(model, collectingCenterService.findById(id), false);
    }

    @GetMapping("/add")
    public String collectingCenterAddFrom(Model model) {
        return commonMethod(model, new CollectingCenter(), true);
    }

    // Above method support to send data to front end - All List, update, edit
    //Bellow method support to do back end function save, delete, update, search

    @PostMapping(value = {"/save", "/update"})
    public String addCollectingCenter(@Valid @ModelAttribute CollectingCenter collectingCenter, BindingResult result, Model model) {
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + ": " + error.getDefaultMessage());
            }
            return commonMethod(model, collectingCenter, true);
        }
        if (collectingCenter.getId() != null) {
            collectingCenter.setEstablishedDate(dateTimeAgeService.getCurrentDate());
        }
        collectingCenterService.persist(collectingCenter);
        return "redirect:/collectingCenter";
    }

    @GetMapping("/remove/{id}")
    public String removeCollectingCenter(@PathVariable Integer id) {
        collectingCenterService.delete(id);
        return "redirect:/collectingCenter";
    }

    @GetMapping("/search")
    public String search(Model model, CollectingCenter collectingCenter) {
        model.addAttribute("collectingCenterDetail", collectingCenterService.search(collectingCenter));
        return "collectingCenter/collectingCenter-detail";
    }


}
