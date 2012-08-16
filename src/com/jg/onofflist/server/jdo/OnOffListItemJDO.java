package com.jg.onofflist.server.jdo;

import com.google.appengine.api.datastore.Key;
import com.jg.codegen.PathParent;
import com.jg.codegen.Ref;
import com.jg.core.server.dao.JDO;

import javax.jdo.annotations.*;

/**
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class OnOffListItemJDO extends JDO {

    @PathParent(from = {OnOffListJDO.class})
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;


    @Persistent
    @Ref(to = OnOffListJDO.class)
    private Key onOffListKey;


    @Persistent
    private String name;

    @Persistent
    private Boolean on;

    public OnOffListItemJDO() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }

    public Key getOnOffListKey() {
        return onOffListKey;
    }

    public void setOnOffListKey(Key onOffListKey) {
        this.onOffListKey = onOffListKey;
    }


    @Override
    public Key getKey() {
        return key;
    }

    @Override
    public void setKey(Key key) {
        this.key = key;
    }
}