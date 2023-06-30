package com.rezapour.officemanager.data.network.retrofit.autenticator

import okhttp3.Credentials;

object BasicAuthentication {
    fun getApiKey(user: String, password: String) = Credentials.basic(user, password)
}