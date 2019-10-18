package tk.shanebee.skriptpaper.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import javax.annotation.Nullable;

@Name("Entity Origin")
@Description("The location where an entity originates from (Based on testing, " +
        "entities spawned during chunk generation do not have an origin)")
@Examples({"loop all entities in radius 10 around player:",
        "\tif loop-entity is not a player:",
        "\t\tteleport loop-entity to origin of loop-entity"})
@RequiredPlugins("Paper 1.13.2+")
@Since("1.1.0")
public class ExprEntityOrigin extends SimplePropertyExpression<Entity, Location> {
    static {
        if (Skript.isRunningMinecraft(1, 13, 2)) {
            register(ExprEntityOrigin.class, Location.class, "origin[al] [location]", "entities");
        } }

    @Override
    @Nullable
    public Location convert(final Entity t) {
        return t.getOrigin();
    }

    @Override
    protected String getPropertyName() {
        return "entity origin";
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }
}
