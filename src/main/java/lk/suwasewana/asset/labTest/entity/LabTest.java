package lk.suwasewana.asset.labTest.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.suwasewana.asset.invoice.entity.InvoiceHasLabTest;
import lk.suwasewana.asset.labTest.entity.Enum.Department;
import lk.suwasewana.asset.labTest.entity.Enum.LabtestDoneHere;
import lk.suwasewana.asset.labTestParameter.entity.LabTestParameter;
import lk.suwasewana.asset.medicalPackage.entity.MedicalPackage;
import lk.suwasewana.asset.sampleCollectingTube.entity.SampleCollectingTube;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "LabTest" )
public class LabTest {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( nullable = false, unique = true )
    private String code;

    @Column( nullable = false )
    private String name;

    @Column( precision = 10, scale = 2 )
    private BigDecimal price;

    @Enumerated( EnumType.STRING )
    private Department department;

    @Enumerated( EnumType.STRING )
    private LabtestDoneHere labtestDoneHere;

    private String description;

    @ManyToOne
    private SampleCollectingTube sampleCollectingTube;

    @OneToMany( mappedBy = "labTest", fetch = FetchType.EAGER )
    private List< InvoiceHasLabTest > invoiceHasLabTests;

    @ManyToMany( mappedBy = "labTests", fetch = FetchType.LAZY )
    private List< MedicalPackage > medicalPackages;

    @ManyToMany
    @JoinTable( name = "lab_test_has_parameter",
            joinColumns = @JoinColumn( name = "lab_test_id" ),
            inverseJoinColumns = @JoinColumn( name = "lab_test_parameter_id" ) )
    private List< LabTestParameter > labTestParameters;

}
