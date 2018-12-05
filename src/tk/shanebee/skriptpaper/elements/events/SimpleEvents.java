package tk.shanebee.skriptpaper.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import com.destroystokyo.paper.event.block.AnvilDamagedEvent;
import com.destroystokyo.paper.event.block.BeaconEffectEvent;
import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import com.destroystokyo.paper.event.entity.*;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import com.destroystokyo.paper.event.player.PlayerReadyArrowEvent;

public class SimpleEvents {

    static {
        // 1.9.4+ EVENTS
        Skript.registerEvent("Beacon Effect", SimpleEvent.class, BeaconEffectEvent.class, "beacon effect")
                .description("Called when a beacon effect is being applied to a player.")
                .examples("ToDo") // to do
                .requiredPlugins("Paper 1.9+")
                .since("1.0.0");

        // 1.10.2+ EVENTS
        if (Skript.isRunningMinecraft(1, 10, 2)) {
            Skript.registerEvent("Entity Zap", SimpleEvent.class, EntityZapEvent.class, "entity (zap|struck by lightning)")
                    .description("Fired when lightning strikes an entity")
                    .examples("on entity zap:",
                            "\tif event-entity is a pig:",
                            "\t\tspawn 3 zombie pigmen at event-location")
                    .requiredPlugins("Paper 1.10.2+")
                    .since("1.0.0");
        }

        // 1.11.2+ EVENTS
        if (Skript.isRunningMinecraft(1, 11, 2)) {
            Skript.registerEvent("Projectile Collide", SimpleEvent.class, ProjectileCollideEvent.class, "projectile collide")
                    .description("Called when an projectile collides with an entity" +
                            " (This event is called before entity damage event, and cancelling it will allow the projectile to continue flying)")
                    .examples("on projectile collide:",
                            "\tif event-entity is a player:",
                            "\t\tcancel event")
                    .requiredPlugins("Paper 1.11.2+")
                    .since("1.0.0");
        }


        // 1.12.2+ EVENTS
        if (Skript.isRunningMinecraft(1, 12, 2)) {
            Skript.registerEvent("Enderman Attack Player", SimpleEvent.class, EndermanAttackPlayerEvent.class, "ender(man|men) attack [player]")
                    .description("Fired when an Enderman determines if it should attack a player or not. Starts off cancelled " +
                            "if the player is wearing a pumpkin head or is not looking at the Enderman, according to Vanilla rules.")
                    .examples("on enderman attack:",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            Skript.registerEvent("Entity Knockback", SimpleEvent.class, EntityKnockbackByEntityEvent.class, "entity knockback")
                    .description("Fired when an Entity is knocked back by the hit of another Entity. If this event is cancelled, the entity is not knocked back.")
                    .examples("ToDo") // add examples here (figure out entity/player/victim/etc
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            Skript.registerEvent("Experience Orb Merge", SimpleEvent.class, ExperienceOrbMergeEvent.class, "(experience|xp) orb merge")
                    .description("Fired anytime the server is about to merge 2 experience orbs into one")
                    .examples("on xp merge:",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            Skript.registerEvent("Witch Consume Potion", SimpleEvent.class, WitchConsumePotionEvent.class, "witch (consume|drink) potion")
                    .description("Fired when a witch consumes the potion in their hand to buff themselves.")
                    .examples("on witch consume potion",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            Skript.registerEvent("Witch Throw Potion", SimpleEvent.class, WitchThrowPotionEvent.class, "witch throw potion")
                    .description("Fired when a witch throws a potion at a player")
                    .examples("on witch throw potion",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            Skript.registerEvent("Player Change Armor", SimpleEvent.class, PlayerArmorChangeEvent.class, "player change armor")
                    .description("Called when the player themselves change their armor items")
                    .examples("on player change armor:",
                            "\tcancel event",
                            "\tset helmet of player to pumpkin")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            Skript.registerEvent("Player Pickup Experience Orb", SimpleEvent.class, PlayerPickupExperienceEvent.class, "player pickup (experience|xp) [orb]")
                    .description("Fired when a player is attempting to pick up an experience orb")
                    .examples("on player pickup xp:",
                            "\tadd 10 to level of player")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            Skript.registerEvent("Player Ready Arrow", SimpleEvent.class, PlayerReadyArrowEvent.class, "player ready arrow")
                    .description("Called when a player is firing a bow and the server is choosing an arrow to use.")
                    .examples("ToDo") // need to figure out arrow stuff here
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
        }


        // 1.13.2+ EVENTS
        if (Skript.isRunningMinecraft(1, 13, 2)) {
            Skript.registerEvent("Anvil Damage", SimpleEvent.class, AnvilDamagedEvent.class, "anvil damag(e|ing)")
                    .description("Called when an anvil is damaged from being used")
                    .examples("on anvil damage:",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            Skript.registerEvent("TNT Primed", SimpleEvent.class, TNTPrimeEvent.class, "tnt prime[d]")
                    .description("Called when TNT block is about to turn into TNTPrimed")
                    .examples("on tnt prime:",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            Skript.registerEvent("Creeper Ignite", SimpleEvent.class, CreeperIgniteEvent.class, "creeper ignit(e|ing)")
                    .description("Called when a Creeper is ignite flag is changed (armed/disarmed to explode)")
                    .examples("on creeper ignite:",
                            "\tspawn 2 creeper at event-location")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            Skript.registerEvent("Enderdragon Shoot Fireball", SimpleEvent.class, EnderDragonShootFireballEvent.class, "ender[ ]dragon shoot[s] fireball")
                    .description("Fired when an EnderDragon shoots a fireball")
                    .examples("")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            Skript.registerEvent("Enderdragon Fireball Hit", SimpleEvent.class, EnderDragonFireballHitEvent.class, "ender[ ]dragon fireball hit")
                    .description("Fired when a DragonFireball collides with a block/entity and spawns an AreaEffectCloud")
                    .examples("on enderdragon fireball hit:",
                            "\tloop all players in radius 5 of event-location:",
                            "\t\theal loop-player by 2 hearts")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            Skript.registerEvent("Skeleton Horse Trap", SimpleEvent.class, SkeletonHorseTrapEvent.class, "skeleton horse trap")
                    .description("Event called when a player gets close to a " +
                            "<a href='https://minecraft.gamepedia.com/Horse#Skeleton_trap'>skeleton horse trap</a> and triggers the lightning trap.")
                    .examples("on skeleton horse trap:",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            Skript.registerEvent("Turtle Go Home", SimpleEvent.class, TurtleGoHomeEvent.class, "turtle go home")
                    .description("Fired when a Turtle decides to go home")
                    .examples("ToDo") // dunno what to do here
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            Skript.registerEvent("Turtle Lay Egg", SimpleEvent.class, TurtleLayEggEvent.class, "turtle lay[s] egg")
                    .description("Fired when a Turtle lays eggs")
                    .examples("on turtle lay egg:",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            Skript.registerEvent("Turtle Starts Digging", SimpleEvent.class, TurtleStartDiggingEvent.class, "turtle start[s] digging")
                    .description("Fired when a Turtle starts digging to lay eggs")
                    .examples("on turtle starts digging:",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            Skript.registerEvent("Player Elytra Boost", SimpleEvent.class, PlayerElytraBoostEvent.class, "[player] elytra boost")
                    .description("Fired when a player boosts elytra flight with a firework")
                    .examples("on elytra boost:",
                            "\tpush player forward at speed 50")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");


        }




    }

}
