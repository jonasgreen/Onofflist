package com.jg.onofflist.server.services;


import com.jg.core.client.appcontrol.ApplicationException;
import com.jg.core.client.service.SingleResult;
import com.jg.core.server.dao.CounterDao;
import com.jg.core.server.dao.PMF;
import com.jg.core.server.services.ActionHandler;
import com.jg.onofflist.client.model.CreateListItemAction;
import com.jg.onofflist.client.model.OnOffListItem;
import com.jg.onofflist.server.repository.CreateListItem;
import com.jg.onofflist.server.repository.CreateOnOffList;
import com.jg.onofflist.client.model.CreateOnOffListAction;
import com.jg.onofflist.client.model.OnOffList;
import com.jg.onofflist.server.repository.ListItemRepository;
import com.jg.onofflist.server.repository.OnOffListRepository;

import java.util.ArrayList;

/**
 *
 */
public class CreateListItemHandler extends ActionHandler<CreateListItemAction, SingleResult<OnOffListItem>> {

    private ListItemRepository repos = new ListItemRepository();

    @Override
        public SingleResult<OnOffListItem> execute(CreateListItemAction action) throws ApplicationException {
        long nextId = getNextListId();

        try {
            PMF.startTransaction();

            CreateListItem cr = new CreateListItem(action.getParentId(), action.getName(), action.getOn(), nextId );
            OnOffListItem item = repos.create(cr);
            PMF.commitTransaction();

            SingleResult<OnOffListItem> result = new SingleResult<OnOffListItem>();
            result.setResult(item);
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


    @Override
    public Class<CreateListItemAction> getActionType() {
        return CreateListItemAction.class;
    }


}