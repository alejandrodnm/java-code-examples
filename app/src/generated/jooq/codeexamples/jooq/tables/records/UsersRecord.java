/*
 * This file is generated by jOOQ.
 */
package codeexamples.jooq.tables.records;


import codeexamples.jooq.tables.Users;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsersRecord extends UpdatableRecordImpl<UsersRecord> implements Record3<UUID, String, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.users.id</code>.
     */
    public UsersRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.users.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.users.name</code>.
     */
    public UsersRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.users.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.users.created_at</code>.
     */
    public UsersRecord setCreatedAt(OffsetDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.users.created_at</code>.
     */
    public OffsetDateTime getCreatedAt() {
        return (OffsetDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, String, OffsetDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, String, OffsetDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Users.USERS.ID;
    }

    @Override
    public Field<String> field2() {
        return Users.USERS.NAME;
    }

    @Override
    public Field<OffsetDateTime> field3() {
        return Users.USERS.CREATED_AT;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public OffsetDateTime component3() {
        return getCreatedAt();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public OffsetDateTime value3() {
        return getCreatedAt();
    }

    @Override
    public UsersRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public UsersRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public UsersRecord value3(OffsetDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public UsersRecord values(UUID value1, String value2, OffsetDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UsersRecord
     */
    public UsersRecord() {
        super(Users.USERS);
    }

    /**
     * Create a detached, initialised UsersRecord
     */
    public UsersRecord(UUID id, String name, OffsetDateTime createdAt) {
        super(Users.USERS);

        setId(id);
        setName(name);
        setCreatedAt(createdAt);
    }

    /**
     * Create a detached, initialised UsersRecord
     */
    public UsersRecord(codeexamples.jooq.tables.pojos.Users value) {
        super(Users.USERS);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setCreatedAt(value.getCreatedAt());
        }
    }
}
