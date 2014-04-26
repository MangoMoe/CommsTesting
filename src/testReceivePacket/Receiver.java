package testReceivePacket;

import java.io.IOException;

public class Receiver 
{
	public static void main(String[] args) throws IOException {
    	System.out.println(System.getProperty("user.dir") + "\nReciever.java reporting for duty.");
        new ReceiverThread().start();
    }
}
