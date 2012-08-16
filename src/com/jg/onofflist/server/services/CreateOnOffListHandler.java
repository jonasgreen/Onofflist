package com.jg.onofflist.server.services;


import com.jg.core.client.appcontrol.ApplicationException;
import com.jg.core.client.service.SingleResult;
import com.jg.core.server.dao.CounterDao;
import com.jg.core.server.dao.PMF;
import com.jg.core.server.services.ActionHandler;
import com.jg.onofflist.server.repository.CreateOnOffList;
import com.jg.onofflist.client.model.CreateOnOffListAction;
import com.jg.onofflist.client.model.OnOffList;
import com.jg.onofflist.server.repository.OnOffListRepository;

import java.util.ArrayList;

/**
 *
 */
public class CreateOnOffListHandler extends ActionHandler<CreateOnOffListAction, SingleResult<OnOffList>> {

    private OnOffListRepository repos = new OnOffListRepository();

    public SingleResult<OnOffList> execute(CreateOnOffListAction action) throws ApplicationException {

        long listId = getNextListId();

        try {
            PMF.startTransaction();

            CreateOnOffList cr = new CreateOnOffList(listId);
            OnOffList onOffList = repos.create(cr);
            PMF.commitTransaction();

            SingleResult<OnOffList> result = new SingleResult<OnOffList>();
            result.setResult(onOffList);
            return result;
        }
        finally {
            PMF.endTransaction();
        }
    }


    private long getNextListId() {
        try {
            PMF.startTransaction();
            long next = new CounterDao().next(repos);
            PMF.commitTransaction();
            return next;
        }
        finally {
            PMF.endTransaction();
        }
    }



    public Class<CreateOnOffListAction> getActionType() {
        return CreateOnOffListAction.class;
    }


}