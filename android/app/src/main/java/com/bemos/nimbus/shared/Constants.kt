package com.bemos.nimbus.shared

object Constants {

    private const val SERVER_IP = ""
    private const val VERSION = "v1"
    private const val USERS = "users"
    private const val FILES = "files"

    const val USER_FOLDER = "user_folder"
    const val FILE_NAME = "filename"
    const val BASE_SERVICE_URL = "http://$SERVER_IP/"
    const val USERS_REGISTER = "$USERS/api/$VERSION/$USERS/register"
    const val SAVE_FILES = "$FILES/api/$VERSION/$FILES/save_file"
    const val LIST_FILES = "$FILES/api/$VERSION/$FILES/list_files"
    const val DOWNLOAD_FILE = "$FILES/api/$VERSION/$FILES/file"

    const val NAV_ON_BOARD = "onBoard"
    const val NAV_LIST_OF_FILES = "listOfFiles"

    const val SHARED_PREF_KEY = "KEY"
}