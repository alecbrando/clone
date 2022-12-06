package com.snapchat.clone.clone.services

import com.snapchat.clone.clone.models.Snap

interface SnapService {
    fun sendSnap(snap: Snap): Snap
    fun getSnaps(recipientId: String?): List<Snap?>?
}