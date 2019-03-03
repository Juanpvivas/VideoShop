package com.vivcom.videoshop.repository.persistence.database.data

import com.vivcom.videoshop.repository.persistence.database.entity.Word

class PopulateDB {

    fun getWords(): MutableList<Word> {
        val list = emptyList<Word>().toMutableList()

        list.add(Word("Imagen"))
        list.add(Word("Audio"))
        list.add(Word("Archivo"))

        return list
    }
}