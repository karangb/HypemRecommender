package com.hypemrecommender.dal;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 14/12/2013
 * Time: 20:35
 */
public class CloudId {
    private final int id;

    public CloudId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString()
    {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CloudId cloudId = (CloudId) o;

        if (id != cloudId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
