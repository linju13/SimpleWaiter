package server;

import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;

/**
 * Created by jürgen on 14.11.2017.
 */
public class SimpleWaiterServer extends SimpleServer
{
    //Attribute
    private Set<Integer> id;
    private Random rand;

    public SimpleWaiterServer()
    {
        id = new HashSet<Integer>();
    }

    @Override
    protected void log(String text){}

    @Override
    protected String action(Socket socket, Scanner scanner)
    {
        rand = new Random();

        int id = rand.nextInt();

        return "I'm here";
    }

}
