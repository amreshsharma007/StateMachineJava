package com.aks007;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.Transient;

//@MappedSuperclass
public abstract class StateBaseEntity<State, Child> extends BaseEntity<Child> {

    public abstract State getStatus();

    public abstract Child setStatus(State status);

    @JsonIgnore
    @Transient
    public abstract Child getThis();

}
