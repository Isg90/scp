package scp.view

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import scp.io.FileSystemService
import scp.model.item.BlockItem
import scp.model.item.ButtonItem
import scp.model.item.MainItem
import tornadofx.*

class MainView : View("SaveCopyPast v1.0") {
    val controller: MyController by inject()
    override val root = vbox {}

    init {
        buildMainView(
            controller.getMainItem()
        )
    }

    private fun buildMainView(mainItem: MainItem) {
        root.apply {
            mainItem.blockItems.forEach {
                buildBlock(it)
            }
        }
    }

    private fun buildBlock(block: BlockItem): VBox {
        return vbox {
            label {
                text = block.name
                font = Font.font(20.0)
            }
            flowpane {
                block.buttonItems.forEach {
                    buildButton(it)
                }
            }
        }
    }

    private fun buildButton(buttonItem: ButtonItem) : Button =
        button {
            text = buttonItem.name
            action {
                onLeftClick {
                    clipboard.putString(buttonItem.value)
                }
            }
        }
}

class MyFragment : Fragment() {
    override val root = label("This is a popup")
}

class MyController : Controller() {
    val fsService = FileSystemService.instance

    fun getMainItem() =
        MainItem().apply {
            updateModel(
                fsService.loadJsonObjectFromDataFile()
            )
        }

}