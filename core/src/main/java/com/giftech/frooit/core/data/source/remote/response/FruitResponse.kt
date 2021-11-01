package com.giftech.frooit.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FruitResponse(

	@field:SerializedName("genus")
	val genus: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("family")
	val family: String? = null,

)
