package com.vivcom.videoshop.repository.persistence

/**
 * Clase que permite administrar datos para acceso rapido con la clase SharedPreferences
 */
class PreferencesManager {
/*
    companion object {

        fun setUser(user: User, context: Context){
            val editor: SharedPreferences.Editor = getUserSharedPreferences(context).edit()
            editor.putString(Constants.Keys.USER, user.toJsonString())
            editor.apply()
        }

        fun getUser(context: Context): User?{

            val userSharedPreferences: SharedPreferences = getUserSharedPreferences(context)
            val jsonString: String = userSharedPreferences.getString(Constants.Keys.USER, Constants.Persistence.USER_NOT_FOUND)
            if (jsonString.equals(Constants.Persistence.USER_NOT_FOUND)){
                return null
            }
            return BaseModel.objectFromJson(jsonString,User::class.java) as User
        }

        fun clean(context: Context){
            val editor: SharedPreferences.Editor = getUserSharedPreferences(context).edit()
            editor.clear()
            editor.apply()
        }

    }
*/
}