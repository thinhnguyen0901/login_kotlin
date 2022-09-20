package github.app.login_kotlin.data

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object Database {
    private const val databaseName = "UserDB.sqlite"
    fun initDatabase(activity: Activity): SQLiteDatabase {
        try {
            val outFileName =
                activity.applicationInfo.dataDir + "/databases/" + databaseName
            val f = File(outFileName)
            if (!f.exists()) {
                val e = activity.assets.open(databaseName)
                val folder = File(activity.applicationInfo.dataDir + "/databases/")
                if (!folder.exists()) {
                    folder.mkdir()
                }
                val myOutput = FileOutputStream(outFileName)
                val buffer = ByteArray(1024)
                var length: Int
                while (e.read(buffer).also { length = it } > 0) {
                    myOutput.write(buffer, 0, length)
                }
                myOutput.flush()
                myOutput.close()
                e.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return activity.openOrCreateDatabase(
            databaseName,
            Context.MODE_PRIVATE,
            null
        )
    }

}
