package com.myboard.di

import com.myboard.data.source.local.dao.ContentDao
import com.myboard.data.source.remote.api.ContentService
import com.myboard.data.repository.ContentRepository
import com.myboard.data.repository.ContentRepositoryImpl
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
        contentService: ContentService,
        contentDao: ContentDao
    ) : ContentRepository = ContentRepositoryImpl(contentService,contentDao)
}