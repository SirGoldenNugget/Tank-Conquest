package org.minhvu.tankconquest.network.packets;

import org.minhvu.tankconquest.network.Client;
import org.minhvu.tankconquest.network.Server;

public abstract class Packet
{
	public static enum Packets
	{
		INVALID(-1),
		LOGIN(00),
		DISCONNECT(01);
		
		private int packetid;
		
		private Packets(int packetid)
		{
			this.packetid = packetid;
		}
		
		public int getPacketID()
		{
			return packetid;
		}
	}
	
	private byte packetid;
	
	public Packet(int packetid)
	{
		this.packetid = (byte) packetid;
	}
	
	public abstract void writeData(Client client);
	public abstract void writeData(Server server);
	public abstract byte[] getData();
	
	public String readData(byte[] data)
	{
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public static Packets findPacket(String packetid)
	{
		try
		{
			return findPacket(Integer.parseInt(packetid));
		}
		
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		return Packets.INVALID;
	}
	
	public static Packets findPacket(int packetid)
	{
		for (Packets packet : Packets.values())
		{
			if (packet.getPacketID() == packetid)
			{
				return packet;
			}
		}
		
		return Packets.INVALID;
	}
}
