package com.hypemrecommender;


/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 10/11/2013
 * Time: 21:17
 */
public interface Visitable {
    public void accept(Visitor visitor);
}
