package testReceivePacket;

import java.io.IOException;

public class Receiver 
{
	public static void main(String[] args) /*throws IOException*/ {
		try{
    	System.out.println("Reciever.java reporting for duty.");
        new ReceiverThread().start();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("aaaaaah!");
		}
    }
}
