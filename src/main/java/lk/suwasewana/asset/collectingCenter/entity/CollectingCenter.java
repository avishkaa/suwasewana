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
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "CollectingCenter" )
public class CollectingCenter extends AuditEntity {

    @Min( value = 1, message = "Should be need to include one character buddy !!" )
    private String name;

    @Min( value = 1, message = "Should be need to include one character buddy !!" )
    private String owner;

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
