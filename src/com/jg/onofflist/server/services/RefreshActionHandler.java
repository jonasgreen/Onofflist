package com.jg.onofflist.server.services;


import com.jg.core.client.appcontrol.ApplicationException;
import com.jg.core.client.service.LongSingleFinder;
import com.jg.core.client.service.SingleFinder;
import com.jg.core.client.service.StringSingleFinder;
import com.jg.core.client.service.VoidResult;
import com.jg.core.server.Logger;
import com.jg.core.server.dao.PMF;
import com.jg.core.server.services.ActionHandler;
import com.jg.onofflist.client.model.DeleteListItemAction;
import com.jg.onofflist.client.model.OnOffList;
import com.jg.onofflist.client.model.OnOffListItem;
import com.jg.onofflist.client.model.RefreshAction;
import com.jg.onofflist.server.repository.ListItemRepository;
import com.jg.onofflist.server.repository.OnOffListRepository;

import java.util.List;

/**
 *
 */
public class RefreshActionHandler extends ActionHandler<RefreshAction, VoidResult> {

    private OnOffListRepository listRepos = new OnOffListRepository();
    private ListItemRepository repos = new ListItemRepository();

    @Override
    public VoidResult execute(RefreshAction action) throws ApplicationException {

        try {
            List<OnOffList> list = listRepos.findBy(new StringSingleFinder("url", "ruals"));
            Logger.log("" + list);
        }
        catch (Exception e) {
            //ignore
        }

        try {
            List<OnOffListItem> by = repos.findBy(new StringSingleFinder("name", "idssss"));
            Logger.log("" + by);
            return new VoidResult();
        }
        catch (Exception e) {
            //ignore
        }
        return new VoidResult();
    }


    @Override
    public Class<RefreshAction> getActionType() {
        return RefreshAction.class;
    }


}