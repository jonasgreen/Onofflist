package com.jg.onofflist.server.repository;

import com.jg.core.client.appcontrol.SystemException;
import com.jg.core.server.repository.Converter;
import com.jg.onofflist.client.model.OnOffList;
import com.jg.onofflist.server.jdo.OnOffListJDO;
import com.jg.onofflist.server.util.RandomPass;

import java.util.Date;


/**
 *
 */
public class OnOffListConverter extends Converter<OnOffListJDO, OnOffList, CreateOnOffList> {

    public OnOffListJDO convert(CreateOnOffList model) {
        if (model == null) {
            return null;
        }

        OnOffListJDO jdoOnOff = new OnOffListJDO();
        jdoOnOff.setUrl(model.getId()+ RandomPass.getUrl(4));
        jdoOnOff.setKey(KeyFac.createOnOffList(model.getId()));
        jdoOnOff.setCreatedDate(new Date());
        jdoOnOff.setLastChangeDate(new Date());
        return jdoOnOff;
    }

    public OnOffList convert(OnOffListJDO jdoOnOff) {
        if (jdoOnOff == null) {
            return null;
        }
        OnOffList model = new OnOffList();

        model.setId(KeyFac.getOnOffListId(jdoOnOff.getKey()));
        model.setLastChangeDate(jdoOnOff.getLastChangeDate());
        model.setCreatedDate(jdoOnOff.getCreatedDate());
        model.setUrl(jdoOnOff.getUrl());

        return model;
    }

    @Override
    public void updateJDO(OnOffListJDO jdoOnOff, OnOffList model) {
        throw new SystemException("updateJDO(Model m) not supported in "+this.getClass().getName());
    }


}