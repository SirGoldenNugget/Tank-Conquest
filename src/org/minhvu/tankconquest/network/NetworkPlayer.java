package org.minhvu.tankconquest.network;

import java.net.InetAddress;

import org.minhvu.tankconquest.sprites.Player;

public class NetworkPlayer extends Player
{
	private InetAddress address;
	private int port;
	
	public NetworkPlayer(InetAddress address, int port)
	{
		super();
		
		this.address = address;
		this.port = port;
	}
	
	@Override
	public void move()
	{
		super.move();
	}
}
