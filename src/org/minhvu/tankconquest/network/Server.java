package org.minhvu.tankconquest.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.minhvu.tankconquest.Game;
import org.minhvu.tankconquest.network.packets.Login;
import org.minhvu.tankconquest.network.packets.Packet;
import org.minhvu.tankconquest.network.packets.Packet.Packets;

public class Server extends Thread
{
	private DatagramSocket socket;
	
	private List<NetworkPlayer> players  = new ArrayList<NetworkPlayer>();
	
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
			
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
			
			/*String message = new String(packet.getData());
			
			if (message.trim().equalsIgnoreCase("ping"))
			{
				System.out.println("Client > " + message);
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}*/
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port)
	{
		String message = new String(data).trim();
		Packets packet = Packet.findPacket(message.substring(2));
		
		switch (packet)
		{
			case INVALID:
				break;
			case LOGIN:
				Login loginpacket = new Login(data);
				System.out.println("[" + address.getHostAddress() + ":" + port + "] " + loginpacket.getUsername() + " Has Connected...");
				NetworkPlayer player = new NetworkPlayer(loginpacket.getUsername(), address, port);
				players.add(player);
				
				Game.getInstance().setPlayer(player);
				// Game.getInstance().getPlayers().add(player);
				
				break;
			case DISCONNECT:
				break;
			default:
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

	public void sendDataToAllClients(byte[] data)
	{
		for (NetworkPlayer player : players)
		{
			sendData(data, player.getAddress(), player.getPort());
		}
	}
}
