package com.cervantes.appejemploenclase2024

import com.google.gson.annotations.SerializedName

data class PersonasResponse(
    @SerializedName("listaPersonas") var listaPersonas : ArrayList <Persona>
)
