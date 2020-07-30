package lk.suwasewana.asset.labTestParameter.entity;

import lk.suwasewana.asset.labTest.entity.Enum.ParameterHeader;
import lk.suwasewana.asset.labTest.entity.LabTest;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LabTestParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String name;

    private String unit;

    private String max;

    private String min;

    @Enumerated(EnumType.STRING)
    private ParameterHeader parameterHeader;

    @ManyToMany(mappedBy = "labTestParameters",fetch=FetchType.LAZY)
    private List<LabTest> labTests = new ArrayList<>();


}
