package org.minhvu.tankconquest.network.packets;

import org.minhvu.tankconquest.network.Client;
import org.minhvu.tankconquest.network.Server;

public class Login extends Packet
{
	private String username;
	
	public Login(byte[] data)
	{
		super(00);
		username = readData(data);
	}
	
	public Login(String username)
	{
		super(00);
		this.username = username;
	}

	@Override
	public void writeData(Client client)
	{
		client.sendData(getData());
	}
	
	@Override
	public void writeData(Server server)
	{
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData()
	{
		return ("00" + username).getBytes();
	}

	public String getUsername()
	{
		return username;
	}

}
