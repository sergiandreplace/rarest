package com.sergiandreplace.rarest.service;

public enum HttpVerb {
    get ("get",false),
    post ("post",true),
    put ("put",true),
    delete ("delete",false),
    patch ("patch",true),
    head ("head",false);

    public String name;
    public Boolean hasBody;

    HttpVerb(String name, boolean hasBody) {
        this.name=name;
        this.hasBody=hasBody;
    }

    public static HttpVerb getByName(String name) {
        for (HttpVerb type:values()) {
            if(type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }

        return null;
    }
}