package scp.view

import javafx.geometry.Orientation
import javafx.scene.layout.VBox
import javafx.scene.text.FontWeight
import scp.io.FileSystemService
import scp.model.item.BlockItem
import scp.model.item.ButtonItem
import scp.model.item.MainItem
import tornadofx.*

val defaultDimensionUnit = Dimension.LinearUnits.px
val defaultHgrap = Dimension(10.0, defaultDimensionUnit)
val defaultVgrap = Dimension(10.0, defaultDimensionUnit)
val labelFontSize = Dimension(20.0, defaultDimensionUnit)
const val applicationName = "SaveCopyPast v1.0"

class MainView : View(applicationName) {
    private val controller: MyController by inject()
    override val root = vbox {}

    init {
        buildMainView(
            controller.getMainItem()
        )
    }

    private fun buildMainView(mainItem: MainItem) {
        root.apply {
            style {
                vgap = defaultVgrap
            }
            mainItem.blockItems.forEach {
                add(buildBlock(it))
            }
        }
    }

    private fun buildBlock(block: BlockItem): VBox {
        return vbox {
            borderpane {
                left {
                    label(block.name) {
                        style {
                            fontWeight = FontWeight.BOLD
                            fontSize = labelFontSize
                        }
                    }
                }
                right {
                    add(buildImageButton("button/002-pencil.png", {

                    }))
                }
            }

            flowpane {
                orientation = Orientation.HORIZONTAL
                style {
                    hgap = defaultHgrap
                    vgap = defaultVgrap
                }
                block.buttonItems.forEach {
                    add(buildButton(it))
                }
            }
        }
    }

    private fun buildButton(buttonItem: ButtonItem) =
        button(buttonItem.name) {
            action {
                onLeftClick {
                    clipboard.putString(buttonItem.value)
                }
            }
        }

    private fun buildImageButton(imagePath: String, actionBlock: () -> Unit) =
        button {
            imageview(imagePath) {
                fitHeight= 20.0
                fitWidth= 20.0
            }
            action {
                actionBlock()
            }
//            imageview(Image(FileInputStream(resources[imagePath]))) {
//                style {
//                    size = labelFontSize
//                }
//            }
        }
}

class MyFragment : Fragment() {
    override val root = label("This is a popup")
}

class MyController : Controller() {
    private val fsService = FileSystemService.instance

    fun getMainItem() =
        MainItem().apply {
            updateModel(
                fsService.loadJsonObjectFromDataFile()
            )
        }

}