package ir.heydarii.musicmanager.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.heydarii.musicmanager.base.BaseApplication
import ir.heydarii.musicmanager.pojos.ArtistResponseModel
import ir.heydarii.musicmanager.repository.di.DaggerDataProviderComponent
import ir.heydarii.musicmanager.repository.networkinteractor.NetworkInteractor
import javax.inject.Inject

class DataRepository @Inject constructor() {


    init {
        DaggerDataProviderComponent.builder().retrofitComponent(BaseApplication.getRetrofitComponent()).build().inject(this)
    }

    @Inject
    lateinit var network: NetworkInteractor

    fun getArtistName(artistName: String, page: Int, apiKey: String): Observable<ArtistResponseModel> {
        return network.getArtistsName(artistName, page, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}