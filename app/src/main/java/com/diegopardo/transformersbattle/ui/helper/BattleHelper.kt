package com.diegopardo.transformersbattle.ui.helper

import com.diegopardo.transformersbattle.model.pojo.Transformer
import javax.inject.Inject

class BattleHelper @Inject constructor() {

    private var fightCount = 0
    private var destroyedAutobotCount = 0
    private var destroyedDecepticonCount = 0
    private val skippedAutobots = mutableListOf<Transformer>()
    private val skippedDecepticons = mutableListOf<Transformer>()
    private val destroyedTransformers = mutableListOf<Transformer>()
    private lateinit var autobots: MutableList<Transformer>
    private lateinit var decepticons: MutableList<Transformer>

    fun wageBattle(transformerList: MutableList<Transformer>): BattleResults {
        fightCount = 0
        destroyedAutobotCount = 0
        destroyedDecepticonCount = 0
        skippedAutobots.clear()
        skippedDecepticons.clear()

        autobots = getAutobots(transformerList)
        decepticons = getDecepticons(transformerList)

        while (autobots.size > decepticons.size) {
            skippedAutobots.add(autobots.removeAt(autobots.size - 1))
        }
        while (decepticons.size > autobots.size) {
            skippedDecepticons.add(decepticons.removeAt(decepticons.size - 1))
        }

        while (autobots.size >= 1 && decepticons.size >= 1) {
            val autobotFighter = autobots[0]
            val decepticonFighter = decepticons[0]

            // In the event either of Optimus Prime or Predaking face each other (or a duplicate of
            // each other), the game immediately ends with all competitors destroyed
            if ((autobotFighter.name.equals("Optimus Prime") || autobotFighter.name.equals("Predaking")) &&
                (decepticonFighter.name.equals("Optimus Prime") || decepticonFighter.name.equals("Predaking"))
            ) {
                transformerList?.forEach {
                    destroyedTransformers.add(it)
                }
                autobots.clear()
                decepticons.clear()
                break
            }

            // Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria
            if (autobotFighter.name.equals("Optimus Prime") || autobotFighter.name.equals("Predaking")) {
                destroyFighter(decepticonFighter, decepticons)
            } else if (decepticonFighter.name.equals("Optimus Prime") || decepticonFighter.name.equals(
                    "Predaking"
                )
            ) {
                destroyFighter(autobotFighter, autobots)
            }

            // If any fighter is down 4 or more points of courage and 3 or more points of strength
            // compared to their opponent, the opponent automatically wins the face-off regardless of
            // overall rating (opponent has ran away)
            else if (autobotFighter.courage <= decepticonFighter.courage - 4 && autobotFighter.strength <= decepticonFighter.strength - 3) {
                destroyFighter(decepticonFighter, decepticons)
            } else if (decepticonFighter.courage <= autobotFighter.courage - 4 && decepticonFighter.strength <= autobotFighter.strength - 3) {
                destroyFighter(autobotFighter, autobots)
            }

            // Otherwise, if one of the fighters is 3 or more points of skill above their opponent, they
            // win the fight regardless of overall rating
            else if (autobotFighter.skill >= decepticonFighter.skill + 3) {
                destroyFighter(decepticonFighter, decepticons)
            } else if (decepticonFighter.skill >= autobotFighter.skill + 3) {
                destroyFighter(autobotFighter, autobots)
            }

            // The winner is the Transformer with the highest overall rating
            else if (autobotFighter.getOverallRating() > decepticonFighter.getOverallRating()) {
                destroyFighter(decepticonFighter, decepticons)
            } else if (decepticonFighter.getOverallRating() > autobotFighter.getOverallRating()) {
                destroyFighter(autobotFighter, autobots)
            }

            // In the event of a tie, both Transformers are considered destroyed
            else {
                destroyFighter(autobotFighter, autobots)
                destroyFighter(decepticonFighter, decepticons)
            }
        }

        return BattleResults(
            fightCount,
            destroyedAutobotCount,
            destroyedDecepticonCount,
            skippedAutobots,
            skippedDecepticons,
            destroyedTransformers,
            autobots,
            decepticons
        )
    }

    private fun getAutobots(transformerList: MutableList<Transformer>): MutableList<Transformer> {
        val autobots =
            transformerList?.filter { transformer -> transformer.isAutobot() } as MutableList<Transformer>
        autobots.sortDescending()
        return autobots
    }

    private fun getDecepticons(transformerList: MutableList<Transformer>): MutableList<Transformer> {
        var decepticons =
            transformerList?.filter { transformer -> transformer.isDecepticon() } as MutableList<Transformer>
        decepticons.sortDescending()
        return decepticons
    }

    private fun destroyFighter(fighter: Transformer, fighters: MutableList<Transformer>) {
        destroyedTransformers.add(fighter)
        fighters.remove(fighter)
        fightCount++
        if (fighter.isAutobot()) {
            destroyedAutobotCount++
        } else {
            destroyedDecepticonCount++
        }
    }

    class BattleResults(
        val fightCount: Int,
        val destroyedAutobotCount: Int,
        val destroyedDecepticonCount: Int,
        val skippedAutobots: MutableList<Transformer>,
        val skippedDecepticons: MutableList<Transformer>,
        val destroyedTransformers: MutableList<Transformer>,
        val remainingAutobots: MutableList<Transformer>,
        val remaininDecepticons: MutableList<Transformer>,
    ) {

        fun getWinningTeam(): String {
            return if (destroyedDecepticonCount > destroyedAutobotCount) {
                "Autobots"
            } else if (destroyedAutobotCount > destroyedDecepticonCount) {
                "Decepticons"
            } else {
                "Tie"
            }
        }

        fun getWinningTeamMembers(): String {
            return if (destroyedDecepticonCount > destroyedAutobotCount) {
                (remainingAutobots + skippedAutobots).toString()
            } else if (destroyedAutobotCount > destroyedDecepticonCount) {
                (remaininDecepticons + skippedDecepticons).toString()
            } else {
                "Tie"
            }
        }

        fun getLosingTeam(): String {
            return if (destroyedDecepticonCount < destroyedAutobotCount) {
                "Autobots"
            } else if (destroyedAutobotCount < destroyedDecepticonCount) {
                "Decepticons"
            } else {
                "Tie"
            }
        }

        fun getLosingTeamMembers(): String {
            return if (destroyedDecepticonCount < destroyedAutobotCount) {
                (remainingAutobots + skippedAutobots).toString()
            } else if (destroyedAutobotCount < destroyedDecepticonCount) {
                (remaininDecepticons + skippedDecepticons).toString()
            } else {
                "Tie"
            }
        }
    }
}