package testSendPacket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class TestServer {
	private final static int PORT = 4445;
	
	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);	// initialize some stuff
		String host;
		int light;
		boolean shutDown = false;
		InetAddress address;	// initialize udp packet things
		byte[] buf = new byte[256];
		DatagramPacket packet;
		
		System.out.println("Enter name (ex HAL1) or ip address of host to broadcast to: ");
		host = reader.nextLine();
		System.out.println("Getting address by name or ip address: " + host);
		address = InetAddress.getByName(host);
				
		System.out.println(address.getAddress());	
		System.out.println(address.toString());

        // get a datagram socket
        DatagramSocket socket = new DatagramSocket();
		
		
		while(!shutDown)
		{
			System.out.println("Enter a 1 (on) or 0 (off) to send packet or -1 to quit");
			light = reader.nextInt();
			if(light == -1)
			{
				System.out.println("Shutting Down, bye");
				shutDown = true;
				continue;
			}
	        
			// send request
	        if(light == 0)
	        {
	        	buf[0] = 0;
	        }
	        else
	        {
	        	buf[0] = 1;
	        }
	        
	        packet = new DatagramPacket(buf, buf.length, address, PORT);	// sends packet to address host on port 4445
	        socket.send(packet);
	        System.out.println("Packet sent");
		}
		
		// testing
		// my laptop's ip address: 128.187.97.22 //10.24.26.32 //192.168.1.109
		// host name: Medallion
    
            // get response
        /*packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

	    // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Quote of the Moment: " + received);*/
    
        socket.close();	// shut things down
        reader.close();
    }
}