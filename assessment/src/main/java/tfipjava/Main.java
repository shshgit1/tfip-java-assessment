package tfipjava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    Socket socket;
    ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {
       Main newConnection=new Main();
       newConnection.StartServer();
    }
    public void StartServer() throws IOException{
        System.out.println("Welcome, key --port <port number> for port or --docroot <directory>");
        Scanner scan=new Scanner(System.in);
        String inputOption= scan.next();
        String afterInput=scan.nextLine();
  
      
        if (inputOption.equals("--port"))
        {
            int x=Integer.parseInt(afterInput.trim());
            System.out.println(x);
            serverSocket=new ServerSocket(x);
                       System.out.println("listening to port "+x);
try {
    while (true)
    {
        socket=serverSocket.accept();
        HttpServer serv=new HttpServer(socket);
        serv.run();
    }
}
finally {
    serverSocket.close();
}

        }

        else if (inputOption.equals("--docroot"))
        {
            String s=afterInput.trim();

        }
    
    
    }

}
