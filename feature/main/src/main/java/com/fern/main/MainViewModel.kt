package com.fern.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fern.base.legacy.SharedPrefs
import com.fern.data.repository.CurrencyRepository
import com.fern.domain.usecase.exchange.SyncExchangeRatesUseCase
import com.fern.frp.test.TestIdlingResource
import com.fern.legacy.IvyWalletCtx
import com.fern.legacy.data.model.MainTab
import com.fern.legacy.domain.deprecated.logic.AccountCreator
import com.fern.legacy.utils.asLiveData
import com.fern.legacy.utils.ioThread
import com.fern.navigation.MainScreen
import com.fern.navigation.Navigation
import com.fern.wallet.domain.deprecated.logic.model.CreateAccountData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val ivyContext: IvyWalletCtx,
    private val nav: Navigation,
    private val syncExchangeRatesUseCase: SyncExchangeRatesUseCase,
    private val accountCreator: AccountCreator,
    private val sharedPrefs: SharedPrefs,
    private val currencyRepository: CurrencyRepository,
) : ViewModel() {

    private val _currency = MutableLiveData<String>()
    val currency = _currency.asLiveData()

    fun start(screen: MainScreen) {
        nav.onBackPressed[screen] = {
            if (ivyContext.mainTab == MainTab.ACCOUNTS) {
                ivyContext.selectMainTab(MainTab.HOME)
                true
            } else {
                // Exiting (the backstack will close the app)
                false
            }
        }

        viewModelScope.launch {
            TestIdlingResource.increment()

            val baseCurrency = currencyRepository.getBaseCurrency()
            _currency.value = baseCurrency.code

            ivyContext.dataBackupCompleted =
                sharedPrefs.getBoolean(SharedPrefs.DATA_BACKUP_COMPLETED, false)

            ioThread {
                // Sync exchange rates
                syncExchangeRatesUseCase.sync(baseCurrency)
            }

            TestIdlingResource.decrement()
        }
    }

    fun selectTab(tab: MainTab) {
        ivyContext.selectMainTab(tab)
    }

    fun createAccount(data: CreateAccountData) {
        viewModelScope.launch {
            TestIdlingResource.increment()

            accountCreator.createAccount(data) {}

            TestIdlingResource.decrement()
        }
    }
}
