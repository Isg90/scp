package scp.model.item

import javafx.collections.FXCollections
import tornadofx.JsonBuilder
import tornadofx.JsonModel
import tornadofx.toJSON
import tornadofx.toModel
import javax.json.JsonObject

class MainItem : JsonModel {
    val blockItems = FXCollections.observableArrayList<BlockItem>()

    override fun updateModel(json: JsonObject) {
        with(json) {
            blockItems.setAll(getJsonArray("blockItems").toModel())
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("blockItems", blockItems.toJSON())
        }
    }
}