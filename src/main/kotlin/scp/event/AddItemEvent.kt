package scp.event

import scp.model.Item
import tornadofx.FXEvent

class AddItemEvent(val item: Item): FXEvent()