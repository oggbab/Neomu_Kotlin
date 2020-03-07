package com.not.common.util

interface OnBackPressedObserver {
    fun addOnBackPressedListener(listener: OnBackPressedListener)
    fun removeOnBackPressedListener(listener: OnBackPressedListener)
}