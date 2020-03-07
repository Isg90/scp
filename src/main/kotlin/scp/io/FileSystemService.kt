package scp.io

import java.io.File
import java.io.FileReader
import java.io.FileWriter
import javax.json.Json
import javax.json.JsonObject

class FileSystemService {

    fun createFileStructureOnStartup() {
        if (!File(DATA_FOLDER_PATH).exists()) {
            File(DATA_FOLDER_PATH).mkdir()
            File(DATA_FILE_PATH).createNewFile()
        }
    }

    fun getJsonObjectFromDataFile() =
        Json
            .createReader(FileReader(DATA_FILE_PATH))
            .readObject()

    fun saveJsonObjectToFile(jsonObject: JsonObject) {
        val fileWriter = FileWriter(DATA_FILE_PATH)
        fileWriter.use {
            val jsonWriter = Json.createWriter(fileWriter)
            jsonWriter.use {
                jsonWriter.writeObject(jsonObject)
            }
        }
    }

    companion object {
        const val DATA_FOLDER_PATH = "./data"
        const val DATA_FILE_NAME = "data.json"
        val DATA_FILE_PATH = "$DATA_FOLDER_PATH${File.separator}$DATA_FILE_NAME"
    }
}