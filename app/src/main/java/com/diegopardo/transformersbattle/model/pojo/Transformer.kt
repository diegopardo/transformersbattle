package com.diegopardo.transformersbattle.model.pojo

class Transformer(
    val id: Int,
    val name: String,
    val team: Team,
    val strength: Int,
    val intelligence: Int,
    val speed: Int,
    val endurance: Int,
    val rank: Int,
    val courage: Int,
    val firepower: Int,
    val skill: Int,
    val team_icon: String,
) {
    enum class Team {
        AUTOBOT,
        DECEPTICON,
    }

    fun getOverallRating() = this.strength + this.intelligence + this.speed + this.endurance + this.firepower
}