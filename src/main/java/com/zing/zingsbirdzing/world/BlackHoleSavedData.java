package net.mcreator.zingsbirdzing.world;

import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.BlockPos;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

import com.mojang.serialization.Codec;

public class BlackHoleSavedData extends SavedData {
	private static final String DATA_NAME = "black_hole_manager_data";
	private final ConcurrentHashMap<BlackHoleManager.BlackHoleKey, BlackHoleManager.BlackHoleData> blackHoles = new ConcurrentHashMap<>();
	public static final Codec<BlackHoleSavedData> CODEC = CompoundTag.CODEC.xmap(tag -> BlackHoleSavedData.load(tag, null), data -> data.save(new CompoundTag(), null));
	public static final SavedDataType<BlackHoleSavedData> TYPE = new SavedDataType<BlackHoleSavedData>(Identifier.parse(DATA_NAME), BlackHoleSavedData::new, CODEC, null);

	public static BlackHoleSavedData get(ServerLevel level) {
		ServerLevel overworld = level.getServer().getLevel(Level.OVERWORLD);
		if (overworld == null)
			overworld = level;
		return overworld.getDataStorage().computeIfAbsent(TYPE);
	}

	public BlackHoleSavedData() {
	}

	public ConcurrentHashMap<BlackHoleManager.BlackHoleKey, BlackHoleManager.BlackHoleData> getBlackHoles() {
		return blackHoles;
	}

	public static BlackHoleSavedData load(CompoundTag tag, HolderLookup.Provider provider) {
		BlackHoleSavedData data = new BlackHoleSavedData();
		ListTag list = tag.getList("BlackHoles").orElseGet(ListTag::new);
		for (int i = 0; i < list.size(); i++) {
			CompoundTag bhTag = list.getCompound(i).orElseGet(CompoundTag::new);
			String dimStr = bhTag.getString("Dimension").orElse("minecraft:overworld");
			long posLong = bhTag.getLong("Pos").orElse(0L);
			ResourceKey<Level> dim = ResourceKey.create(Registries.DIMENSION, Identifier.parse(dimStr.isEmpty() ? "minecraft:overworld" : dimStr));
			BlockPos pos = BlockPos.of(posLong);
			int stackSize = bhTag.getInt("StackSize").orElse(1);
			double blockRadius = bhTag.getDouble("BlockRadius").orElse(10.0);
			boolean pullEntities = bhTag.getBoolean("PullEntities").orElse(true);
			double entityRadius = bhTag.getDouble("EntityRadius").orElse(15.0);
			double power = bhTag.getDouble("Power").orElse(1.0);
			boolean killEntities = bhTag.getBoolean("KillEntities").orElse(true);
			BlackHoleManager.BlackHoleKey key = new BlackHoleManager.BlackHoleKey(dim, pos);
			BlackHoleManager.BlackHoleData bhData = new BlackHoleManager.BlackHoleData(key, stackSize, blockRadius, pullEntities, entityRadius, power, killEntities);
			data.blackHoles.put(key, bhData);
		}
		return data;
	}

	public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
		ListTag list = new ListTag();
		for (Map.Entry<BlackHoleManager.BlackHoleKey, BlackHoleManager.BlackHoleData> entry : blackHoles.entrySet()) {
			CompoundTag bhTag = new CompoundTag();
			bhTag.putString("Dimension", entry.getKey().dimension().identifier().toString());
			bhTag.putLong("Pos", entry.getKey().pos().asLong());
			bhTag.putInt("StackSize", entry.getValue().stackSize());
			bhTag.putDouble("BlockRadius", entry.getValue().blockRadius());
			bhTag.putBoolean("PullEntities", entry.getValue().pullEntities());
			bhTag.putDouble("EntityRadius", entry.getValue().entityRadius());
			bhTag.putDouble("Power", entry.getValue().power());
			bhTag.putBoolean("KillEntities", entry.getValue().killEntities());
			list.add(bhTag);
		}
		tag.put("BlackHoles", list);
		return tag;
	}
}