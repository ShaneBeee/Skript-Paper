package tk.shanebee.skriptpaper.elements.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.skript.registrations.Classes;
import ch.njol.util.Kleenean;
import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.lang.reflect.Array;

@Name("Projectile Collision Target")
@Description("The entity that is hit in a projectile collide event")
@Examples({"on projectile collide:",
        "\tif projectile target is a sheep:",
        "\t\tcancel event"})
@Events("projectile collide")
@RequiredPlugins("Paper 1.11.2+")
@Since("1.1.0")
public class ExprProjectileTarget extends SimpleExpression<Entity> {

    static {
        if(Skript.isRunningMinecraft(1,11,2)) {
            Skript.registerExpression(ExprProjectileTarget.class, Entity.class, ExpressionType.SIMPLE, "[the] projectile target [<(.+)>]");
        }
    }

    @SuppressWarnings("null")
    private EntityData<?> type;

    @Override
    public boolean init(final Expression<?>[] expressions, final int matchedPattern, final Kleenean isDelayed, final ParseResult parser) {
        if(!ScriptLoader.isCurrentEvent(ProjectileCollideEvent.class)) {
            Skript.error("The expression 'projectile target' can only be used in a projectile collide event", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        final String type = parser.regexes.size() == 0 ? null : parser.regexes.get(0).group();
        if (type == null) {
            this.type = EntityData.fromClass(Entity.class);
        } else {
            final EntityData<?> t = EntityData.parse(type);
            if (t == null) {
                Skript.error("'" + type + "' is not an entity type", ErrorQuality.NOT_AN_EXPRESSION);
                return false;
            }
            this.type = t;
        }
        return true;
    }

    @Override
    @Nullable
    protected Entity[] get(final Event e) {
        final Entity[] one = (Entity[]) Array.newInstance(type.getType(), 1);
        final Entity entity = ((ProjectileCollideEvent) e).getCollidedWith();

        if (type.isInstance(entity)) {
            one[0] = entity;
            return one;
        }
        return null;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return type.getType();
    }

    @Override
    public String toString(final @Nullable Event e, final boolean debug) {
        if (e == null)
            return "the attacked " + type;
        return Classes.getDebugMessage(getSingle(e));
    }

    @Override
    public boolean isSingle() {
        return true;
    }
}
