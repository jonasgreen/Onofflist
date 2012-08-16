package com.jg.onofflist.server.repository;

import com.jg.core.server.dao.Dao;
import com.jg.core.server.repository.Repository;
import com.jg.onofflist.client.model.OnOffList;
import com.jg.onofflist.server.jdo.OnOffListJDO;

/**
 *
 */
public class OnOffListRepository extends Repository<OnOffListJDO, OnOffList, CreateOnOffList> {

    public OnOffListRepository(){
        this(new Dao(OnOffListJDO.class), new OnOffListConverter());
    }

    public OnOffListRepository(Dao dao, OnOffListConverter con) {
        super(dao, con);
    }

    public Class<OnOffList> getRepositoryType() {
        return OnOffList.class;
    }


}