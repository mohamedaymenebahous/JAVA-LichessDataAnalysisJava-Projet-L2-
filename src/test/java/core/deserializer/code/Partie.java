package core.deserializer.code;

import java.io.Serializable;

public class Partie implements Serializable {

    private String event;
    private String site;
    private Joueur white;
    private Joueur black;
    private String resultat;
    private DatePartie UTCDate;
    private String whiteElo;
    private String blackElo;
    private String eco;
    private Ouverture ouv;
    private String time;
    private String termination;
    private String play;
    private int nbCoups;

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setWhite(Joueur white) {
        this.white = white;
    }

    public Joueur getWhite() {
        return white;
    }

    public void setBlack(Joueur black) {
        this.black = black;
    }

    public Joueur getBlack() {
        return black;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getResultat() {
        return resultat;
    }

    public void setUTCDate(DatePartie uTCDate) {
        UTCDate = uTCDate;
    }

    public DatePartie getUTCDate() {
        return UTCDate;
    }

    public void setWhiteElo(String whiteElo) {
        this.whiteElo = whiteElo;
    }

    public String getWhiteElo() {
        return whiteElo;
    }

    public void setBlackElo(String blackElo) {
        this.blackElo = blackElo;
    }

    public String getBlackElo() {
        return blackElo;
    }

    public void setECO(String eco) {
        this.eco = eco;
    }

    public String getECO() {
        return eco;
    }

    public void setOuv(Ouverture ouv) {
        this.ouv = ouv;
    }

    public Ouverture getOuv() {
        return ouv;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTermination(String termination) {
        this.termination = termination;
    }

    public String getTermination() {
        return termination;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getPlay() {
        return play;
    }

    public void setNbCoups(int nbCoups) {
        this.nbCoups = nbCoups;
    }

    public int getNbCoups() {
        return nbCoups;
    }

    public int countNbCoups() {
        String p = this.getPlay();
        int out = 1;
        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '.') {
                out++;
            }
        }
        return out;
    }

    public String toString() {
        String p = "";
        p += "[Event \"" + this.getEvent() + "\"]\n";
        p += "[Site \"" + this.getSite() + "\"]\n";
        p += "[White \"" + this.getWhite().getName() + "\"]\n";
        p += "[Black \"" + this.getBlack().getName() + "\"]\n";
        p += "[Result \"" + this.getResultat() + "\"]\n";
        p += "[UTCDate \"" + this.getUTCDate().toString() + "\"]\n";
        p += "[WhiteElo \"" + this.getWhiteElo() + "\"]\n";
        p += "[BlackElo \"" + this.getBlackElo() + "\"]\n";
        p += "[ECO \"" + this.getOuv().getECO() + "\"]\n";
        p += "[Opening \"" + this.getOuv().getName() + "\"]\n";
        p += "[TimeControl \"" + this.getTime() + "\"]\n";
        p += "[Termination \"" + this.getTermination() + "\"]\n\n";
        p += this.getPlay() + "";
        return p;
    }

    public Partie() {
        this.white = new Joueur();
        this.black = new Joueur();
        this.ouv = new Ouverture();
        this.UTCDate = new DatePartie();
    }

}
