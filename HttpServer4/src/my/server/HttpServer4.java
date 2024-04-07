package my.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class HttpServer4 {

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8080);

        while (true) {
            Socket socket = ss.accept();
            HttpRequest request = readRequest(socket);
            HttpResponse httpResponse = processRequest(request);
            send(httpResponse, socket);
        }


    }

    static HttpRequest readRequest(Socket s) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(s.getInputStream()));

        String requestLine = reader.readLine();
        String[] requestLineParts = requestLine.split(" ");

        String method = requestLineParts[0];
        String url = requestLineParts[1];
        String protocol = requestLineParts[2];

        Map<String, String> headers = new HashMap<>();
        String line;
        while (!"".equals(line = reader.readLine())) {
            String[] headerLines = line.split(":");
            headers.put(headerLines[0], headerLines[1]);
        }


        return new HttpRequest(method, url, protocol, headers);
    }

    static HttpResponse processRequest(HttpRequest request) throws Exception {
        String filePath = getPathFromUrl(request.url);


        if (!new File(filePath).exists()) {
            return new HttpResponse(request.protocol, 404, "Not Found", Map.of("Content-Length", 0), null);
        }

        Map<String, Object> headers = new HashMap<>();
        String content = String.join("\r\n", Files.readAllLines(Path.of(filePath)));
        int contentLength = content.length();
        String contentType = getTypeFromFilePath(filePath);

        headers.put("Content-Type", contentType);
        headers.put("Content-Length", contentLength);


        return new HttpResponse(request.protocol, 200, "OK", headers, content);
    }



    static String getPathFromUrl(String url) {
        if ("/".equals(url)) {
            return "index.html";
        }
        String[] urlParts = url.split("/");

        return String.join(File.separator, urlParts).substring(1);
    }

    static String getTypeFromFilePath(String fileName) {
        if (fileName.endsWith("html")) {
            return "text/html";
        } else if (fileName.endsWith("css")) {
            return "text/css";
        } else {
            return "text/plain";
        }
    }



    static void send(HttpResponse response, Socket socket) throws Exception {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

        // response line
        writer.println(response.protocol + " " + response.statusCode + " " + response.statusName);

        // headers
        for (Map.Entry<String, Object> entry : response.headers.entrySet()) {
            writer.println(entry.getKey() + ":" + entry.getValue());
        }

        // empty line
        writer.println();

        // body
        if (!"".equals(response.body)) {
            writer.println(response.body);
        }
    }


}

class HttpRequest {
    String method;
    String url;
    String protocol;
    Map<String, String> headers;

    public HttpRequest(String method, String url, String protocol, Map<String, String> headers) {
        this.method = method;
        this.url = url;
        this.protocol = protocol;
        this.headers = headers;
    }
}


class HttpResponse {
    String protocol;
    int statusCode;
    String statusName;
    Map<String, Object> headers;
    String body;


    public HttpResponse(String protocol, int statusCode, String statusName, Map<String, Object> headers, String body) {
        this.protocol = protocol;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.headers = headers;
        this.body = body;
    }
}


