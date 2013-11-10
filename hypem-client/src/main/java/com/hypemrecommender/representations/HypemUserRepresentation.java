package com.hypemrecommender.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 07/11/2013
 * Time: 20:55
 */
@SuppressWarnings("ALL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class HypemUserRepresentation {


    @JsonProperty("@name")
    public String name;

    @JsonProperty("@o_p")
    public ObsessedRepresentation obsessed;

    public HypemUserRepresentation()
    {
        //EMPTY
    }

    public HypemUserRepresentation(final String name, final ObsessedRepresentation obsessed) {
        this.name = name;
        this.obsessed = obsessed;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ObsessedRepresentation getObsessed() {
        return obsessed;
    }

    public void setObsessed(final ObsessedRepresentation obsessed) {
        this.obsessed = obsessed;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final HypemUserRepresentation hypemUser = (HypemUserRepresentation) o;

        if (name != null ? !name.equals(hypemUser.name) : hypemUser.name != null) return false;
        return !(obsessed != null ? !obsessed.equals(hypemUser.obsessed) : hypemUser.obsessed != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (obsessed != null ? obsessed.hashCode() : 0);
        return result;
    }
}
