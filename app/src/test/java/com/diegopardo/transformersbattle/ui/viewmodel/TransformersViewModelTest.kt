package com.diegopardo.transformersbattle.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.diegopardo.transformersbattle.model.pojo.Transformer
import com.diegopardo.transformersbattle.repository.TransformersRepository
import com.diegopardo.transformersbattle.ui.helper.BattleHelper
import com.diegopardo.transformersbattle.utils.*
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransformersViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var transformersRepository: TransformersRepository
    @Mock
    lateinit var battleHelper: BattleHelper
    @Mock
    lateinit var transformersObserver: Observer<MutableList<Transformer>>
    @Mock
    lateinit var newTransformerObserver: Observer<Transformer>
    @Mock
    lateinit var updatedTransformerObserver: Observer<Transformer>
    @Mock
    lateinit var deletedTransformerObserver: Observer<Transformer>
    @Mock
    lateinit var battleResultsObserver: Observer<BattleHelper.BattleResults>

    lateinit var transformersViewModel: TransformersViewModel

    //mocks
    private val transformers = getTransformers()
    private val transformerDTOToCreate = getTransformerDTOToCreate()
    private val createdTransformer = getCreatedTransformer()
    private val transformerDTOToUpdate = getTransformerDTOToUpdate()
    private val updatedTransformer = getUpdatedTransformer()
    private val battleResults = getBattleResults()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        transformersViewModel = TransformersViewModel(transformersRepository, battleHelper)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getting transformers then items should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersRepository.getTransformers()).thenReturn(transformers)
            transformersViewModel.transformerList.observeForever(transformersObserver)

            transformersViewModel.getTransformers()

            verify(transformersObserver).onChanged(transformers)
            transformersViewModel.transformerList.value?.isEmpty()?.let { assertFalse(it) }

            transformersViewModel.transformerList.removeObserver(transformersObserver)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when creating transformer then item should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersRepository.createTransformer(transformerDTOToCreate)).thenReturn(createdTransformer)
            transformersViewModel.newTransformer.observeForever(newTransformerObserver)

            transformersViewModel.createTransformer(transformerDTOToCreate)

            verify(newTransformerObserver).onChanged(createdTransformer)
            assertNotNull(transformersViewModel.newTransformer.value)

            transformersViewModel.newTransformer.removeObserver(newTransformerObserver)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when updating transformer then item should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersRepository.updateTransformer(transformerDTOToUpdate)).thenReturn(updatedTransformer)
            transformersViewModel.updatedTransformer.observeForever(updatedTransformerObserver)

            transformersViewModel.updateTransformer(transformerDTOToUpdate)

            verify(updatedTransformerObserver).onChanged(updatedTransformer)
            assertNotNull(transformersViewModel.updatedTransformer.value)

            transformersViewModel.newTransformer.removeObserver(updatedTransformerObserver)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when deleting transformer then item should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersRepository.deleteTransformer("transformerId")).thenReturn(true)
            transformersViewModel.deletedTransformer.observeForever(deletedTransformerObserver)

            transformersViewModel.deleteTransformer("transformerId")

            verify(deletedTransformerObserver).onChanged(Transformer.newEmptyInstanceWithId("transformerId"))
            assertNotNull(transformersViewModel.deletedTransformer.value)

            transformersViewModel.deletedTransformer.removeObserver(deletedTransformerObserver)
        }

//    @ExperimentalCoroutinesApi
//    @Test
//    fun `when waging a battle then results should be returned`() =
//        testCoroutineRule.runBlockingTest {
//            whenever(battleHelper.wageBattle(mutableListOf())).thenReturn(battleResults)
//            transformersViewModel.battleResults.observeForever(battleResultsObserver)
//
//            transformersViewModel.wageBattle()
//
//            verify(battleResultsObserver).onChanged(battleResults)
//            assertNotNull(transformersViewModel.battleResults.value)
//
//            transformersViewModel.battleResults.removeObserver(battleResultsObserver)
//        }
}