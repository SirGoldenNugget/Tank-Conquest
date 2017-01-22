package org.minhvu.tankconquest.network;

import java.net.InetAddress;

import org.minhvu.tankconquest.sprites.Player;

public class NetworkPlayer extends Player
{
	private InetAddress address;
	private int port;
	
	public NetworkPlayer(String name, InetAddress address, int port)
	{
		super(name);
		
		this.address = address;
		this.port = port;
	}
	
	public InetAddress getAddress()
	{
		return address;
	}
	
	public int getPort()
	{
		return port;
	}
	
	@Override
	public void move()
	{
		super.move();
	}
}
