package com.death.photonik.ui.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.death.instagram.utils.rx.SchedulerProvider
import com.death.photonik.data.repository.PhotoRepository
import com.death.photonik.ui.base.BaseActivity
import com.death.photonik.ui.home.HomeViewModel
import com.death.photonik.ui.home.HomeViewModelImpl
import com.death.photonik.ui.home.HomeActivity
import com.death.photonik.ui.home.adapter.PhotoAdapter
import com.death.photonik.utils.common.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class HomeActivityModule(private val activity: HomeActivity): BaseActivityModule(activity){

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        photoRepository: PhotoRepository
    ):HomeViewModel = ViewModelProviders.of(activity, ViewModelProviderFactory(HomeViewModelImpl::class){
        HomeViewModelImpl(schedulerProvider, compositeDisposable, photoRepository)
    }).get(HomeViewModelImpl::class.java)

    @Provides
    fun provideStaggeredGridLayoutManager(activity: BaseActivity<*, *>): StaggeredGridLayoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

    @Provides
    fun provideAdapter() = PhotoAdapter(ArrayList(), activity)
}