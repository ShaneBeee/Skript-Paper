package tk.shanebee.skriptpaper.elements.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import com.destroystokyo.paper.event.entity.WitchConsumePotionEvent;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

@Name("Consumed Potion")
@Description("The potion that is consumed in a 'Witch Consumed Potion' event.")
@Examples({"on witch consume potion:", "\tset witch's consumed potion to strong potion of instant damage"})
@Events("Witch Consume Potion")
@RequiredPlugins("Paper 1.12.2+")
@Since("1.1.0")
public class ExprConsumedPotion extends SimpleExpression<ItemStack> {
    static {
        if (Skript.isRunningMinecraft(1, 12, 2)) {
            Skript.registerExpression(ExprConsumedPotion.class, ItemStack.class, ExpressionType.SIMPLE,"[the] witch['s] (consumed|drank) potion");
        }
    }

    @Override
    protected ItemStack[] get(Event e) {
        return new ItemStack[] {((WitchConsumePotionEvent) e).getPotion()};
    }

    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET)
            return new Class[] {ItemStack.class};
        return null;
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if (!ScriptLoader.isCurrentEvent(WitchConsumePotionEvent.class)) {
            Skript.error("The expression 'consumed potion' may only be used in Witch Consume Potion events", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        return true;
    }

    @Override
    public void change(final Event e, final @Nullable Object[] delta, final Changer.ChangeMode mode) {
        ItemStack item = delta == null ? null : ((ItemStack) delta[0]);

        switch (mode) {
            case SET:
                ((WitchConsumePotionEvent) e).setPotion(item);
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