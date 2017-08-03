package com.qyihu.nicerom.utils;

import com.qyihu.nicerom.model.RomSummary;
import com.qyihu.nicerom.model.Rom;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Container {

    public static BlockingQueue<RomSummary> romSummaries = new ArrayBlockingQueue<>(50000);

    public static BlockingQueue<Rom> romsQueue = new ArrayBlockingQueue<>(50000);

    public static final List<String> romTypes = Arrays.asList("amiga-500", "dreamcast", "gamecube", "game-gear",
            "gameboy-advance", "gameboy-color", "mame", "nintendo", "nintendo-64", "nintendo-ds", "playstation", "playstation-2",
            "playstation-portable", "sega-genesis", "super-nintendo");
}
