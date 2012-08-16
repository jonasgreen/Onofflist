package com.jg.onofflist.server.services;


import com.jg.core.client.appcontrol.ApplicationException;
import com.jg.core.client.service.SingleResult;
import com.jg.core.client.service.VoidResult;
import com.jg.core.server.dao.CounterDao;
import com.jg.core.server.dao.PMF;
import com.jg.core.server.services.ActionHandler;
import com.jg.onofflist.client.model.CreateListItemAction;
import com.jg.onofflist.client.model.OnOffListItem;
import com.jg.onofflist.client.model.UpdateListItemAction;
import com.jg.onofflist.server.repository.CreateListItem;
import com.jg.onofflist.server.repository.ListItemRepository;

import java.util.ArrayList;

/**
 *
 */
public class UpdateListItemHandler extends ActionHandler<UpdateListItemAction, VoidResult> {

    private ListItemRepository repos = new ListItemRepository();

    @Override
        public VoidResult execute(UpdateListItemAction action) throws ApplicationException {
        try {
            PMF.startTransaction();
            repos.update(action.getItem());
            PMF.commitTransaction();
            return new VoidResult();
        }
        finally {
            PMF.endTransaction();
        }
    }




    @Override
    public Class<UpdateListItemAction> getActionType() {
        return UpdateListItemAction.class;
    }


}