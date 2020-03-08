package scp.io

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import scp.io.FileSystemService.Companion.DATA_FILE_PATH
import tornadofx.JsonBuilder
import java.io.File

class FileSystemServiceTest {
    private val fileSystemService = FileSystemService.instance
    private var dataFile = File(FileSystemService.DATA_FILE_PATH)
    private val block1Name = "testBlock1"
    private val block2Name = "testBlock2"

    @BeforeEach
    fun beforeEach() {
        fileSystemService.createFileStructureOnStartup()
    }

    @AfterEach
    fun afterEach() {
        val dataDirectory = File(FileSystemService.DATA_FOLDER_PATH)
        if (dataDirectory.exists())
            dataDirectory.deleteRecursively()
    }

    @Test
    fun `createFileStructureOnStartup creates data folder and json file`() {
        assert(dataFile.exists())
    }

    @Test
    fun `saveDataToFile saves jsonObject to data file`() {
        val jsonObject = JsonBuilder()
            .apply { add("block1", block1Name); add("block2", block2Name) }
            .build()
        fileSystemService.saveJsonObjectToFile(jsonObject)
        assert(dataFile.readText().contains(block1Name))
        assert(dataFile.readText().contains(block2Name))
    }

    @Test
    fun `getJsonObjectFromDataFile reads MainItem from data file`() {
        File(DATA_FILE_PATH).writeText("""{"blockItems":[{"name":"testBlock1","buttons":[{"name":"button1","value":"value1"}]}]}""")
        val jsonObject = fileSystemService.loadJsonObjectFromDataFile()
        assertNotNull(jsonObject.getJsonArray("blockItems")[0].asJsonObject().getJsonString("name").string)
    }
}