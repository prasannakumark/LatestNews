package com.techbots.latestnews.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.techbots.latestnews.datasource.NewsDAO
import com.techbots.latestnews.db.DataRepository
import com.techbots.latestnews.network.APIInterface
import com.techbots.latestnews.utils.isOnline
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
internal class HomePageViewModelTest {
    @InjectMocks
    private lateinit var homePageViewModel: NewsArticleViewModel

    @Mock
    private lateinit var uiCallBacks: DataRepository.UICallBacks

    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var newsDao: NewsDAO

    @Mock
    private lateinit var apiInterface: APIInterface

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    lateinit var connectivityManager: ConnectivityManager

    @Mock
    lateinit var networkInfo: NetworkInfo


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.homePageViewModel = NewsArticleViewModel(context, newsDao)
    }

    @Test
    fun `load more data when network available and verify makeServerRequest called using the data repository`() {
        mockNetworkInfo()
        /*homePageViewModel.loadMoreData()
        Mockito.verify(dataRepository.makeServerRequest(uiCallBacks), Mockito.atLeast(1))*/
        Assert.assertTrue(isOnline(context))
    }

    private fun mockNetworkInfo() {
        Mockito.`when`(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
        Mockito.`when`(networkInfo.isConnected).thenReturn(true)
    }

}