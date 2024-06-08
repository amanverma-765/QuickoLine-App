package org.quickoline.dashboard.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.quickoline.dashboard.presentation.components.Category
import org.quickoline.dashboard.presentation.screens.HomeScreen
import org.quickoline.dashboard.presentation.screens.PostListScreen
import org.quickoline.dashboard.presentation.viewmodel.home.HomeViewModel
import org.quickoline.dashboard.presentation.viewmodel.userentry.UserEntryUiEvents
import org.quickoline.dashboard.presentation.viewmodel.userentry.UserEntryViewModel
import org.quickoline.utils.ApiResponse
import org.quickoline.utils.Constants
import org.quickoline.utils.canNavigate

@Serializable
object DashboardGraph

fun NavController.navigateToDashBoardGraph(navOptions: NavOptions? = null) {
    if (canNavigate()) navigate(route = DashboardGraph, navOptions = navOptions)
}

fun NavGraphBuilder.dashboardGraph(
    navigator: NavController,
    navigateToOnBoarding: () -> Unit
) {
    navigation<DashboardGraph>(startDestination = DashboardDestinations.Home) {

        composable<DashboardDestinations.Home> {

            val userEntryVm = koinViewModel<UserEntryViewModel>(
                parameters = { parametersOf(Constants.USER_ENTRY) }
            )
            val userEntryState by userEntryVm.userEntryState.collectAsState()
            val context = LocalContext.current

            LaunchedEffect(key1 = userEntryState) {

                userEntryVm.onEvent(UserEntryUiEvents.CheckIfOnBoardingIsCompleted)

                // Check the policy response state
                when (val policy = userEntryState.policyResponse) {
                    is ApiResponse.Error -> {
                        Toast.makeText(
                            context,
                            policy.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        navigateToOnBoarding()
                        return@LaunchedEffect
                    }
                    is ApiResponse.Success -> {
                        // Check the user entry response state
                        when (val entry = userEntryState.userEntryResponse) {
                            is ApiResponse.Error -> {
                                Toast.makeText(
                                    context,
                                    entry.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                navigateToOnBoarding()
                                return@LaunchedEffect
                            }

                            is ApiResponse.Success -> {
                                if (entry.data.not() || policy.data.not()) {
                                    navigateToOnBoarding()
                                }
                                return@LaunchedEffect
                            }
                            else -> return@LaunchedEffect
                        }
                    }
                    else -> return@LaunchedEffect
                }
            }

            val homeVm = viewModel<HomeViewModel>()
            val homeUiState by homeVm.homeUiState.collectAsState()
            HomeScreen(
                uiStates = homeUiState,
                uiEvents = homeVm::onEvent,
                navigateToPostListScreen = { category ->
                    if (navigator.canNavigate()) {
                        navigator.navigate(DashboardDestinations.PostList(category.name))
                    }
                }
            )
        }

        composable<DashboardDestinations.PostList> { backStack ->

            val category = backStack.toRoute<DashboardDestinations.PostList>().category

            PostListScreen(
                category = Category.valueOf(category),
                navigateBack = { if (navigator.canNavigate()) navigator.popBackStack() }
            )
        }
    }
}