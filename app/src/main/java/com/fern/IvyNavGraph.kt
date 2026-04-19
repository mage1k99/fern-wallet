package com.fern

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import com.fern.attributions.AttributionsScreenImpl
import com.fern.balance.BalanceScreen
import com.fern.budgets.BudgetScreen
import com.fern.categories.CategoriesScreen
import com.fern.contributors.ContributorsScreenImpl
import com.fern.disclaimer.DisclaimerScreenImpl
import com.fern.exchangerates.ExchangeRatesScreen
import com.fern.features.FeaturesScreenImpl
import com.fern.importdata.csv.CSVScreen
import com.fern.importdata.csvimport.ImportCSVScreen
import com.fern.loans.loan.LoansScreen
import com.fern.loans.loandetails.LoanDetailsScreen
import com.fern.main.MainScreen
import com.fern.navigation.AttributionsScreen
import com.fern.navigation.BalanceScreen
import com.fern.navigation.BudgetScreen
import com.fern.navigation.CSVScreen
import com.fern.navigation.CategoriesScreen
import com.fern.navigation.ContributorsScreen
import com.fern.navigation.DisclaimerScreen
import com.fern.navigation.EditPlannedScreen
import com.fern.navigation.EditTransactionScreen
import com.fern.navigation.ExchangeRatesScreen
import com.fern.navigation.FeaturesScreen
import com.fern.navigation.ImportScreen
import com.fern.navigation.LoanDetailsScreen
import com.fern.navigation.LoansScreen
import com.fern.navigation.MainScreen
import com.fern.navigation.OnboardingScreen
import com.fern.navigation.PieChartStatisticScreen
import com.fern.navigation.PlannedPaymentsScreen
import com.fern.navigation.PollScreen
import com.fern.navigation.ReleasesScreen
import com.fern.navigation.ReportScreen
import com.fern.navigation.Screen
import com.fern.navigation.SearchScreen
import com.fern.navigation.SettingsScreen
import com.fern.navigation.TransactionsScreen
import com.fern.onboarding.OnboardingScreen
import com.fern.piechart.PieChartStatisticScreen
import com.fern.planned.edit.EditPlannedScreen
import com.fern.planned.list.PlannedPaymentsScreen
import com.fern.poll.impl.ui.PollScreen
import com.fern.releases.ReleasesScreenImpl
import com.fern.reports.ReportScreen
import com.fern.search.SearchScreen
import com.fern.settings.SettingsScreen
import com.fern.transaction.EditTransactionScreen
import com.fern.transactions.TransactionsScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
@Suppress("CyclomaticComplexMethod", "FunctionNaming")
fun BoxWithConstraintsScope.IvyNavGraph(screen: Screen?) {
    when (screen) {
        null -> {
            // show nothing
        }

        is MainScreen -> MainScreen(screen = screen)
        is OnboardingScreen -> OnboardingScreen(screen = screen)
        is ExchangeRatesScreen -> ExchangeRatesScreen()
        is EditTransactionScreen -> EditTransactionScreen(screen = screen)
        is TransactionsScreen -> TransactionsScreen(screen = screen)
        is PieChartStatisticScreen -> PieChartStatisticScreen(screen = screen)
        is CategoriesScreen -> CategoriesScreen(screen = screen)
        is SettingsScreen -> SettingsScreen()
        is PlannedPaymentsScreen -> PlannedPaymentsScreen(screen = screen)
        is EditPlannedScreen -> EditPlannedScreen(screen = screen)
        is BalanceScreen -> BalanceScreen(screen = screen)
        is ImportScreen -> ImportCSVScreen(screen = screen)
        is ReportScreen -> ReportScreen(screen = screen)
        is BudgetScreen -> BudgetScreen(screen = screen)
        is LoansScreen -> LoansScreen(screen = screen)
        is LoanDetailsScreen -> LoanDetailsScreen(screen = screen)
        is SearchScreen -> SearchScreen(screen = screen)
        is CSVScreen -> CSVScreen(screen = screen)
        FeaturesScreen -> FeaturesScreenImpl()
        AttributionsScreen -> AttributionsScreenImpl()
        ContributorsScreen -> ContributorsScreenImpl()
        ReleasesScreen -> ReleasesScreenImpl()
        DisclaimerScreen -> DisclaimerScreenImpl()
        PollScreen -> PollScreen()
    }
}
