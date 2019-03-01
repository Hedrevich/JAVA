package com.leverx.leverxspringproj.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.leverx.leverxspringproj.domain.OData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryResult;

@Repository
public class ODataDao {
    private static final Logger logger = LoggerFactory.getLogger(ODataDao.class);
    private final String SERVICE_PATH = "/V2/OData/OData.svc/";
    private final String SERVICE_ENTITY = "Suppliers";

    public List<OData> getAll(String destinationName) {
        List<OData> SupplierList = new ArrayList<>();
        try {
            ODataQueryResult result = ODataQueryBuilder.withEntity(SERVICE_PATH, SERVICE_ENTITY)
            .expand("Products")
            .select("ID", "Name", "Address")
            .build().execute(destinationName);
            List<Map<String, Object>> listMap;
            listMap = result.asListOfMaps();
            logger.error("ODATA: " + getODataList(listMap));
            return  getODataList(listMap);
        } catch (ODataException e) {
            logger.error("Error while trying to get list of entities: " + e.getMessage());
        }
        return SupplierList;
    }

    private List<OData> getODataList(List<Map<String, Object>> listMap){
        List<OData> suppliersList = new ArrayList<>();
        listMap.forEach(item -> {
            OData supplier = new OData();
            supplier.setId(Integer.parseInt(item.get("ID").toString()));
            supplier.setName(item.get("Name").toString());
            supplier.setAddress(item.get("Address").toString());
            suppliersList.add(supplier);
        });
        return suppliersList;
    }
}
