package dev.isxander.yacl.gui.controllers;

import dev.isxander.yacl.api.NameableEnum;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.gui.controllers.cycling.CyclingListController;
import net.minecraft.text.Text;
import net.minecraft.util.TranslatableOption;

import java.util.Arrays;
import java.util.function.Function;

@Deprecated
public class EnumController<T extends Enum<T>> extends CyclingListController<T> {
    public static <T extends Enum<T>> Function<T, Text> getDefaultFormatter() {
        return value -> {
            if (value instanceof NameableEnum nameableEnum)
                return nameableEnum.getDisplayName();
            if (value instanceof TranslatableOption translatableOption)
                return translatableOption.getText();
            return Text.of(value.toString());
        };
    }

    public EnumController(Option<T> option) {
        this(option, getDefaultFormatter());
    }

    public EnumController(Option<T> option, Function<T, Text> valueFormatter) {
        this(option, valueFormatter, option.typeClass().getEnumConstants());
    }

    public EnumController(Option<T> option, Function<T, Text> valueFormatter, T[] availableValues) {
        super(option, Arrays.asList(availableValues), valueFormatter);
    }
}
