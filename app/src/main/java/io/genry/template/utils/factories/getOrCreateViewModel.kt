package io.genry.template.utils.factories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel

@Composable
inline fun <reified T : ViewModel> getOrCreateViewModel(): T {
    return hiltViewModel<T>()
}

