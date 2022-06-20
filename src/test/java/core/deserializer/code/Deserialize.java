package core.deserializer.code;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class Deserialize {

    private Hashtable<String, ArrayList<Partie>> htJoueurs;
    private Hashtable<String, Integer> htOuvertures;
    private Hashtable<String, Integer> htJoueursActifs1; //active players on the 1st week
    private Hashtable<String, Integer> htJoueursActifs2; //active players on the 2nd week
    private Hashtable<String, Integer> htJoueursActifs3; //active players on the 3rd week
    private Hashtable<String, Integer> htJoueursActifs4; //active players on the 4th week
    private Hashtable<String, Integer> htJoueursActifs5; //active players on the 5th week
    private Hashtable<String, Integer> htPartiesCourtes;

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

    public Deserialize() throws IOException, ClassNotFoundException {
        this.htOuvertures = null;
        this.htJoueursActifs1 = null;
        this.htJoueursActifs2 = null;
        this.htJoueursActifs3 = null;
        this.htJoueursActifs4 = null;
        this.htJoueursActifs5 = null;
        this.htPartiesCourtes = null;
        this.DeserializeAllHashtables();
    }

    public void DeserializeHTJoueur() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("../../serializer/serfiles/htJoueurs.ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        this.htJoueurs = (Hashtable) objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }

    public void DeserializeHTOuvertures() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("../../serializer/serfiles/htOuvertures.ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        this.htOuvertures = (Hashtable) objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }

    public void DeserializeHTJoueursActifs1() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("../../serializer/serfiles/htJoueursActifs1.ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        this.htJoueursActifs1 = (Hashtable) objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }

    public void DeserializeHTJoueursActifs2() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("../../serializer/serfiles/htJoueursActifs2.ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        this.htJoueursActifs2 = (Hashtable) objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }

    public void DeserializeHTJoueursActifs3() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("../../serializer/serfiles/htJoueursActifs3.ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        this.htJoueursActifs3 = (Hashtable) objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }

    public void DeserializeHTJoueursActifs4() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("../../serializer/serfiles/htJoueursActifs4.ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        this.htJoueursActifs4 = (Hashtable) objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }

    public void DeserializeHTJoueursActifs5() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("../../serializer/serfiles/htJoueursActifs5.ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        this.htJoueursActifs5 = (Hashtable) objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }

    public void DeserializeHTPartiesCourtes() throws IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("../../serializer/serfiles/htPartiesCourtes.ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        this.htPartiesCourtes = (Hashtable) objectInput.readObject();
        objectInput.close();
        fileInput.close();
    }

    public void DeserializeAllHashtables() throws IOException, ClassNotFoundException {
        this.DeserializeHTJoueur();
        this.DeserializeHTOuvertures();
        this.DeserializeHTJoueursActifs1();
        this.DeserializeHTJoueursActifs2();
        this.DeserializeHTJoueursActifs3();
        this.DeserializeHTJoueursActifs4();
        this.DeserializeHTJoueursActifs5();
        this.DeserializeHTPartiesCourtes();
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

}
