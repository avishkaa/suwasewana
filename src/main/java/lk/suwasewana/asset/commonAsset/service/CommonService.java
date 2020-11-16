package lk.suwasewana.asset.commonAsset.service;

import lk.suwasewana.asset.employee.controller.EmployeeRestController;
import lk.suwasewana.asset.employee.entity.Enum.Designation;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Service
public class CommonService {
    //common things to employee and offender - start
    public void commonUrlBuilder(Model model) {
        model.addAttribute("addStatus", true);
        model.addAttribute("designations", Designation.values());
/*        model.addAttribute("provinces", Province.values());
        model.addAttribute("districtUrl", MvcUriComponentsBuilder
                .fromMethodName(WorkingPlaceRestController.class, "getDistrict", "")
                .build()
                .toString());
        model.addAttribute("stationUrl", MvcUriComponentsBuilder
                .fromMethodName(WorkingPlaceRestController.class, "getStation", "")
                .build()
                .toString());*/
        Object[] arg = {"designation", "id"};
        model.addAttribute("employeeUrl", MvcUriComponentsBuilder
                .fromMethodName(EmployeeRestController.class, "getEmployeeByWorkingPlace", arg)
                .build()
                .toString());
    }


    //common things to employee and offender - end
    //common mobile number length employee,offender,guardian, petitioner - start
    // private final mobile length validator
    public String commonMobileNumberLengthValidator(String number) {
        if ( number.length() == 9 ) {
            number = "0".concat(number);
        }
        return number;
    }

}
