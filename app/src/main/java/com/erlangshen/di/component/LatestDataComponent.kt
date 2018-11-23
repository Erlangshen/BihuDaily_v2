package com.erlangshen.di.component

import com.erlangshen.base.BasePresenter
import com.erlangshen.base.BaseView
import com.erlangshen.di.module.LatestDataModule
import com.erlangshen.di.scope.LatestDataScope
import dagger.Component
@LatestDataScope
@Component(modules = [(LatestDataModule::class)],dependencies = [(NetworkComponent::class)])
interface LatestDataComponent {
    fun inject(basePresenter: BasePresenter<BaseView>)
}