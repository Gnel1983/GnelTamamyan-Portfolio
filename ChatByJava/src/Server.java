
import java.io.*;
import java.net.*;


public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("waiting for a new connection");
        ServerSocket ss = new ServerSocket(6060);
        try {
            while (true) {

                Socket s = ss.accept();
                new Thread(new Processor(s)).start();
            }

        } catch (IOException e) {
              e.printStackTrace();
        }

    }
}
