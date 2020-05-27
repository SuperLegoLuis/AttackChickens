package dev.sllcoding.MindstomTest.DeathMessages;

import net.kyori.text.Component;
import net.kyori.text.TextComponent;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.damage.DamageType;

public class ChickenDeathMessage extends DamageType {

    public ChickenDeathMessage() {
        super("attack.byChicken");
    }

    @Override
    public Component buildChatMessage(Player killed) {
        return TextComponent.of(killed.getUsername() + " was clucked to death by an Attack Chicken");
    }

    @Override
    public Component buildDeathScreenMessage(Player killed) {
        return TextComponent.of("haha chimkn go brrr");
    }

}
