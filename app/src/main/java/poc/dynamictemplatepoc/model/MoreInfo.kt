package poc.dynamictemplatepoc.model

import com.google.gson.annotations.SerializedName


data class MoreInfo(

	@field:SerializedName("month")
	val month: Month? = null,

	@field:SerializedName("year")
	val year: Year? = null,

	@field:SerializedName("12months")
	val jsonMember12months: JsonMember12months? = null
)