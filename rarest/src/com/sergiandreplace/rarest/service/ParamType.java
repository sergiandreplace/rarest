package com.sergiandreplace.rarest.service;

/**
 * Created with IntelliJ IDEA.
 * User: Sergi
 * Date: 22/07/13
 * Time: 00:24
 * To change this template use File | Settings | File Templates.
 */
public enum ParamType {
    rest ("rest"),
    query ("query"),
    body ("body"),
    header ("header");

    String name;

    ParamType(String name) {
        this.name=name;
    }

    public static ParamType getByName(String name) {
        for (ParamType type:values()) {
            if(type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }

        return null;
    }
}
