package com.bedfordshire.helpmebackend.resource;

/**
 * @author Lakitha Prabudh
 */
public class HelpTypeResource {
    private String uuid;
    private String name;

    public HelpTypeResource() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HelpTypeResource{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
