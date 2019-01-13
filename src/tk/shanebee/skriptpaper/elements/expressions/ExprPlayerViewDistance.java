package tk.shanebee.skriptpaper.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Getter;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("View Distance of Player")
@Description("Get and set the view distance for players. Reset will set the view distance to match the server's view distance.")
@Examples({"send \"View: %view distance of player%\"" +
        "set {_view} to view distance of player" +
        "set view distance of player to 10" +
        "set view distance of all players to 5" +
        "reset view distance of all players"})
@RequiredPlugins("Paper 1.13.2+")
@Since("1.3.0")
public class ExprPlayerViewDistance extends PropertyExpression<Player, Integer> {

    static {
        if (Skript.isRunningMinecraft(1,13,2)) {
            register(ExprPlayerViewDistance.class, Integer.class, "[client] view distance", "players");
        }
    }

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setExpr((Expression<Player>) exprs[0]);
        return true;
    }

    @Override
    protected Integer[] get(Event event, Player[] players) {
        return get(players, new Getter<Integer, Player>() {
            @Override
            public Integer get(Player player) {
                return player.getViewDistance();
            }
        });
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(ChangeMode mode) {
        if (mode == ChangeMode.SET || mode == ChangeMode.RESET) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }

    @Override
    public void change(final Event e, final @Nullable Object[] delta, final ChangeMode mode) {
        double d = delta == null ? 0 : ((Number) delta[0]).doubleValue();
        int view = ((Double) d).intValue();
        switch (mode) {
            case SET:
                for (Player p : getExpr().getArray(e)) {
                    p.setViewDistance(view);
                }
                break;
            case RESET:
                for (Player p :getExpr().getArray(e)) {
                    view = Bukkit.getServer().getViewDistance();
                    p.setViewDistance(view);
                }
        }
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "view distance of " + getExpr().toString(e, debug);
    }

}
