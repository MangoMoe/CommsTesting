package testReceivePacket;

import java.io.*;
import java.net.*;

public class ReceiverThread  extends Thread {

	    protected DatagramSocket socket = null;
	    protected BufferedReader in = null;
	    protected boolean shutDown = false;	// replace with interrupts?
	    protected final int PORT = 4444;
	    private static TwoWayMap<HeaderType, Byte> HeaderMap = new TwoWayMap<HeaderType, Byte>();	// map byte values to strings for easy encoding and decoding

	    public ReceiverThread() throws IOException {
	    	this("Test RecieverThread");
	    }

	    public ReceiverThread(String name) throws IOException {
	        super(name);
	        socket = new DatagramSocket(PORT);
	        initializeHeaderMap();
	    }
	    	
	    public void run() {

	        while (!shutDown) {
	            try {
	                byte[] buf = new byte[6];

	                // receive request
	                DatagramPacket packet = new DatagramPacket(buf, buf.length);
	                socket.receive(packet);
	                System.out.println("Packet Recieved: " + HeaderMap.getHeader(buf[0]));
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
	    
	    private static void initializeHeaderMap()	// set up map of strings to byte values
	    {
	    	
	    	//enum?
	    	// System: 0x00 - 0x0F
	    	HeaderMap.put(HeaderType.systemConfirmation, new Byte((byte) 0x00));
	    	
	    	// Drive: 0x10 - 0x1F
	    	HeaderMap.put(HeaderType.driveAll, new Byte((byte) 0x10));
	    	HeaderMap.put(HeaderType.driveRight, new Byte((byte) 0x11));
	    	HeaderMap.put(HeaderType.driveLeft, new Byte((byte) 0x12));
	    	HeaderMap.put(HeaderType.drive1, new Byte((byte) 0x13));
	    	HeaderMap.put(HeaderType.drive2, new Byte((byte) 0x14));
	    	HeaderMap.put(HeaderType.drive3, new Byte((byte) 0x15));
	    	HeaderMap.put(HeaderType.drive4, new Byte((byte) 0x16));
	    	HeaderMap.put(HeaderType.drive5, new Byte((byte) 0x17));
	    	HeaderMap.put(HeaderType.drive6, new Byte((byte) 0x18));
	    	
	    	// Arm: 0x20 - 0x2F
	    	HeaderMap.put(HeaderType.armTurretCommand, new Byte((byte) 0x20));
	    	HeaderMap.put(HeaderType.armShoulderCommand, new Byte((byte) 0x21));
	    	HeaderMap.put(HeaderType.armElbowCommand, new Byte((byte) 0x22));
	    	HeaderMap.put(HeaderType.armWristFlapCommand, new Byte((byte) 0x23));
	    	HeaderMap.put(HeaderType.armWristRotateCommand, new Byte((byte) 0x24));
	    	HeaderMap.put(HeaderType.armGripperCommand, new Byte((byte) 0x25));
	    	HeaderMap.put(HeaderType.armRotatorCommand, new Byte((byte) 0x26));
	    	HeaderMap.put(HeaderType.armShoulderFeedback, new Byte((byte) 0x27));
	    	HeaderMap.put(HeaderType.armShoulderCurrent, new Byte((byte) 0x28));
	    	HeaderMap.put(HeaderType.armElbowFeedback , new Byte((byte) 0x29));
	    	HeaderMap.put(HeaderType.armElbowCurrent, new Byte((byte) 0x2A));
	    	HeaderMap.put(HeaderType.armWristFlapFeedback, new Byte((byte) 0x2B));
	    	HeaderMap.put(HeaderType.armWristRotateFeedback, new Byte((byte) 0x2C));
	    	HeaderMap.put(HeaderType.armGripperCurrent, new Byte((byte) 0x2B));
	    	
	    	// Gimbal: 0x30-0x3F
	    	HeaderMap.put(HeaderType.gimbal, new Byte((byte) 0x30));
	    	
	    	// Camera: 0x40 - 0x4F
	    	HeaderMap.put(HeaderType.camera, new Byte((byte) 0x40));
	    	
	    	// Battery: 0x50 - 0x5F
	    	HeaderMap.put(HeaderType.battery, new Byte((byte) 0x50));
	    	
	    	// IMU: 0x60 - 0x6F
	    	HeaderMap.put(HeaderType.imu, new Byte((byte) 0x60));
	    	
	    	// Misc/laser?: 0x70 - 0x7F
	    	HeaderMap.put(HeaderType.misc, new Byte((byte) 0x70));
	    	
	    	
	    }
}
