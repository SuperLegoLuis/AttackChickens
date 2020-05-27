package dev.sllcoding.MindstomTest.Entities;

import dev.sllcoding.MindstomTest.DeathMessages.ChickenDeathMessage;
import fr.themode.demo.entity.ChickenCreature;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.entity.EntityTickEvent;
import net.minestom.server.utils.Position;

public class AttackChicken extends ChickenCreature {

    private long waitingTicks = 0;

    public AttackChicken(Position defaultPosition) {
        super(defaultPosition);
        this.setCustomName("Attack Chicken");
        this.setCustomNameVisible(true);
        addEventCallback(EntityTickEvent.class, event -> {
            if (event.getEntity().equals(this)) {
                waitingTicks++;
                Player nearestPlayer = null;
                for (Player player : this.getViewers()) {
                    if (nearestPlayer == null) {
                        if (!player.isDead())
                            nearestPlayer = player;
                    } else {
                        if (this.getDistance(player) < this.getDistance(nearestPlayer) && !player.isDead()) {
                            nearestPlayer = player;
                        }
                    }
                }
                if (nearestPlayer != null) {
                    float radians = (float) Math.atan2(nearestPlayer.getPosition().getZ() - position.getZ(), nearestPlayer.getPosition().getX() - position.getX());
                    float yaw = (float) (radians * (180.0 / Math.PI)) - 90;
                    this.setView(yaw, this.getPosition().getPitch());
                    Position newPos = nearestPlayer.getPosition();
                    newPos.setYaw(yaw);
                    this.moveTowards(newPos, 0.25f);
                    if (getBoundingBox().intersect(nearestPlayer.getBoundingBox()) && waitingTicks >= 10) {
                        waitingTicks = 0;
                        nearestPlayer.damage(new ChickenDeathMessage(), 1);
                    }
                }
            }
        });
    }

}
