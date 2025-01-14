package com.aks007;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@Accessors(chain = true)
public abstract class BaseEntity<Child> implements Serializable {

    @Serial
    private static final long serialVersionUID = 9199084043007604682L;

    @Id
    @Column(unique = true, updatable = false)
    @Access(AccessType.PROPERTY)
    protected String id;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected Date createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected Date updatedAt;

    public Child setId(String id) {

        this.id = id;
        return getThis();
    }

    // This column is not needed in all the table
    // protected boolean deleted = false;

    @JsonIgnore
    protected abstract Child getThis();

    public Child setCreatedAt(Date createdAt) {

        this.createdAt = createdAt;
        return getThis();
    }

    public Child setUpdatedAt(Date updatedAt) {

        this.updatedAt = updatedAt;
        return getThis();
    }

}
