package com.diegopardo.transformersbattle.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegopardo.transformersbattle.api.service.TransformersService
import com.diegopardo.transformersbattle.utils.*
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class TransformersRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var transformersService: TransformersService

    private lateinit var transformersRepository: TransformersRepository

    //mocks
    private val transformersResponse = getTransformersResponse()

    private val transformerDTOToCreate = getTransformerDTOToCreate()
    private val createdTransformerResponse = createdTransformerResponse()

    private val transformerDTOToUpdate = getTransformerDTOToUpdate()
    private val updatedTransformerResponse = updatedTransformerResponse()


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        transformersRepository = TransformersRepository(transformersService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getting transformers then items should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersService.getTransformers()).doReturn(transformersResponse)

            val fetchedTransformers = transformersRepository.getTransformers()

            verify(transformersService).getTransformers()
            assertNotNull(fetchedTransformers)
            val expectedTransformers = transformersResponse.body()?.transformers?.map { it.toTransformer() }
            assertEquals(expectedTransformers, fetchedTransformers)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when getting transformers and http status code different than 200, then null should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersService.getTransformers()).doReturn(Response.error(500, ResponseBody.create(null, "")))

            val fetchedTransformers = transformersRepository.getTransformers()

            verify(transformersService).getTransformers()
            assertNull(fetchedTransformers)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when creating transformer then item should be returned with id and team icon`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersService.createTransformer(transformerDTOToCreate)).doReturn(createdTransformerResponse)

            val createdTransformer = transformersRepository.createTransformer(transformerDTOToCreate)

            verify(transformersService).createTransformer(transformerDTOToCreate)
            assertNotNull(createdTransformer)
            assertNotNull(createdTransformer?.id)
            assertNotNull(createdTransformer?.team_icon)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when creating transformer and http status code different than 201, then null should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersService.createTransformer(transformerDTOToCreate)).doReturn(Response.error(500, ResponseBody.create(null, "")))

            val createdTransformer = transformersRepository.createTransformer(transformerDTOToCreate)

            verify(transformersService).createTransformer(transformerDTOToCreate)
            assertNull(createdTransformer)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when updating transformer then item should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersService.updateTransformer(transformerDTOToUpdate)).doReturn(updatedTransformerResponse)

            val updatedTransformer = transformersRepository.updateTransformer(transformerDTOToUpdate)

            verify(transformersService).updateTransformer(transformerDTOToUpdate)
            assertNotNull(updatedTransformer)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when updating transformer and http status code different than 200, then null should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersService.updateTransformer(transformerDTOToUpdate)).doReturn(Response.error(500, ResponseBody.create(null, "")))

            val updatedTransformer = transformersRepository.updateTransformer(transformerDTOToUpdate)

            verify(transformersService).updateTransformer(transformerDTOToUpdate)
            assertNull(updatedTransformer)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when deleting transformer then true should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersService.deleteTransformer("transformerId")).doReturn(Response.success(204, voidFunction()))

            val deletedTransformer = transformersRepository.deleteTransformer("transformerId")

            verify(transformersService).deleteTransformer("transformerId")
            assertNotNull(deletedTransformer)
            assertTrue(deletedTransformer)
        }

    private fun voidFunction(): Void? {
        return null
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when deleting transformer and http status code different than 204, then false should be returned`() =
        testCoroutineRule.runBlockingTest {
            whenever(transformersService.deleteTransformer("transformerId")).doReturn(Response.error(500, ResponseBody.create(null, "")))

            val deletedTransformer = transformersRepository.deleteTransformer("transformerId")

            verify(transformersService).deleteTransformer("transformerId")
            assertNotNull(deletedTransformer)
            assertFalse(deletedTransformer)
        }
}