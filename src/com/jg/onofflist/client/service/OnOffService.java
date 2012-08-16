package com.jg.onofflist.client.service;

import com.jg.core.client.CoreServerService;
import com.jg.core.client.service.CallBack;
import com.jg.core.client.service.SingleResult;
import com.jg.core.client.service.VoidResult;
import com.jg.onofflist.client.model.*;

/**
 *
 */
public class OnOffService extends CoreServerService {

    private static OnOffService instance;

    private OnOffService() {
    }

    public static OnOffService getInstance() {
        if (instance == null) {
            instance = new OnOffService();
        }
        return instance;
    }

    public void createOnOffList(CallBack<SingleResult<OnOffList>> cb) {
        executeWithRetry(new CreateOnOffListAction(), cb);
    }

    public void getOnOffList(String urlId, CallBack<SingleResult<OnOffList>> cb) {
        executeWithRetry(new GetOnOffListAction(urlId), cb);

    }

    public void createListItem(OnOffListId parentId, String name, CallBack<SingleResult<OnOffListItem>> cb) {
        executeWithRetry(new CreateListItemAction(parentId, name, true), cb);
    }

    public void updateListItem(OnOffListItem item, CallBack<VoidResult> cb) {
        executeWithRetry(new UpdateListItemAction(item), cb);
    }

    public void deleteItem(OnOffListItem item, CallBack<VoidResult> cb){
        executeWithRetry(new DeleteListItemAction(item), cb);
    }


}
