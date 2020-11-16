package lk.suwasewana.asset.discountRatio.dao;

import lk.suwasewana.asset.discountRatio.entity.DiscountRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface DiscountRatioDao extends JpaRepository< DiscountRatio, Integer > {
}
