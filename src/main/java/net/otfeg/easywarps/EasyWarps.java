/*
 * Copyright (c) 2015 OTFEG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.otfeg.easywarps;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class EasyWarps extends JavaPlugin {
    private HashMap<String, Long> recentlyDamaged = new HashMap<String, Long>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    public HashMap<String, Long> getRecentlyDamaged() {
        return this.recentlyDamaged;
    }

    public void addToRecentlyDamaged(String playerName, long time) {
        this.recentlyDamaged.put(playerName, time);
    }

    public boolean wasDamagedRecently(Player player) {
        if ((System.currentTimeMillis() - recentlyDamaged.get(player.getName()) >= getConfig().getInt("pvptimeout"))) {
            recentlyDamaged.remove(player.getName());
            return false;
        }
        return true;
    }
}
