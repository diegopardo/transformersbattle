package com.diegopardo.transformersbattle.model.pojo

data class Transformer(
    val id: String?,
    val name: String,
    val team: String, //    TODO: change to enum
    val strength: Int,
    val intelligence: Int,
    val speed: Int,
    val endurance: Int,
    val rank: Int,
    val courage: Int,
    val firepower: Int,
    val skill: Int,
    val team_icon: String?,
) {
//    enum class Team(val abbreviation: String) {
//        AUTOBOT("A"),
//        DECEPTICON("B"),
//    }

    fun getOverallRating() = this.strength + this.intelligence + this.speed + this.endurance + this.firepower

    override fun equals(other: Any?): Boolean {
        return other is Transformer && id.equals(other.id)
    }
}