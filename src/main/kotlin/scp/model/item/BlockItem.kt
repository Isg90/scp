package scp.model.item

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import tornadofx.*
import javax.json.JsonObject

class BlockItem : JsonModel {
    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val buttonItems = FXCollections.observableArrayList<ButtonItem>()

    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name")
            buttonItems.setAll(getJsonArray("buttons").toModel())
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("name", name)
            add("buttons", buttonItems.toJSON())
        }
    }
}