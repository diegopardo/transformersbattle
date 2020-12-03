package com.diegopardo.transformersbattle.model.dto

import com.diegopardo.transformersbattle.model.pojo.Transformer

data class TransformerDTO(
    val id: Int,
    val name: String,
    val team: Transformer.Team,
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
    fun toTransformer(): Transformer {
        return Transformer(
                this.id,
                this.name,
                this.team,
                this.strength,
                this.intelligence,
                this.speed,
                this.endurance,
                this.rank,
                this.courage,
                this.firepower,
                this.skill,
                this.team_icon
        )
    }
}