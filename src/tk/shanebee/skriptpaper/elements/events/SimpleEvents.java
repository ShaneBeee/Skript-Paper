package tk.shanebee.skriptpaper.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.destroystokyo.paper.event.block.AnvilDamagedEvent;
import com.destroystokyo.paper.event.block.BeaconEffectEvent;
import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import com.destroystokyo.paper.event.entity.*;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import com.destroystokyo.paper.event.player.PlayerReadyArrowEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class SimpleEvents {

    static {
        // 1.9.4+ EVENTS
        Skript.registerEvent("Beacon Effect", SimpleEvent.class, BeaconEffectEvent.class, "beacon effect")
                .description("Called when a beacon effect is being applied to a player.")
                .examples("ToDo") // to do
                .requiredPlugins("Paper 1.9+")
                .since("1.0.0");
        EventValues.registerEventValue(BeaconEffectEvent.class, Player.class, new Getter<Player, BeaconEffectEvent>() {
            @Override
            public Player get(BeaconEffectEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(BeaconEffectEvent.class, PotionEffectType.class, new Getter<PotionEffectType, BeaconEffectEvent>() {
            @Override
            public PotionEffectType get(BeaconEffectEvent e) {
                return e.getEffect().getType();
            }
        }, 0);

        Skript.registerEvent("Shear Entity", SimpleEvent.class, PlayerShearEntityEvent.class, "[player] shear entity")
                .description("Called when a player shears an entity [Does not require Paper]")
                .examples("on player shear entity:")
                .since("1.3.0");
        EventValues.registerEventValue(PlayerShearEntityEvent.class, Player.class, new Getter<Player, PlayerShearEntityEvent>() {
            @Override
            public Player get(PlayerShearEntityEvent e) {
                return e.getPlayer();
            }
        }, 0);

        // 1.10.2+ EVENTS
        if (Skript.isRunningMinecraft(1, 10, 2)) {
            Skript.registerEvent("Entity Zap", SimpleEvent.class, EntityZapEvent.class, "entity (zap|struck by lightning)")
                    .description("Fired when lightning strikes an entity")
                    .examples("on entity zap:",
                            "\tif event-entity is a pig:",
                            "\t\tspawn 3 zombie pigmen at event-location")
                    .requiredPlugins("Paper 1.10.2+")
                    .since("1.0.0");
            EventValues.registerEventValue(EntityZapEvent.class, Location.class, new Getter<Location, EntityZapEvent>() {
                @Override
                public Location get(EntityZapEvent e) {
                    return e.getEntity().getLocation();
                }
            }, 0);
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
            EventValues.registerEventValue(ProjectileCollideEvent.class, Entity.class, new Getter<Entity, ProjectileCollideEvent>() {
                @Override
                public Entity get(ProjectileCollideEvent e) {
                    return e.getCollidedWith();
                }
            }, 0);
            EventValues.registerEventValue(ProjectileCollideEvent.class, Projectile.class, new Getter<Projectile, ProjectileCollideEvent>() {
                @Override
                public Projectile get(ProjectileCollideEvent e) {
                    return e.getEntity();
                }
            }, 0);
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
            EventValues.registerEventValue(EndermanAttackPlayerEvent.class, Player.class, new Getter<Player, EndermanAttackPlayerEvent>() {
                @Override
                public Player get(EndermanAttackPlayerEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(EndermanAttackPlayerEvent.class, Entity.class, new Getter<Entity, EndermanAttackPlayerEvent>() {
                @Override
                public Entity get(EndermanAttackPlayerEvent e) {
                    return e.getEntity();
                }
            }, 0);
            Skript.registerEvent("Entity Knockback", SimpleEvent.class, EntityKnockbackByEntityEvent.class, "entity knockback")
                    .description("Fired when an Entity is knocked back by the hit of another Entity. If this event is cancelled, the entity is not knocked back.")
                    .examples("on entity knockback:", "" +
                            "\tif event-entity is a cow:", "\t\tcancel event")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            // TODO more event values for Entity Knockback
            Skript.registerEvent("Experience Orb Merge", SimpleEvent.class, ExperienceOrbMergeEvent.class, "(experience|xp) orb merge")
                    .description("Fired anytime the server is about to merge 2 experience orbs into one")
                    .examples("on xp merge:",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            Skript.registerEvent("Witch Consume Potion", SimpleEvent.class, WitchConsumePotionEvent.class, "witch (consume[s]|drink[s]) [a] potion")
                    .description("Fired when a witch consumes the potion in their hand to buff themselves.")
                    .examples("on witch consume potion",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            EventValues.registerEventValue(WitchConsumePotionEvent.class, ItemType.class, new Getter<ItemType, WitchConsumePotionEvent>() {
                @Override
                public ItemType get(WitchConsumePotionEvent e) {
                    ItemStack item = e.getPotion();
                    ItemType type = new ItemType(item);
                    return type;
                }
            }, 0);
            Skript.registerEvent("Witch Throw Potion", SimpleEvent.class, WitchThrowPotionEvent.class, "witch throw[s] [a] potion")
                    .description("Fired when a witch throws a potion at a player")
                    .examples("on witch throw potion",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            EventValues.registerEventValue(WitchThrowPotionEvent.class, ItemType.class, new Getter<ItemType, WitchThrowPotionEvent>() {
                @Override
                public ItemType get(WitchThrowPotionEvent e) {
                    ItemStack item = e.getPotion();
                    ItemType it = new ItemType(item);
                    return it;
                }
            }, 0);
            Skript.registerEvent("Player Change Armor", SimpleEvent.class, PlayerArmorChangeEvent.class, "player change armor")
                    .description("Called when the player themselves change their armor items")
                    .examples("on player change armor:",
                            "\tcancel event",
                            "\tset helmet of player to pumpkin")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            EventValues.registerEventValue(PlayerArmorChangeEvent.class, ItemType.class, new Getter<ItemType, PlayerArmorChangeEvent>() {
                @Override
                public ItemType get(PlayerArmorChangeEvent e) {
                    ItemStack item = e.getOldItem();
                    if (item != null) return new ItemType(item);
                    return null;
                }
            }, -1);
            EventValues.registerEventValue(PlayerArmorChangeEvent.class, ItemType.class, new Getter<ItemType, PlayerArmorChangeEvent>() {
                @Override
                public ItemType get(PlayerArmorChangeEvent e) {
                    ItemStack item = e.getNewItem();
                    if (item != null) return new ItemType(item);
                    return null;
                }
            }, 0);
            EventValues.registerEventValue(PlayerArmorChangeEvent.class, ItemType.class, new Getter<ItemType, PlayerArmorChangeEvent>() {
                @Override
                public ItemType get(PlayerArmorChangeEvent e) {
                    ItemStack item = e.getNewItem();
                    if (item != null) return new ItemType(item);
                    return null;
                }
            }, 1);
            Skript.registerEvent("Player Pickup Experience Orb", SimpleEvent.class, PlayerPickupExperienceEvent.class, "player pickup (experience|xp) [orb]")
                    .description("Fired when a player is attempting to pick up an experience orb")
                    .examples("on player pickup xp:",
                            "\tadd 10 to level of player")
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            Skript.registerEvent("Player Ready Arrow", SimpleEvent.class, PlayerReadyArrowEvent.class, "player ready arrow")
                    .description("Called when a player is firing a bow and the server is choosing an arrow to use.")
                    .examples("ToDo") // TODO need to figure out arrow stuff here
                    .requiredPlugins("Paper 1.12.2+")
                    .since("1.0.0");
            EventValues.registerEventValue(PlayerReadyArrowEvent.class, ItemType.class, new Getter<ItemType, PlayerReadyArrowEvent>() {
                @Override
                public ItemType get(PlayerReadyArrowEvent e) {
                    return new ItemType(e.getArrow());
                }
            }, 0);
        }


        // 1.13.2+ EVENTS
        if (Skript.isRunningMinecraft(1, 13, 2)) {
            Skript.registerEvent("Anvil Damage", SimpleEvent.class, AnvilDamagedEvent.class, "anvil damag(e|ing)")
                    .description("Called when an anvil is damaged from being used")
                    .examples("on anvil damage:",
                            "\tcancel event")
                    .requiredPlugins("Paper 1.13.2+")
                    .since("1.0.0");
            EventValues.registerEventValue(AnvilDamagedEvent.class, Location.class, new Getter<Location, AnvilDamagedEvent>() {
                @Override
                public Location get(AnvilDamagedEvent e) {
                    return e.getInventory().getLocation();
                }
            }, 0);
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
                    .examples("ToDo") // TODO dunno what to do here
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
            EventValues.registerEventValue(PlayerElytraBoostEvent.class, ItemType.class, new Getter<ItemType, PlayerElytraBoostEvent>() {
                @Override
                public ItemType get(PlayerElytraBoostEvent e) {
                    return new ItemType(e.getItemStack());
                }
            }, 0);


        }
    }


}
