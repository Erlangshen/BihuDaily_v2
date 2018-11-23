package com.erlangshen.di.module

import com.erlangshen.mvp.model.LatestData
import dagger.Module
import dagger.Provides

@Module
class LatestDataModule {
    @Provides
    fun provideLatestData(): LatestData = LatestData()

    @Provides
    fun provideStoriesEntity(): LatestData.StoriesEntity = LatestData.StoriesEntity()
}