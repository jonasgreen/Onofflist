package com.jg.onofflist.server.repository;

import com.google.appengine.api.datastore.Key;
import com.jg.core.server.repository.Converter;
import com.jg.onofflist.client.model.OnOffListItem;
import com.jg.onofflist.server.jdo.OnOffListItemJDO;

import java.util.Date;


/**
 *
 */
public class ListItemConverter extends Converter<OnOffListItemJDO, OnOffListItem, CreateListItem> {

    public OnOffListItemJDO convert(CreateListItem model) {
        if (model == null) {
            return null;
        }

        OnOffListItemJDO jdo = new OnOffListItemJDO();
        Key parent = KeyFac.convert(model.getParent());
        jdo.setOnOffListKey(parent);
        jdo.setKey(KeyFac.createOnOffListItem(parent, model.getId()));

        jdo.setName(model.getName().toLowerCase());
        jdo.setOn(model.getOn());

        jdo.setCreatedDate(new Date());
        jdo.setLastChangeDate(new Date());

        return jdo;
    }

    public OnOffListItem convert(OnOffListItemJDO jdo) {
        if (jdo == null) {
            return null;
        }

        OnOffListItem model = new OnOffListItem();
        model.setId(KeyFac.getOnOffListItemId(jdo.getKey()));

        model.setName(jdo.getName());
        model.setOn(jdo.getOn());

        model.setLastChangeDate(jdo.getLastChangeDate());
        model.setCreatedDate(jdo.getCreatedDate());

        return model;
    }

    @Override
    public void updateJDO(OnOffListItemJDO jdoOnOff, OnOffListItem model) {
        jdoOnOff.setName(model.getName());
        jdoOnOff.setOn(model.getOn());
        jdoOnOff.setLastChangeDate(new Date());
    }


}