package lk.suwasewana.asset.sampleCollectingTube.service;


import lk.suwasewana.asset.sampleCollectingTube.dao.SampleCollectingTubeDao;
import lk.suwasewana.asset.sampleCollectingTube.entity.SampleCollectingTube;
import lk.suwasewana.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SampleCollectingTubeService implements AbstractService< SampleCollectingTube, Integer> {

    private SampleCollectingTubeDao sampleCollectingTubeDao;
    @Autowired
    public SampleCollectingTubeService(SampleCollectingTubeDao sampleCollectingTubeDao){
        this.sampleCollectingTubeDao = sampleCollectingTubeDao;
    }


    @Cacheable(value = "SampleCollectingTube")
    public List< SampleCollectingTube > findAll() {
        return sampleCollectingTubeDao.findAll();
    }


    public SampleCollectingTube findById(Integer id) {
        return sampleCollectingTubeDao.getOne(id);
    }

    @Transactional
    public SampleCollectingTube persist(SampleCollectingTube sampleCollectingTube) {
        return sampleCollectingTubeDao.save(sampleCollectingTube);
    }

    @Transactional
    public boolean delete(Integer id) {
        sampleCollectingTubeDao.deleteById(id);
        return false;
    }


    public List< SampleCollectingTube > search(SampleCollectingTube sampleCollectingTube) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example< SampleCollectingTube > sampleCollectingTubeExample = Example.of(sampleCollectingTube, matcher);
        return sampleCollectingTubeDao.findAll(sampleCollectingTubeExample);
    }
}
