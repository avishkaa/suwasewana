package lk.suwasewana.asset.labTest.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Department {
    BI("Bio Chemistry"),
    CP("Clinical Pathology"),
    HL("Haematology"),
    MB("Microbiology"),
    IL("Immunology"),
    SL("Serology"),
    HP("Histopathology Cytology"),
    MU("Molecular Biology"),
    EX("Extra");

    private final String department;
}