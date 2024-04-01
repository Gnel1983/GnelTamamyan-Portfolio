import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 6060);
        System.out.println("please enter your name");
        Scanner scanner = new Scanner(System.in);
        String massage = scanner.nextLine();
        OutputStream os = socket.getOutputStream();
        sendMassage(massage, os);

        new Thread(() -> {

            try {

                while (true) {
                    InputStream is = socket.getInputStream();
                    String content = readMassage(is);
                    System.out.println(content);

                }

            } catch (IOException e) {

            }

        }).start();

        while (true) {
            String text = scanner.nextLine();
            sendMassage(text, os);

        }


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
}