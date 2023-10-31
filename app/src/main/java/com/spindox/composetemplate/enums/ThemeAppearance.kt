package com.spindox.composetemplate.enums

enum class ThemeAppearance(val value: Int, val type: String) {
    AUTO(0, "Auto"),
    LIGHT(1, "Light"),
    DARK(2, "Dark");

    companion object {
        private val values = ThemeAppearance.values()
        fun getByValue(value: Int?) = values.firstOrNull { it.value == value } ?: AUTO
    }
}