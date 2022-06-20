package core.serializer.code;

import java.io.Serializable;
import java.util.ArrayList;

public class Joueur implements Serializable {

    private String name;
    private ArrayList<Partie> parties = null;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Partie> getParties() {
        return parties;
    }

    public Joueur() {
        this.setName("");
        this.parties = new ArrayList<Partie>();
    }

}