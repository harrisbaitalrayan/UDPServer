import java.io.*;
import java.net.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
      BufferedReader inFromUser =
         new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
     // String sentence = inFromUser.readLine();
      // sendData = sentence.getBytes();


      //int data = 0b11010111;
        int data = 0xD7;
      //sendData[0] = data;
      //sendData[1] = (byte)100;
      
      //sendData = {0xD, 0x01, 0x04, 0x00, 0x41, 0x54 };
      
     // String val = "11010111";
     // sendData= new BigInteger(val, 2).toByteArray();
      
      byte x = (byte)data;
      
     	sendData[0] = x;
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      clientSocket.send(sendPacket);
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      String modifiedSentence = new String(receivePacket.getData());
     // System.out.println("FROM SERVER:" + modifiedSentence);
      clientSocket.close();
   }
}