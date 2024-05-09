package org.quickoline.features.tabs.activity.navigation

import kotlinx.serialization.Serializable

internal sealed interface ActivityDestinations {

    @Serializable
    data object Activity: ActivityDestinations
}