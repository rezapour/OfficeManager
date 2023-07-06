package com.rezapour.officemanager.utils


import java.util.Locale

fun String.capitalize() =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() }