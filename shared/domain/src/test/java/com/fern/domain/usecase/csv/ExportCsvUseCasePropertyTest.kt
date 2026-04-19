package com.fern.domain.usecase.csv

import arrow.core.Some
import com.fern.base.TestDispatchersProvider
import com.fern.base.time.impl.TestTimeConverter
import com.fern.data.file.FileSystem
import com.fern.data.model.Transaction
import com.fern.data.model.getFromAccount
import com.fern.data.model.getToAccount
import com.fern.data.model.testing.account
import com.fern.data.model.testing.category
import com.fern.data.model.testing.transaction
import com.fern.data.repository.AccountRepository
import com.fern.data.repository.CategoryRepository
import com.fern.data.repository.TransactionRepository
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.next
import io.kotest.property.checkAll
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ExportCsvUseCasePropertyTest {

    private val accountRepository = mockk<AccountRepository>()
    private val categoryRepository = mockk<CategoryRepository>(relaxed = true)
    private val transactionRepository = mockk<TransactionRepository>()
    private val fileSystem = mockk<FileSystem>()
    private val timeConverter = TestTimeConverter()

    private lateinit var useCase: ExportCsvUseCase

    @Before
    fun setup() {
        useCase = ExportCsvUseCase(
            accountRepository = accountRepository,
            categoryRepository = categoryRepository,
            transactionRepository = transactionRepository,
            dispatchers = TestDispatchersProvider,
            fileSystem = fileSystem,
            timeConverter = timeConverter
        )
    }

    @Test
    fun `property - num of row and columns matches the format`() = runTest {
        checkAll(Arb.list(Arb.transaction())) { trns ->
            // given
            val accounts = trns.flatMap {
                listOfNotNull(it.getFromAccount(), it.getToAccount())
            }.map {
                Arb.account(accountId = Some(it)).next()
            }
            coEvery { accountRepository.findAll() } returns accounts
            val categories = trns
                .mapNotNull(Transaction::category)
                .map {
                    Arb.category(categoryId = Some(it)).next()
                }.run {
                    if (isNotEmpty()) {
                        drop(Arb.int(indices).bind()).shuffled()
                    } else {
                        this
                    }
                }
            coEvery { categoryRepository.findAll() } returns categories

            // when
            val csv = useCase.exportCsv { trns }

            // then
            val rows = ReadCsvUseCase().readCsv(csv)
            rows.size shouldBe trns.size + 1 // +1 for the header
            rows.forEach { row ->
                // Matches the expected # of columns
                val hasExpectedNumOfColumns = row.size == IvyCsvRow.Columns.size
                if (!hasExpectedNumOfColumns) {
                    println("(${row.size} cols) $row")
                }
                hasExpectedNumOfColumns shouldBe true
            }
        }
    }
}