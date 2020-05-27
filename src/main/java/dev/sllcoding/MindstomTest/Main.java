package dev.sllcoding.MindstomTest;

import dev.sllcoding.MindstomTest.ChunkGeneration.CustomChunkGenerator;
import dev.sllcoding.MindstomTest.Entities.AttackChicken;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.network.ConnectionManager;
import net.minestom.server.utils.Position;

public class Main {

    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer world = instanceManager.createInstanceContainer();

        world.setChunkGenerator(new CustomChunkGenerator());
        world.enableAutoChunkLoad(true);

        ConnectionManager connectionManager = MinecraftServer.getConnectionManager();

        connectionManager.addPlayerInitialization(player -> {
            player.addEventCallback(PlayerLoginEvent.class, event -> event.setSpawningInstance(world));
            player.addEventCallback(PlayerSpawnEvent.class, event -> {
                player.teleport(new Position(0, 70, 0));
                AttackChicken chicken = new AttackChicken(player.getPosition());
                chicken.setInstance(world);
            });
        });

        server.start("localhost", 25565);
    }

}
