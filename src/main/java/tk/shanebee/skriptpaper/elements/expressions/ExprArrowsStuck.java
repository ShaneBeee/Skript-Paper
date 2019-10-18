package tk.shanebee.skriptpaper.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Arrows Stuck")
@Description("The number of arrows stuck in an entity (based on testing this only seems to work for a player)")
@Examples({"set arrows stuck of player to 10",
        "add 10 to arrows stuck of player",
        "remove 10 from player's stuck arrows",
        "delete arrows stuck of player"})
@RequiredPlugins("Paper 1.9.4+")
@Since("1.1.0")
public class ExprArrowsStuck extends SimplePropertyExpression<LivingEntity, Integer> {
    static {
        if (Skript.isRunningMinecraft(1, 9, 4)) {
            register(ExprArrowsStuck.class, Integer.class, "[number of] (arrows stuck|stuck arrows)", "livingentity");
        }
    }

    @Override
    @Nullable
    public Integer convert(final LivingEntity t) {
        return t.getArrowsStuck();

    }

    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (!( mode == ChangeMode.REMOVE_ALL))
            return new Class[] {Number.class};
        return null;
    }

    @Override
    public void change(final Event e, final @Nullable Object[] delta, final ChangeMode mode) {
        int d = delta == null ? 0 : ((Number) delta[0]).intValue();
        for (final LivingEntity en : getExpr().getArray(e)) {
            assert en != null : getExpr();
            switch (mode) {
                case SET:
                    en.setArrowsStuck(d);
                    break;
                case REMOVE:
                    d = -d;
                case ADD:
                    en.setArrowsStuck(en.getArrowsStuck() + d);
                    break;
                case RESET:
                case DELETE:
                    en.setArrowsStuck(0);
                    break;
                case REMOVE_ALL:
                    assert false;
            }
        }
    }

    @Override
    protected String getPropertyName() {
        return "arrows stuck";
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
