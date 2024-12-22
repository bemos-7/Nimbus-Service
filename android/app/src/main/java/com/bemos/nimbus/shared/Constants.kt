package com.bemos.nimbus.shared

object Constants {

    private const val SERVER_IP = ""
    private const val VERSION = "v1"
    private const val USERS = "users"
    private const val FILES = "files"
    private const val FILE = "file"
    private const val UPLOAD = "upload"
    private const val NIMBUS = "nimbus"

    const val USER_FOLDER = "user_folder"
    const val FILE_NAME = "filename"
    const val BASE_SERVICE_URL = "http://$SERVER_IP/"
    const val USERS_REGISTER = "$NIMBUS/api/$VERSION/$USERS/register"
    const val SAVE_FILES = "$NIMBUS/api/$VERSION/$FILES/save_file"
    const val LIST_FILES = "$NIMBUS/api/$VERSION/$FILES/list_files"
    const val DOWNLOAD_FILE = "$NIMBUS/api/$VERSION/$FILES/file"
    const val DELETE_FILE = "$NIMBUS/api/$VERSION/$FILES/file"

    const val NAV_ON_BOARD = "onBoard"
    const val NAV_LIST_OF_FILES = "listOfFiles"

    const val SHARED_PREF_KEY = "KEY"
}