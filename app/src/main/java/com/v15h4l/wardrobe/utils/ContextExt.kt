package com.v15h4l.wardrobe.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri

/**
 * Get URI of a drawable resource
 */
fun Context.resourceUri(resourceId: Int): Uri = with(resources) {
    Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(getResourcePackageName(resourceId))
        .appendPath(getResourceTypeName(resourceId))
        .appendPath(getResourceEntryName(resourceId))
        .build()
}