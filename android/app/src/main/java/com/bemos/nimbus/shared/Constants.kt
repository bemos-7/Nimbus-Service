package com.bemos.nimbus.shared

object Constants {

    private const val SERVER_IP = ""
    private const val VERSION = "v1"
    private const val USERS = "users"

    const val BASE_SERVICE_URL = "http://$SERVER_IP/"
    const val USERS_REGISTER = "$USERS/api/$VERSION/$USERS/register"

    const val NAV_ON_BOARD = "onBoard"
}