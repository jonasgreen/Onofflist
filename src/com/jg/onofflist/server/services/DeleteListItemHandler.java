package com.jg.onofflist.server.services;


import com.jg.core.client.appcontrol.ApplicationException;
import com.jg.core.client.service.VoidResult;
import com.jg.core.server.dao.PMF;
import com.jg.core.server.services.ActionHandler;
import com.jg.onofflist.client.model.DeleteListItemAction;
import com.jg.onofflist.client.model.UpdateListItemAction;
import com.jg.onofflist.server.repository.ListItemRepository;

import java.util.ArrayList;

/**
 *
 */
public class DeleteListItemHandler extends ActionHandler<DeleteListItemAction, VoidResult> {

    private ListItemRepository repos = new ListItemRepository();

    @Override
        public VoidResult execute(DeleteListItemAction action) throws ApplicationException {


        try {
            PMF.startTransaction();
            repos.delete(action.getItem().getId());
            PMF.commitTransaction();
            return new VoidResult();
        }
        finally {
            PMF.endTransaction();
        }
    }




    @Override
    public Class<DeleteListItemAction> getActionType() {
        return DeleteListItemAction.class;
    }


}