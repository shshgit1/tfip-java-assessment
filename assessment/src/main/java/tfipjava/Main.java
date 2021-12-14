package tfipjava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) throws IOException{
        
    String inputfromArgs;
    Socket socket;
    int port=3000;
    ServerSocket serverSocket;  
    String directory="/static/";
    String[] input;
    String[] moreFiles;

    try{               
        if (args != null && args.length == 2)
       {     
        if (args[0].equals("--port")){

        port=Integer.valueOf(args[1]);
        System.out.println(port);
    }
        else if (args[0].equals("--docRoot"))
        {
            directory=args[1];
        }
            }

            if (args.length==4)
            {
                input = args;
                if(args[3].contains(":"))
                {
                String[] MoreFiles=input[3].split(":");
                moreFiles=MoreFiles;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("System.exit(1)");
        }

       
           ExecutorService threadPool = Executors.newFixedThreadPool(3);
        serverSocket = new ServerSocket(port);
        System.out.println("Server listening at port..." +port);
        try{
        while (true)
        {
            socket = serverSocket.accept();
HttpServer serv=new HttpServer(socket,directory);
threadPool.submit(serv);
        }
    }
        finally
        {
            serverSocket.close();  
        }
}

}