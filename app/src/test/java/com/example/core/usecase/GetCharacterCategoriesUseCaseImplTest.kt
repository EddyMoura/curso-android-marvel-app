package com.example.core.usecase

import com.example.core.data.repository.CharactersRepository
import com.example.core.usecase.base.ResultStatus
import com.example.testing.MainCoroutineRule
import com.example.testing.model.CharacterFactory
import com.example.testing.model.ComicFactory
import com.example.testing.model.EventFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterCategoriesUseCaseImplTest {

    //Qual classe eu quero testar? Declaro e Instâncio. Verifico quais dependências ela precisa e monto os mocks

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CharactersRepository

    private val characters = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)
    private val comics = listOf(ComicFactory().create(ComicFactory.FakeComic.FakeComic1))
    private val events = listOf(EventFactory().create(EventFactory.FakeEvent.FakeEvent1))

    private lateinit var getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase

    @Before
    fun setUp() {
        getCharacterCategoriesUseCase = GetCharacterCategoriesUseCaseImpl(
            repository,
            mainCoroutineRule.textDispatcherProvider
        )
    }

    @Test
    fun `should return Success from ResultStatus when get both requests return success`() =
        runTest {
            // Arrange
            whenever(repository.getComics(characters.id)).thenReturn(comics)
            whenever(repository.getEvents(characters.id)).thenReturn(events)

            // Act
            val result = getCharacterCategoriesUseCase
                .invoke(
                    GetCharacterCategoriesUseCase.GetCategoriesParams(characters.id)
                )

            // Assert
            val resulList = result.toList()
            assertEquals(ResultStatus.Loading, resulList[0])
            assertTrue(resulList[1] is ResultStatus.Success)
        }

    @Suppress("TooGenericExceptionThrown")
    @Test
    fun `should return Error from ResultStatus when get events request returns error`() =
        runTest {
            // Arrange
            whenever(repository.getComics(characters.id)).thenAnswer { throw Throwable() }

            // Act
            val result = getCharacterCategoriesUseCase
                .invoke(
                    GetCharacterCategoriesUseCase.GetCategoriesParams(characters.id)
                )

            // Assert
            val resulList = result.toList()
            assertEquals(ResultStatus.Loading, resulList[0])
            assertTrue(resulList[1] is ResultStatus.Error)
        }

    @Suppress("TooGenericExceptionThrown")
    @Test
    fun `should return Error from ResultStatus when get comics request returns error`() =
        runTest {
            // Arrange
            whenever(repository.getComics(characters.id)).thenReturn(comics)
            whenever(repository.getEvents(characters.id)).thenAnswer { throw Throwable() }

            // Act
            val result = getCharacterCategoriesUseCase
                .invoke(
                    GetCharacterCategoriesUseCase.GetCategoriesParams(characters.id)
                )

            // Assert
            val resulList = result.toList()
            assertEquals(ResultStatus.Loading, resulList[0])
            assertTrue(resulList[1] is ResultStatus.Error)
        }
}