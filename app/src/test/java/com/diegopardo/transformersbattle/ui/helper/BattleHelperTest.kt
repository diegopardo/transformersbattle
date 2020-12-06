package com.diegopardo.transformersbattle.ui.helper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegopardo.transformersbattle.utils.TestCoroutineRule
import com.diegopardo.transformersbattle.utils.getTransformersForBattle
import com.diegopardo.transformersbattle.utils.getTransformersResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BattleHelperTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    // Mocks
    private val transformersForBattle = getTransformersForBattle()

    @ExperimentalCoroutinesApi
    @Test
    fun `when getting transformers then items should be returned`() =
        testCoroutineRule.runBlockingTest {
            val battleHelper = BattleHelper()
            val battleResults = battleHelper.wageBattle(transformersForBattle)
            assertEquals(1, battleResults.fightCount)
            assertEquals("Decepticons", battleResults.getWinningTeam())
            assertEquals("[Soundwave]", battleResults.getWinningTeamMembers())
            assertEquals("Autobots", battleResults.getLosingTeam())
            assertEquals("[Hubcap]", battleResults.getLosingTeamMembers())
        }
}