package com.jg.onofflist.server.repository;

import com.jg.core.server.dao.Dao;
import com.jg.core.server.repository.Repository;
import com.jg.onofflist.client.model.OnOffListItem;
import com.jg.onofflist.server.jdo.OnOffListItemJDO;

/**
 *
 */
public class ListItemRepository extends Repository<OnOffListItemJDO, OnOffListItem, CreateListItem> {

    public ListItemRepository(){
        this(new Dao(OnOffListItemJDO.class), new ListItemConverter());
    }

     public ListItemRepository(Dao dao, ListItemConverter con) {
        super(dao, con);
    }

    @Override
    public Class<OnOffListItem> getRepositoryType() {
        return OnOffListItem.class;
    }
}