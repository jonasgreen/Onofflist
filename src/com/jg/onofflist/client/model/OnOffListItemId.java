package com.jg.onofflist.client.model;

import com.jg.core.client.model.EntityId;

/**
 *
 */
public class OnOffListItemId extends EntityId {

    private static final long serialVersionUID = -881095253911424240L;

    public OnOffListItemId() {
    }

    public OnOffListItemId(String encodedKey) {
        super(encodedKey, "onOffListItemKey");
    }
}
