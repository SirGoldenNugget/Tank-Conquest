package org.minhvu.tankconquest.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client extends Thread
{
	private InetAddress address;
	private DatagramSocket socket;
	
	public Client(String address)
	{
		try
		{
			this.socket = new DatagramSocket();
		}
		
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			this.address = InetAddress.getByName(address);
		}
		
		catch (UnknownHostException e)
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
			
			System.out.println("Server > " + new String(packet.getData()));
		}
	}
	
	public void sendData(byte[] data)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, address, 1331);
		
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
