package tk.shanebee.skriptpaper.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Turtle;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Turtle Home")
@Description("The home of a turtle")
@Examples("")
@RequiredPlugins("Paper 1.13.2+")
@Since("1.1.0")
public class ExprTurtleHome extends SimplePropertyExpression<LivingEntity, Location> {
    static {
        if (Skript.isRunningMinecraft(1, 13, 2)) {
            register(ExprTurtleHome.class, Location.class, "home[s] [location[s]]", "livingentity");
        }
    }

    @Override
    @Nullable
    public Location convert(final LivingEntity t) {
        if (t instanceof Turtle) {
            return ((Turtle) t).getHome();
        } else {
            return null;
        }
    }

    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.DELETE)
            return new Class[] {Location.class};
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        if (delta == null) {
            for (final LivingEntity t : getExpr().getArray(e)) {
                if (!(t instanceof Turtle)) return;
                ((Turtle) t).setHome(null);
            }
        } else {
            final Location l = (Location) delta[0];
            for (final LivingEntity t : getExpr().getArray(e)) {
                if (!(t instanceof Turtle)) return;
                ((Turtle) t).setHome(l);
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "turtle home";
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }
}
