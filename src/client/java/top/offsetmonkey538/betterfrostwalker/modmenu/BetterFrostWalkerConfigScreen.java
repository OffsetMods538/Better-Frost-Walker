package top.offsetmonkey538.betterfrostwalker.modmenu;

import com.mojang.serialization.Codec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import top.offsetmonkey538.betterfrostwalker.BetterFrostWalker;
import top.offsetmonkey538.betterfrostwalker.config.BetterFrostWalkerConfig;
import top.offsetmonkey538.monkeylib538.config.ConfigManager;

import java.util.List;

import static top.offsetmonkey538.betterfrostwalker.BetterFrostWalker.config;

public class BetterFrostWalkerConfigScreen extends GameOptionsScreen {
    private OptionListWidget list;

    public BetterFrostWalkerConfigScreen(Screen parent) {
        super(parent, MinecraftClient.getInstance().options, Text.translatable("better_frost_walker.options"));
    }

    @Override
    protected void init() {
        this.list = new OptionListWidget(this.client, this.width, this.height - 64, 32, 25);
        this.list.addAll(
                new SimpleOption[] {
                        new SimpleOption<>(
                                "better_frost_walker.options.requiredLevel",
                                SimpleOption.constantTooltip(Text.translatable("better_frost_walker.options.tooltip.requiredLevel")),
                                ((optionText, value) -> value == 0 ? Text.of("Both") : Text.of(String.valueOf(value))),
                                new SimpleOption.PotentialValuesBasedCallbacks<>(List.of(0, 1, 2), Codec.INT),
                                config.requiredLevel,
                                value -> config.requiredLevel = value
                        ),
                        new SimpleOption<>(
                                "better_frost_walker.options.canStandOnPowderedSnow",
                                SimpleOption.constantTooltip(Text.translatable("better_frost_walker.options.tooltip.canStandOnPowderedSnow")),
                                ((optionText, value) -> value ? ScreenTexts.YES : ScreenTexts.NO),
                                SimpleOption.BOOLEAN,
                                config.canStandOnPowderedSnow,
                                value -> config.canStandOnPowderedSnow = value
                        )
                }
        );
        this.addSelectableChild(this.list);

        this.addDrawableChild(
                ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
                    ConfigManager.save(config, BetterFrostWalker.LOGGER::error);
                    this.client.setScreen(this.parent);
                })
                        .position((this.width / 4) * 3 - 50, this.height - 27)
                        .size(100, 20)
                        .build()
        );

        this.addDrawableChild(
                ButtonWidget.builder(ScreenTexts.CANCEL, (button) -> {
                    config = ConfigManager.load(config, BetterFrostWalker.LOGGER::error);
                    this.client.setScreen(this.parent);
                })
                        .position(this.width / 2 - 50, this.height - 27)
                        .size(100, 20)
                        .build()
        );

        this.addDrawableChild(
                ButtonWidget.builder(Text.translatable("better_frost_walker.options.default"), (button) -> {
                    config = new BetterFrostWalkerConfig();
                    this.clearAndInit();
                })
                        .position(this.width / 4 - 50, this.height - 27)
                        .size(100, 20)
                        .tooltip(Tooltip.of(Text.translatable("better_frost_walker.options.default.tooltip").withColor(Formatting.RED.getColorValue())))
                        .build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.list.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 5, 0xffffff);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackgroundTexture(context);
    }

    @Override
    public void removed() {
        ConfigManager.save(config, BetterFrostWalker.LOGGER::error);
    }
}
