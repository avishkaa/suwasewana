package lk.suwasewana.asset.labTestParameter.entity;

import lk.suwasewana.asset.labTest.entity.Enum.ParameterHeader;
import lk.suwasewana.asset.labTest.entity.LabTest;
import lk.suwasewana.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabTestParameter extends AuditEntity {

    private String code;

    private String name;

    private String unit;

    private String max;

    private String min;

    @Enumerated( EnumType.STRING )
    private ParameterHeader parameterHeader;

    @ManyToMany( mappedBy = "labTestParameters", fetch = FetchType.LAZY )
    private List< LabTest > labTests = new ArrayList<>();


}
