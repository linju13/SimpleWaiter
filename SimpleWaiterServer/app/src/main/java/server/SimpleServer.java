package server;

/**
 * Created by jürgen on 14.11.2017.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public abstract class SimpleServer
{
    private ServerSocket server;
    private final int PORTNR = 9999;
    private ServerThread thread;

    protected abstract void log(String text);

    protected abstract String action(Socket socket, Scanner scanner);

    public void startServer()
    {
        try
        {
            server = new ServerSocket(PORTNR);
            log("Server started on Port " + PORTNR + ".......");
            thread = new ServerThread();
            thread.start();
        }
        catch(Exception ex)
        {
            log(SimpleServer.class.getName() + "_" + ex.getMessage());
        }
    }

    public void stopServer()
    {
        if(thread != null)
        {
            thread.interrupt();
        }
    }

    class ServerThread extends Thread
    {
        @Override
        public void run()
        {
            while(!this.isInterrupted())
            {
                try
                {
                    Socket socket = server.accept();
                    String clientStr = socket.getRemoteSocketAddress().toString();
                    log("connected to " + clientStr);

                    ClientCommunicationThread cct =
                            new ClientCommunicationThread(socket);
                    cct.start();
                }
                catch (IOException ex){}
            }

            try
            {
                server.close();
                log("shut down");
            }
            catch (IOException ex)
            {
                log("shut down failed");
            }
        }
    }

    class ClientCommunicationThread extends Thread
    {
        private Socket socket;

        public ClientCommunicationThread(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            InputStream is = null;
            OutputStream os = null;

            try
            {
                is = socket.getInputStream();
                os = socket.getOutputStream();

                Scanner scan = new Scanner(is);
                PrintWriter writer = new PrintWriter(os, true);

                String response = action(socket, scan);
                writer.println(response);

                writer.close();
                scan.close();
                socket.close();

            }
            catch (IOException ex)
            {
                log("Fehler in ClientCommunication" +  ex.toString());
            }
        }
    }
}

