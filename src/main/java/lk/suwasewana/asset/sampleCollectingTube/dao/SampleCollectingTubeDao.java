package lk.suwasewana.asset.sampleCollectingTube.dao;


import lk.suwasewana.asset.sampleCollectingTube.entity.SampleCollectingTube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleCollectingTubeDao extends JpaRepository< SampleCollectingTube, Integer> {
}
