package tfipjava;

import java.io.IOException;




public class Main {
    public static void main(String[] args) throws IOException{
    
    int port=3000;
    String directory="/static/";
    String[] input;
    String additionalFile="";
    try{               
        if (args != null && args.length == 2)
       {     
        if (args[0].equals("--port")){
        port=Integer.parseInt(args[1]);
         }
        else if (args[0].equals("--docRoot"))
        {
            directory=args[1];
            System.out.println(directory);
             }
            }
            if (args.length==4)
            {
                input = args;
                if(args[3].contains(":"))
                {
                String[] MoreFiles=input[3].split(":");
                directory=MoreFiles[0];
                additionalFile=MoreFiles[1];
                                }
                if (args[0].equals("--port"))
                {
                    port=Integer.parseInt(args[1]);
                }
            }
            HttpServer Serv=new HttpServer(port, directory, additionalFile);
            Serv.startServer();
        }
        catch (Exception e)
        {
            System.out.println("System.exit(1)");
        }
          
}

}