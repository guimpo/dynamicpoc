package poc.dynamictemplatepoc.model

import com.google.gson.annotations.SerializedName


data class Year(

	@field:SerializedName("fund")
	val fund: Double? = null,

	@field:SerializedName("CDI")
	val cDI: Double? = null
)