package poc.dynamictemplatepoc.model

import com.google.gson.annotations.SerializedName


data class Cells(

	@field:SerializedName("cells")
	val cells: List<CellsItem?>? = null
)