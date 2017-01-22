package org.minhvu.tankconquest.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread
{
	private DatagramSocket socket;
	
	public Server()
	{
		try
		{
			this.socket = new DatagramSocket(1331);
		}
		
		catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			byte[] data = new byte[1024];
			
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try
			{
				socket.receive(packet);
			}

			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			String message = new String(packet.getData());
			
			if (message.trim().equalsIgnoreCase("ping"))
			{
				System.out.println("Client > " + message);
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}
		}
	}
	
	public void sendData(byte[] data, InetAddress address, int port)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		
		try
		{
			socket.send(packet);
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
