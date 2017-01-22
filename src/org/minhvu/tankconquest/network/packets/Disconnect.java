package org.minhvu.tankconquest.network.packets;

import org.minhvu.tankconquest.network.Client;
import org.minhvu.tankconquest.network.Server;

public class Disconnect extends Packet
{
	public Disconnect(int packetid)
	{
		super(packetid);
	}

	@Override
	public void writeData(Client client)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeData(Server server)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getData()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
