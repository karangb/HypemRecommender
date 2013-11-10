package com.hypemrecommender;

import com.hypemrecommender.representations.HypemTrackRepresentation;
import com.hypemrecommender.representations.HypemUserRepresentation;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 21:18
 */
public interface Visitor {
    public void visit(HypemUserRepresentation userRepresentation);
    public void visit(HypemTrackRepresentation trackRepresentation);
}
