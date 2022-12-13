package com.example.marvelapp.framework.di

import com.example.core.usecase.GetCharacterCategoryUseCase
import com.example.core.usecase.GetCharacterCategoryUseCaseImpl
import com.example.core.usecase.GetCharactersUseCase
import com.example.core.usecase.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCharactersUseCase(useCaseImpl: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindGetComicsUseCase(
        useCaseImpl: GetCharacterCategoryUseCaseImpl
    ): GetCharacterCategoryUseCase
}