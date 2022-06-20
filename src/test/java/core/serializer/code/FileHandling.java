package core.serializer.code;

public class FileHandling {
    public static void main(String[] args) {
        try {
            TestAlternatif testAlternatif = new TestAlternatif("../Sources/LichessData.pgn"); //fait la serialisation
            //Parcourir parcourir = new Parcourir("../Sources/LichessData.pgn"); //sans serialisaion
            /* parcourir.getPartiesJoueurSpec("pseudoknight");
            parcourir.getPartiesJoueurSpec("karimov");
            parcourir.getCinqOuv();
            parcourir.getJoueursActifs();
            parcourir.getPartiesCourtes(); */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

