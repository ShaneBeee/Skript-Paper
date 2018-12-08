package tk.shanebee.skriptpaper.elements.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import com.destroystokyo.paper.event.entity.WitchThrowPotionEvent;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

@Name("Thrown Potion")
@Description("The potion that is thrown in a 'Witch Throws Potion' event. If setting, the potion must be a splash potion.")
@Examples({"on witch throws potion",
        "\tset witch's thrown potion to splash potion of speed"})
@Events("Witch Throws Potion")
@RequiredPlugins("Paper 1.12.2+")
@Since("1.1.0")
public class ExprThrownPotion extends SimpleExpression<ItemStack> {
    static {
        if (Skript.isRunningMinecraft(1, 12, 2)) {
            Skript.registerExpression(ExprThrownPotion.class, ItemStack.class, ExpressionType.SIMPLE,"witch['s] thrown potion");
        }
    }

    @Override
    protected ItemStack[] get(Event e) {
        return new ItemStack[] {((WitchThrowPotionEvent) e).getPotion()};
    }

    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.SET)
            return new Class[] {ItemStack.class};
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(WitchThrowPotionEvent.class)) {
            Skript.error("The expression 'thrown potion' may only be used in Witch Throw Potion events", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }

    @Override
    public void change(final Event e, final @Nullable Object[] delta, final ChangeMode mode) {
        ItemStack item = delta == null ? null : ((ItemStack) delta[0]);

        switch (mode) {
            case SET:
                ((WitchThrowPotionEvent) e).setPotion(item);
                break;
            case REMOVE:
            case ADD:
            case RESET:
            case DELETE:
            case REMOVE_ALL:
                assert false;
        }
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString(Event event, boolean b) {
        return null;
    }
}

