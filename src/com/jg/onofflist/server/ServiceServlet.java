package com.jg.onofflist.server;

import com.jg.core.server.CoreServlet;
import com.jg.core.server.services.ActionHandlerRegistry;
import com.jg.onofflist.server.repository.ListItemRepository;
import com.jg.onofflist.server.repository.OnOffListRepository;
import com.jg.onofflist.server.services.*;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServiceServlet extends CoreServlet {



    @Override
    protected void init(ActionHandlerRegistry registry) {
        registry.add(new CreateOnOffListHandler());
        registry.add(new CreateListItemHandler());
        registry.add(new GetOnOffListHandler());
        registry.add(new UpdateListItemHandler());
        registry.add(new DeleteListItemHandler());

        //Reps for generic methods
        registry.add(new OnOffListRepository());
        registry.add(new ListItemRepository());

    }
}
