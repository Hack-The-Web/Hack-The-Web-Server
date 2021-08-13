package com.hack.game.api.world.entity.player.widget

enum class ContentTabs(val displayName: String) {
    HOME("Home"),
    TASKS("Task Manager"),
    SOFTWARE("Software"),
    INTERNET("Internet"),
    LOGS("Logs"),
    HARDWARE("Hardware"),
    UNIVERSITY("University"),
    FINANCES("Finances"),
    HACKED_DATABASE("Hacked Database"),
    MISSIONS("Missions"),
    CLAN("Clan"),
    RANKING("Ranking"),
    FAME("Hall of Fame"),
    NONE(""),
    DEVELOPER("Developer Tools"),
    GAMEFRAME("game_frame");

    override fun toString(): String {
        return this.displayName
    }

    companion object {

        private val tabs = values()

        fun tab(tab: String) : ContentTabs? {
            return tabs.find { it.name == tab }
        }

    }
}
