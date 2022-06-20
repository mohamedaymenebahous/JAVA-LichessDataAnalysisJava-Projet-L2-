package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import core.deserializer.code.*;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket ss = new ServerSocket(4990)) {
            Socket s = ss.accept();
            System.out.println("Client connected");
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader br = new BufferedReader(in);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            pw.println(
                    "------------------Client connected------------------\n" +
                            "--------------LiChess Data Exploration--------------\n" +
                            "1-Trouver toutes les parties d'un joueur\n" +
                            "2-Trouver les 5 ouvertures les plus jouees\n"+
                            "3-Trouver les parties les plus courtes\n" +
                            "4-Lister les joueurs les plus actifs sur une semaine\n" +
                            "-----------------------Choose-----------------------"
            );
            pw.flush();
            String str = br.readLine();
            System.out.println("Client input:" + str);

            try {
                Deserialize deserializer = new Deserialize();
                switch (str) {
                    case "1": {
                        pw.println("Enter username: ");
                        String str1 = br.readLine();
                        System.out.println("Client input:" + str1);
                        deserializer.getPartiesJoueurSpec(str1);
                    }
                    break;
                    case "2": {
                        deserializer.getCinqOuv();
                    }
                    break;
                    case "3": {
                        deserializer.getJoueursActifs();
                    }
                    break;
                    case "4": {
                        deserializer.getPartiesCourtes();
                    }
                    break;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            pw.close();
            br.close();
            in.close();
        }
    }
}



/*
pw.println(
            "-----------------Client connected-----------------\n" +
            "-------------LiChess Data Exploration-------------\n" +
            "1-Trouver toutes les parties d'un joueur\n" +
            "2-Trouver les 5 ouvertures les plus jouees\n"+
            "3-Trouver les parties les plus courtes\n" +
            "4-Lister les joueurs les plus actifs sur une semaine\n"
        );
*/