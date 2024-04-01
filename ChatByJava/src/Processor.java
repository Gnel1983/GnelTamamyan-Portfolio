import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Processor implements Runnable {

    Socket socket;
    private final static ConcurrentHashMap<String, Socket> clients = new ConcurrentHashMap<>();

    public Processor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            InputStream is = socket.getInputStream();
            String name = readMassage(is);
            System.out.println(name);
            clients.put(name, socket);


            while (true) {
                String massage = readMassage(is);
                String to = getUserFromMassage(massage);
                OutputStream os = clients.get(to).getOutputStream();
                String text = getContentFromMassage(massage);
                sendMassage(text, os);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    private static String getUserFromMassage(String msg) {
        String[] arr = msg.split(":");
        return arr[0];

    }

    private static String getContentFromMassage(String msg) {
        String[] arr = msg.split(":");
        return arr[1];

    }

    private static String readMassage(InputStream is) throws IOException {
        byte[] headerSizeBytes = new byte[4];
        is.read(headerSizeBytes);
        int header = byteArrayToInt(headerSizeBytes);
        byte[] massageBytes = new byte[header];
        is.read(massageBytes);


        return new String(massageBytes);
    }

    private static int byteArrayToInt(byte[] arr) {
        int result = 0;

        result = result | arr[0];
        result = result << 8;
        result = result | arr[1];
        result = result << 8;
        result = result | arr[2];
        result = result << 8;
        result = result | arr[3];

        return result;

    }

    private static void sendMassage(String massage, OutputStream os) throws IOException {
        byte[] massageBytes = massage.getBytes();
        int massageLength = massageBytes.length;
        byte[] headerBytes = intToByteArray(massageLength);

        os.write(headerBytes);
        os.write(massageBytes);


    }

    private static byte[] intToByteArray(int value) {
        byte[] arr = new byte[4];

        arr[0] = (byte) (value >> 24);
        arr[1] = (byte) (value >> 16);
        arr[2] = (byte) (value >> 8);
        arr[3] = (byte) value;

        return arr;

    }

}
