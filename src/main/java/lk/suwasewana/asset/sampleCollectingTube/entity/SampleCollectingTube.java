package lk.suwasewana.asset.sampleCollectingTube.entity;


import lk.suwasewana.asset.labTest.entity.LabTest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SampleCollectingTube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45, unique = true)
    private String name;

    @OneToMany(mappedBy = "sampleCollectingTube")
    private List< LabTest > labTests;


}