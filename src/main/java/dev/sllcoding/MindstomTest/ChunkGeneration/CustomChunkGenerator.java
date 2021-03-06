package dev.sllcoding.MindstomTest.ChunkGeneration;

import net.minestom.server.instance.Biome;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.ChunkGenerator;
import net.minestom.server.instance.ChunkPopulator;
import net.minestom.server.instance.batch.ChunkBatch;
import net.minestom.server.instance.block.Block;

import java.util.Arrays;
import java.util.List;

public class CustomChunkGenerator extends ChunkGenerator {

    @Override
    public void generateChunkData(ChunkBatch batch, int chunkX, int chunkZ) {
        for (byte x = 0; x < Chunk.CHUNK_SIZE_X; x++)
            for (byte z = 0; z < Chunk.CHUNK_SIZE_Z; z++) {
                for (byte y = 0; y < 65; y++) {
                    batch.setBlock(x, y, z, Block.STONE);
                }
            }
    }

    @Override
    public void fillBiomes(Biome[] biomes, int chunkX, int chunkZ) {
        Arrays.fill(biomes, Biome.PLAINS);
    }

    @Override
    public List<ChunkPopulator> getPopulators() {
        return null;
    }
}