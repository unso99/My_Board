package com.myboard.presentation.di

import com.myboard.data.source.remote.api.ContentService
import com.myboard.domain.repository.ContentRepository
import com.myboard.domain.repository.ContentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesContentRepository(
        contentService: ContentService
    ) : ContentRepository = ContentRepositoryImpl(contentService)
}