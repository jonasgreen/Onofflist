package com.jg.onofflist.client.model;

import com.jg.core.client.model.EntityId;

/**
 *
 */
public class OnOffListId extends EntityId {

    public OnOffListId() {
    }

    public OnOffListId(String encodedKey) {
        super(encodedKey, "onOffListKey");
    }
}
