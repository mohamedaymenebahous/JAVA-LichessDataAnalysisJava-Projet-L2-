package core.deserializer.code;

import java.io.Serializable;

public class Ouverture implements Serializable {

    private String eco;
    private String name;

    public void setECO(String eco) {
        this.eco = eco;
    }

    public String getECO() {
        return eco;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
