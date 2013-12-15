package com.hypemrecommender.dal;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 14/12/2013
 * Time: 20:35
 */
public class CloudId {
    private final String id;

    public CloudId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CloudId that = (CloudId) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
