package testReceivePacket;

import java.io.*;
import java.net.*;

public class ReceiverThread  extends Thread {

	    protected DatagramSocket socket = null;
	    protected BufferedReader in = null;
	    protected boolean shutDown = false;	// replace with interrupts?
	    protected final int PORT = 4444;
	    
	    public ReceiverThread() throws IOException {
	    	this("Test RecieverThread");
	    }

	    public ReceiverThread(String name) throws IOException {
	        super(name);
	        socket = new DatagramSocket(PORT);
	    }
	    	
	    public void run() {

	        while (!shutDown) {
	            try {
	                byte[] buf = new byte[5];

	                // receive request
	                DatagramPacket packet = new DatagramPacket(buf, buf.length);
	                socket.receive(packet);
	                System.out.println("Packet Recieved: " + HeaderType.getHeader(buf[0]));
	                System.out.println(byteArrayToHex(buf));

	                // stuff for sending response
	                /*buf = dString.getBytes();

			// send the response to the client at "address" and "port"
	                InetAddress address = packet.getAddress();
	                int port = packet.getPort();
	                packet = new DatagramPacket(buf, buf.length, address, port);
	                socket.send(packet);*/
	            } catch (IOException e) {
	                e.printStackTrace();
	                shutDown = true;
	            }
	        }
	        socket.close();
	    }
	    
	    String byteArrayToHex(byte[] a) {
    	   StringBuilder sb = new StringBuilder();
    	   for(byte b: a)
    	   {
    	      sb.append(String.format("%02x", b&0xff));
    	      sb.append(" ");
    	   }
    	   return sb.toString();
    	}
	    
}
