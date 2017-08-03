package com.qyihu.nicerom.repository;

import com.qyihu.nicerom.model.Rom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiceromRepository extends MongoRepository<Rom, String> {

    Rom findByLink(String link);
}
