package com.fern.legacy

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.fern.base.legacy.SharedPrefs
import com.fern.data.DataObserver
import com.fern.data.DataWriteEvent
import com.fern.data.db.dao.read.UserDao
import com.fern.data.db.dao.write.WriteBudgetDao
import com.fern.data.db.dao.write.WriteLoanDao
import com.fern.data.db.dao.write.WriteLoanRecordDao
import com.fern.data.db.dao.write.WritePlannedPaymentRuleDao
import com.fern.data.db.dao.write.WriteSettingsDao
import com.fern.data.repository.AccountRepository
import com.fern.data.repository.CategoryRepository
import com.fern.data.repository.ExchangeRatesRepository
import com.fern.data.repository.TagRepository
import com.fern.data.repository.TransactionRepository
import com.fern.legacy.utils.ioThread
import com.fern.navigation.MainScreen
import com.fern.navigation.Navigation
import com.fern.navigation.OnboardingScreen
import javax.inject.Inject

@Deprecated("Migrate to an UseCase in the domain layer.")
class LogoutLogic @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val navigation: Navigation,
    private val dataObserver: DataObserver,
    private val dataStore: DataStore<Preferences>,
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository,
    private val tagRepository: TagRepository,
    private val userDao: UserDao,
    private val writeSettingsDao: WriteSettingsDao,
    private val writePlannedPaymentRuleDao: WritePlannedPaymentRuleDao,
    private val writeBudgetDao: WriteBudgetDao,
    private val writeLoanDao: WriteLoanDao,
    private val writeLoanRecordDao: WriteLoanRecordDao,
    private val exchangeRatesRepository: ExchangeRatesRepository
) {
    suspend fun logout() {
        ioThread {
            deleteAllData()
            dataStore.edit {
                it.clear()
            }
            sharedPrefs.removeAll()
        }

        dataObserver.post(DataWriteEvent.AllDataChange)
        navigation.resetBackStack()
        navigation.navigateTo(OnboardingScreen)
    }

    private suspend fun deleteAllData() {
        accountRepository.deleteAll()
        transactionRepository.deleteAll()
        categoryRepository.deleteAll()
        tagRepository.deleteAll()
        writeSettingsDao.deleteAll()
        writePlannedPaymentRuleDao.deleteAll()
        userDao.deleteAll()
        writeBudgetDao.deleteAll()
        writeLoanDao.deleteAll()
        writeLoanRecordDao.deleteAll()
        exchangeRatesRepository.deleteAll()
    }

    suspend fun cloudLogout() {
        navigation.navigateTo(MainScreen)
    }
}
