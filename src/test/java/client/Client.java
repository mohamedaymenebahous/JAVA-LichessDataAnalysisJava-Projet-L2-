package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import core.deserializer.code.*;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 4990)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(in);
            String str = "";
            while((str = reader.readLine()) != null) {
                System.out.println(str);
            };
            try (Scanner scan = new Scanner(System.in)) {
                String input = "";
                do {
                    input = scan.nextLine();
                } while(input.equals(""));
                writer.println(input);
                writer.flush();
            }
        }
    }
}