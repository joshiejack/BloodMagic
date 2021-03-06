package wayoftime.bloodmagic.ritual;

import java.util.concurrent.Callable;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public final class CapabilityRuneType
{
	public static class RuneTypeStorage implements Capability.IStorage<IRitualStone.Tile>
	{
		@Override
		public INBT writeNBT(Capability<IRitualStone.Tile> capability, IRitualStone.Tile instance, Direction side)
		{
			return ByteNBT.valueOf((byte) instance.getRuneType().ordinal());
		}

		@Override
		public void readNBT(Capability<IRitualStone.Tile> capability, IRitualStone.Tile instance, Direction side, INBT nbt)
		{
			instance.setRuneType(EnumRuneType.byMetadata(((ByteNBT) nbt).getByte()));
		}
	}

	public static class RuneTypeWrapper implements IRitualStone.Tile
	{
		private EnumRuneType type = EnumRuneType.BLANK;

		@Override
		public boolean isRuneType(EnumRuneType runeType)
		{
			return type == runeType;
		}

		@Override
		public EnumRuneType getRuneType()
		{
			return type;
		}

		public void setRuneType(EnumRuneType runeType)
		{
			type = runeType;
		}
	}

	public static class Factory implements Callable<IRitualStone.Tile>
	{
		@Override
		public IRitualStone.Tile call()
				throws Exception
		{
			return new RuneTypeWrapper();
		}
	}
}