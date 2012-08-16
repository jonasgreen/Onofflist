package com.jg.onofflist.server.repository;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.jg.core.client.appcontrol.SystemException;
import com.jg.core.client.model.EntityId;
import com.jg.onofflist.client.model.OnOffListId;
import com.jg.onofflist.client.model.OnOffListItem;
import com.jg.onofflist.client.model.OnOffListItemId;
import com.jg.onofflist.server.jdo.OnOffListItemJDO;
import com.jg.onofflist.server.jdo.OnOffListJDO;

/**
 *
 */
public class KeyFac {

    private KeyFac() {
        super();
    }


    public static Key convert(EntityId id) {
        if(id == null){
            throw new SystemException("EntityId is Null");
        }
        return KeyFactory.stringToKey(id.getEncodedKey());
    }

    //************************//
    // ONOFFLIST
    //************************//            

    public static Key createOnOffList(long id) {
        return createKey(OnOffListJDO.class, id);
    }

    public static OnOffListId getOnOffListId(Key key) {
        validate(key, OnOffListJDO.class);
        return new OnOffListId( KeyFactory.keyToString(key));
    }


    //************************//
    // ONOFFLIST ITEM
    //************************//

    public static Key createOnOffListItem(Key parent, long id) {
        return createChild(parent, OnOffListItemJDO.class, id);
    }

    public static OnOffListItemId getOnOffListItemId(Key key) {
        validate(key, OnOffListItemJDO.class);
        return new OnOffListItemId( KeyFactory.keyToString(key));
    }




    private static void validate(Key key, Class kind) {
        if (!key.getKind().equals(kind.getSimpleName())) {
            throw new SystemException("Key validation error. Key:" + key + " kind: " + kind.getSimpleName());
        }
    }

    private static Key createKey(Class cl, long id) {
        return KeyFactory.createKey(cl.getSimpleName(), id);

    }

    private static Key createChild(Key parent, Class cl, long id) {
        return new KeyFactory.Builder(parent).addChild(cl.getSimpleName(), id).getKey();
    }

    private static Key createChild(Key parent, Class cl, String id) {
        return new KeyFactory.Builder(parent).addChild(cl.getSimpleName(), id).getKey();

    }

    public static Key stringToKey(String encodedKey) {
        return KeyFactory.stringToKey(encodedKey);
    }
}
