package core.serializer.code;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StreamTokenizer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

class TestAlternatif {

    private BufferedReader dataL = null;
    private StreamTokenizer streamT = null;
    //private FileOutputStream outputStream = null;
    //private ObjectOutputStream objectOutputStream = null;
    private Hashtable<String, ArrayList<Partie>> htJoueurs;
    private Hashtable<String, Integer> htOuvertures;
    private Hashtable<String, Integer> htJoueursActifs1; //active players on the 1st week
    private Hashtable<String, Integer> htJoueursActifs2; //active players on the 2nd week
    private Hashtable<String, Integer> htJoueursActifs3; //active players on the 3rd week
    private Hashtable<String, Integer> htJoueursActifs4; //active players on the 4th week
    private Hashtable<String, Integer> htJoueursActifs5; //active players on the 5th week
    private Hashtable<String, Integer> htPartiesCourtes;

    public BufferedReader getDataL() {
        return dataL;
    }

    public StreamTokenizer getStream() {
        return streamT;
    }

    public Hashtable<String, ArrayList<Partie>> getHTJoueurs() {
        return htJoueurs;
    }

    public Hashtable<String, Integer> getHTOuvertures() {
        return htOuvertures;
    }

    public Hashtable<String, Integer> getHtJoueursActifs1() {
        return htJoueursActifs1;
    }

    public Hashtable<String, Integer> getHtJoueursActifs2() {
        return htJoueursActifs2;
    }

    public Hashtable<String, Integer> getHtJoueursActifs3() {
        return htJoueursActifs3;
    }

    public Hashtable<String, Integer> getHtJoueursActifs4() {
        return htJoueursActifs4;
    }

    public Hashtable<String, Integer> getHtJoueursActifs5() {
        return htJoueursActifs5;
    }

    public Hashtable<String, Integer> getHtPartiesCourtes() {
        return htPartiesCourtes;
    }

    public TestAlternatif(String f) throws IOException {
        try {
            /* this.fStream = new FileInputStream(f);
            this.iStream= new InputStreamReader(fStream); */
            this.dataL = Files.newBufferedReader(Paths.get(f), StandardCharsets.UTF_8);
            this.streamT = new StreamTokenizer(dataL);
            this.htJoueurs = new Hashtable<>();
            this.htOuvertures = new Hashtable<>();
            this.htJoueursActifs1 = new Hashtable<>();
            this.htJoueursActifs2 = new Hashtable<>();
            this.htJoueursActifs3 = new Hashtable<>();
            this.htJoueursActifs4 = new Hashtable<>();
            this.htJoueursActifs5 = new Hashtable<>();
            this.htPartiesCourtes = new Hashtable<>();
            this.tokenize();
            this.SerializeAllHashtables();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void tokenize() throws IOException {
        //to do: don't put in the hashtable unless it matches the String parameter
        //to do: use st.lineno();
        try {
            StreamTokenizer s = this.getStream();
            Partie p;
            final int DOUBLE_QUOTE_CHARACTER = '"';
            String tmpSite = "";
            int tmpNbCoup = 0;
            int token = s.nextToken();
            boolean eof = false; //end of file
            p = new Partie();
            do {
                switch(token) {
                    case StreamTokenizer.TT_EOF :
                        eof = true;
                        break;
                    case StreamTokenizer.TT_WORD :
                        if(s.sval.equals("Event")) {
                            p = new Partie();
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                p.setEvent(s.sval);
                            }
                        } else if(s.sval.equals("Site")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                p.setSite(s.sval);
                                tmpSite = s.sval;
                            }
                        } else if(s.sval.equals("White")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                //check if the player's name is already in the hashtable
                                //before adding it to the hashtable
                                //new instance of a player means a new instance of arraylist
                                if(this.getHTJoueurs().containsKey(s.sval)) {
                                    p.getWhite().setName(s.sval);
                                    this.getHTJoueurs().get(s.sval).add(p);
                                } else {
                                    p.getWhite().setName(s.sval);
                                    p.getWhite().getParties().add(p);
                                    this.getHTJoueurs().put(p.getWhite().getName(), p.getWhite().getParties());
                                }
                            }
                        } else if(s.sval.equals("Black")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                //check if the player's name is already in the hashtable
                                //before adding it to the hashtable
                                //new instance of a player means a new instance of arraylist so don't do it again
                                if(this.getHTJoueurs().containsKey(s.sval)) {
                                    p.getBlack().setName(s.sval);
                                    this.getHTJoueurs().get(s.sval).add(p);
                                } else {
                                    p.getBlack().setName(s.sval);
                                    p.getBlack().getParties().add(p);
                                    this.getHTJoueurs().put(p.getBlack().getName(), p.getBlack().getParties());
                                }
                            }
                        } else if(s.sval.equals("Result")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                p.setResultat(s.sval);
                            }
                        } else if(s.sval.equals("UTCDate")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                p.setUTCDate(p.getUTCDate().splitDate(s.sval));
                                String day = p.getUTCDate().getDay();
                                int dayInt = Integer.parseInt(day);
                                if(dayInt == 31 || (dayInt >=  1 && dayInt < 7)) {
                                    if(this.getHtJoueursActifs1().containsKey(p.getBlack().getName())) {
                                        int nb = this.getHtJoueursActifs1().get(p.getBlack().getName());
                                        this.getHtJoueursActifs1().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs1().put(p.getBlack().getName(), 1);
                                    }
                                    if(this.getHtJoueursActifs1().containsKey(p.getWhite().getName())) {
                                        int nb = this.getHtJoueursActifs1().get(p.getBlack().getName());
                                        this.getHtJoueursActifs1().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs1().put(p.getBlack().getName(), 1);
                                    }
                                } else if(dayInt >=  7 && dayInt < 14) {
                                    if(this.getHtJoueursActifs2().containsKey(p.getBlack().getName())) {
                                        int nb = this.getHtJoueursActifs2().get(p.getBlack().getName());
                                        this.getHtJoueursActifs2().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs2().put(p.getBlack().getName(), 1);
                                    }
                                    if(this.getHtJoueursActifs2().containsKey(p.getWhite().getName())) {
                                        int nb = this.getHtJoueursActifs2().get(p.getBlack().getName());
                                        this.getHtJoueursActifs2().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs2().put(p.getBlack().getName(), 1);
                                    }
                                } else if(dayInt >=  14 && dayInt < 21) {
                                    if(this.getHtJoueursActifs3().containsKey(p.getBlack().getName())) {
                                        int nb = this.getHtJoueursActifs3().get(p.getBlack().getName());
                                        this.getHtJoueursActifs3().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs3().put(p.getBlack().getName(), 1);
                                    }
                                    if(this.getHtJoueursActifs3().containsKey(p.getWhite().getName())) {
                                        int nb = this.getHtJoueursActifs3().get(p.getBlack().getName());
                                        this.getHtJoueursActifs3().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs3().put(p.getBlack().getName(), 1);
                                    }
                                } else if(dayInt >=  21 && dayInt < 28) {
                                    if(this.getHtJoueursActifs4().containsKey(p.getBlack().getName())) {
                                        int nb = this.getHtJoueursActifs4().get(p.getBlack().getName());
                                        this.getHtJoueursActifs4().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs4().put(p.getBlack().getName(), 1);
                                    }
                                    if(this.getHtJoueursActifs4().containsKey(p.getWhite().getName())) {
                                        int nb = this.getHtJoueursActifs4().get(p.getBlack().getName());
                                        this.getHtJoueursActifs4().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs4().put(p.getBlack().getName(), 1);
                                    }
                                } else if(dayInt >=  28 && dayInt < 31) {
                                    if(this.getHtJoueursActifs5().containsKey(p.getBlack().getName())) {
                                        int nb = this.getHtJoueursActifs5().get(p.getBlack().getName());
                                        this.getHtJoueursActifs5().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs5().put(p.getBlack().getName(), 1);
                                    }
                                    if(this.getHtJoueursActifs5().containsKey(p.getWhite().getName())) {
                                        int nb = this.getHtJoueursActifs5().get(p.getBlack().getName());
                                        this.getHtJoueursActifs5().put(p.getBlack().getName(), nb + 1);
                                    } else {
                                        this.getHtJoueursActifs5().put(p.getBlack().getName(), 1);
                                    }
                                }
                            }
                        } else if(s.sval.equals("WhiteElo")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                p.setWhiteElo(s.sval);
                            }
                        } else if(s.sval.equals("BlackElo")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                p.setBlackElo(s.sval);
                            }
                        } else if(s.sval.equals("ECO")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                p.getOuv().setECO(s.sval);
                            }
                        } else if(s.sval.equals("Opening")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                /* op.setName(s.sval);
                                p.setOuv(op); */
                                if(this.getHTOuvertures().containsKey(s.sval)) {
                                    p.getOuv().setName(s.sval);
                                    int nb = this.getHTOuvertures().get(s.sval);
                                    this.getHTOuvertures().put(s.sval, nb + 1);
                                } else {
                                    p.getOuv().setName(s.sval);
                                    this.getHTOuvertures().put(s.sval, 1);
                                }
                            }
                        } else if(s.sval.equals("TimeControl")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                String timecontrol = s.sval;
                                p.setTime(timecontrol);
                            }
                        } else if(s.sval.equals("Termination")) {
                            token = s.nextToken();
                            if(token == DOUBLE_QUOTE_CHARACTER) {
                                p.setTermination(s.sval);
                            }
                        }
                    case StreamTokenizer.TT_NUMBER:
                        p.setPlay("1. " + this.getDataL().readLine());
                        p.setNbCoups(p.countNbCoups());
                        tmpNbCoup = p.getNbCoups();
                        this.htPartiesCourtes.put(tmpSite, tmpNbCoup);
                }
                token = s.nextToken();
            } while( !eof );
        } finally {
            if(dataL != null) {
                dataL.close();
            }
        }
    }

    public void getPartiesJoueurSpec(String Joueur) throws IOException {
        FileWriter fileWriter = new FileWriter("../output/PartiesJoueur" + Joueur + ".txt", false);
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
        printWriter.println("--- Player \"" + Joueur + "\" found! ---");
        printWriter.println("--- " + this.getHTJoueurs().get(Joueur).size() +  " games found! ---\n");
        for(int j = 0; j < this.getHTJoueurs().get(Joueur).size(); j++) {
            printWriter.println(this.getHTJoueurs().get(Joueur).get(j).toString() + "\n");
        }
        printWriter.close();
        fileWriter.close();
    }

    public void sortOpenings(Hashtable<?, Integer> t) throws IOException {
        ArrayList<Map.Entry<?, Integer>> l = new ArrayList(t.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<?, Integer>>() {
            public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        FileWriter fileWriter = new FileWriter("../output/CinqOuv.txt", false);
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
        printWriter.println("The most played Openings:\n");
        for(int i = l.size() - 1; i >= (l.size() - 5); i--) {
            printWriter.println(l.get(i));
        }
        printWriter.close();
        fileWriter.close();
    }

    public void getCinqOuv() throws IOException {
        sortOpenings(this.getHTOuvertures());
    }

    public void sortJoueurActifs(Hashtable<?, Integer> t, int s) throws IOException {
        ArrayList<Map.Entry<?, Integer>> l = new ArrayList(t.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<?, Integer>>() {
            public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        FileWriter fileWriter = new FileWriter("../output/JoueursActifsWeek" + s, false);
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
        printWriter.println("The most active players in the week no: " + s + " of this month:\n");
        for(int i = l.size() - 1; i >= (l.size() - 10); i--) {
            printWriter.println(l.get(i));
        }
        printWriter.close();
        fileWriter.close();
    }

    public void getJoueursActifs() throws IOException {
        sortJoueurActifs(this.getHtJoueursActifs1(), 1);
        sortJoueurActifs(this.getHtJoueursActifs2(), 2);
        sortJoueurActifs(this.getHtJoueursActifs3(), 3);
        sortJoueurActifs(this.getHtJoueursActifs4(), 4);
        sortJoueurActifs(this.getHtJoueursActifs5(), 5);
    }

    public void sortPartiesCourtes(Hashtable<?, Integer> t) throws IOException {
        ArrayList<Map.Entry<?, Integer>> l = new ArrayList(t.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<?, Integer>>() {
            public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        FileWriter fileWriter = new FileWriter("../output/PartiesCourtes.txt", false);
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
        printWriter.println("The shortest games:\n");
        for(int i = 0; i < 10; i++) {
            String str = l.get(i) + "";
            String str1 = "";
            for(int j = 0; j < 28; j++) {
                str1 += str.charAt(j);
            }
            printWriter.println(str1);
        }
        printWriter.close();
        fileWriter.close();
    }

    public void getPartiesCourtes() throws IOException {
        sortPartiesCourtes(this.getHtPartiesCourtes());
    }

    public void Serialize(Hashtable<?, ?> ht, String FileName) throws IOException {
        FileOutputStream myFileOutStream = new FileOutputStream("../serFiles/" + FileName + ".ser");
        ObjectOutputStream myObjectOutStream = new ObjectOutputStream(myFileOutStream);
        myObjectOutStream.writeObject(ht);
        myObjectOutStream.close();
        myFileOutStream.close();
    }

    public void SerializeAllHashtables() throws IOException {
        this.Serialize(this.getHTJoueurs(), "htJoueurs");
        this.Serialize(this.getHTOuvertures(), "htOuvertures");
        this.Serialize(this.getHtJoueursActifs1(), "htJoueursActifs1");
        this.Serialize(this.getHtJoueursActifs2(), "htJoueursActifs2");
        this.Serialize(this.getHtJoueursActifs3(), "htJoueursActifs3");
        this.Serialize(this.getHtJoueursActifs4(), "htJoueursActifs4");
        this.Serialize(this.getHtJoueursActifs5(), "htJoueursActifs5");
        this.Serialize(this.getHtPartiesCourtes(), "htPartiesCourtes");
    }

}
