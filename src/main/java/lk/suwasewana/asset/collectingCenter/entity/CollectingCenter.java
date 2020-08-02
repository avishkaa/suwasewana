package lk.suwasewana.asset.collectingCenter.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.suwasewana.asset.collectingCenter.entity.Enum.CollectingCenterStatus;
import lk.suwasewana.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "CollectingCenter" )
public class CollectingCenter extends AuditEntity {

    @NotEmpty
    private String name;

    @NotEmpty
    private String owner;

    @NotEmpty
    private String mobile;

    private String land;

    private String email;

    private String address;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate establishedDate;

    @Enumerated( EnumType.STRING )
    private CollectingCenterStatus collectingCenterStatus;


/*    @OneToMany
    @JoinColumn(name = "collecting_center_id")
    private List<Invoice> invoices = new ArrayList<>();*/

}
