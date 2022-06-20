package core.deserializer.code;

public class MainDeserialize {
    public static void main(String[] args) {
        try {
            Deserialize deserializer = new Deserialize();
            deserializer.getPartiesJoueurSpec("pseudoknight");
            deserializer.getPartiesJoueurSpec("karimov");
            deserializer.getCinqOuv();
            deserializer.getJoueursActifs();
            deserializer.getPartiesCourtes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
