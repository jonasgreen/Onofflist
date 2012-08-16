package com.jg.onofflist.server.services;


import com.jg.core.client.appcontrol.ApplicationException;
import com.jg.core.client.service.EntityIdSingleFinder;
import com.jg.core.client.service.Finder;
import com.jg.core.client.service.SingleResult;
import com.jg.core.client.service.StringSingleFinder;
import com.jg.core.server.dao.CounterDao;
import com.jg.core.server.dao.PMF;
import com.jg.core.server.services.ActionHandler;
import com.jg.onofflist.client.model.CreateOnOffListAction;
import com.jg.onofflist.client.model.GetOnOffListAction;
import com.jg.onofflist.client.model.OnOffList;
import com.jg.onofflist.server.repository.CreateOnOffList;
import com.jg.onofflist.server.repository.ListItemRepository;
import com.jg.onofflist.server.repository.OnOffListRepository;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public class GetOnOffListHandler extends ActionHandler<GetOnOffListAction, SingleResult<OnOffList>> {

    private OnOffListRepository listRepos = new OnOffListRepository();
    private ListItemRepository itemRepos = new ListItemRepository();


    public SingleResult<OnOffList> execute(GetOnOffListAction action) throws ApplicationException {
        List<OnOffList> list = listRepos.findBy(new StringSingleFinder("url", action.getUrlId().trim()));

        if(list == null || list.isEmpty()){
            return new SingleResult<OnOffList>();
        }

        OnOffList model = list.get(0);
        model.setItems(itemRepos.findBy(new EntityIdSingleFinder(model.getId())));
        return new SingleResult<OnOffList>(model);
    }




    public Class<GetOnOffListAction> getActionType() {
        return GetOnOffListAction.class;
    }


}