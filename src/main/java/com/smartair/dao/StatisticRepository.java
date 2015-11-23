package com.smartair.dao;

import com.smartair.model.entity.StatisticModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by denis on 14.04.15.
 */
@Repository
public interface StatisticRepository extends MongoRepository<StatisticModel, String>, CustomStatisticRepository {
}
