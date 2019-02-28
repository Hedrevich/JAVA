package com.leverx.leverxspringproj.service;

import com.leverx.leverxspringproj.dao.ODataDao;
import com.leverx.leverxspringproj.domain.OData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ODataService {

    @Autowired
    private ODataDao odataDao;

    public List<OData> getAllSuppliers(String destinationName){
        return odataDao.getAll(destinationName);
    }

}
