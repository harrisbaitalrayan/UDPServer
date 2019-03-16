import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception
      {
	   //UDPServer udpServer = new UDPServer();
	   //udpServer.startServer(4000, "A");
	   
	   Runnable r = new UDPServerThread(4000,"A");
		new Thread(r).start();
		
		r = new UDPServerThread(4001,"B");
		new Thread(r).start();
		
		r = new UDPServerThread(4002,"C");
		new Thread(r).start();
		
		r = new UDPServerThread(4003,"D");
		new Thread(r).start();
	   
      }
   
   public  void startServer(int serverPort, String bank) throws IOException {
	   
	   DatagramSocket serverSocket = new DatagramSocket(serverPort);
       byte[] receiveData = new byte[12];
       byte[] sendData = new byte[1024];
       while(true)
          {
             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
             serverSocket.receive(receivePacket);
             String sentence = new String( receivePacket.getData());
             
             byte[] recieveByteArray = receivePacket.getData();
             
             for(int i=0;i<recieveByteArray.length;i++) {
           	  byte first = recieveByteArray[i];                      
                 int firstIntVal = first & 0xFF;                    		  
                 String binaryString = String.format("%8s", Integer.toBinaryString(first & 0xFF)).replace(' ', '0');
                 String hexString = String.format("%02x", first);
                 
				/*
				 * System.out.println(">>>>>>>> PORT "+serverPort+" BANK "
				 * +bank+" TRIGGERRED <<<<<<<<");
				 * System.out.println("-------------BYTE "+i+"----------------");
				 * System.out.println("RECEIVED BYTE  : " + first);
				 * System.out.println("RECEIVED INT   : " + firstIntVal);
				 * System.out.println("RECEIVED HEX   : " + hexString);
				 * System.out.println("RECEIVED BINARY: " + binaryString);
				 */
             }
             
             


             InetAddress clientIPAddress = receivePacket.getAddress();
             int clientPort = receivePacket.getPort();
             //String capitalizedSentence = sentence.toUpperCase();
             //sendData = capitalizedSentence.getBytes();
             
             sendData[0] = (byte)0xC4; 
             DatagramPacket sendPacket =
             new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
             serverSocket.send(sendPacket);
          }
   }
}