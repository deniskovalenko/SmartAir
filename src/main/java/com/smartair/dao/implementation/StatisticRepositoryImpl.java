package com.smartair.dao.implementation;

import com.smartair.dao.CustomStatisticRepository;
import com.smartair.dao.StatisticRepository;
import com.smartair.model.entity.DeviceModel;
import com.smartair.model.entity.StatisticModel;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by denis on 12.03.15.
 */
@Repository
public class StatisticRepositoryImpl implements CustomStatisticRepository {
    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<StatisticModel> findByDateDescending(int page, int limit) {
        Query query = new Query();
        query.limit(limit);
        query.skip(limit*page);
        query.with(new Sort(Sort.Direction.DESC, "date"));

        return mongoOperations.find(query, StatisticModel.class);
    }

    @Override
    public List<StatisticModel> findByDevice(String deviceId, Date startDate, Date endDate, boolean dateSortDescending) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deviceId").is(deviceId));
        query.addCriteria(Criteria.where("date").gte(startDate).lt(endDate));
        if (dateSortDescending) {
            query.with(new Sort(Sort.Direction.DESC, "date"));
        } else {
            query.with(new Sort(Sort.Direction.ASC, "date"));
        }
        return mongoOperations.find(query, StatisticModel.class);
    }

    @Override
    public List<StatisticModel> findByDevice(String deviceId, int skip, int limit, boolean dateSortDescending) {
        Query query = new Query();
        query.addCriteria(Criteria.where("deviceId").is(deviceId));
        if (dateSortDescending) {
            query.with(new Sort(Sort.Direction.DESC, "date"));
        } else {
            query.with(new Sort(Sort.Direction.ASC, "date"));
        }
        query.skip(skip);
        query.limit(limit);
        return mongoOperations.find(query, StatisticModel.class);
    }
}
