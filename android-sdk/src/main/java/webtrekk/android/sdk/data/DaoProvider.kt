package webtrekk.android.sdk.data

import android.content.Context
import webtrekk.android.sdk.data.dao.CustomParamDao
import webtrekk.android.sdk.data.dao.TrackRequestDao

internal object DaoProvider {

    fun provideTrackRequestDao(context: Context): TrackRequestDao {
        return WebtrekkDatabase.getInstance(context).trackRequestDao()
    }

    fun provideCustomParamDao(context: Context): CustomParamDao {
        return WebtrekkDatabase.getInstance(context).customParamDataDao()
    }
}
