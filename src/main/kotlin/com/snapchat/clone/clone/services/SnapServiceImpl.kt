package com.snapchat.clone.clone.services

import com.snapchat.clone.clone.models.Snap
import com.snapchat.clone.clone.models.User
import com.snapchat.clone.clone.repository.SnapRepository
import com.snapchat.clone.clone.repository.UserRepository
import org.springframework.security.acls.model.NotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class SnapServiceImpl(
    private val snapRepository: SnapRepository,
    private val userRepository: UserRepository
): SnapService {
    override fun sendSnap(snap: Snap): Snap {
        if(snap.id == null) throw NotFoundException("Not found")
        val sender: User? = userRepository.findById(snap.id).orElse(null)
        val recipient: User? = userRepository.findById(snap.id).orElse(null)
        if (sender == null || recipient == null) {
            throw IllegalArgumentException("Not Found")
        }
        if (!sender.friends!!.contains(recipient.id)) {
            throw IllegalArgumentException("")
        }
        val snapWithIdAndDate = snap.copy(id = UUID.randomUUID().toString(), timestamp = Date())
        return snapRepository.save(snapWithIdAndDate)
    }

    override fun getSnaps(recipientId: String?): List<Snap?>? {
        val snaps = snapRepository.findByRecipientId(recipientId)
        snaps!!.forEach { snap ->
            if (snap != null && !snap.viewed) {
                snapRepository.save(snap.copy(viewed = true))
            }
        }
        return snaps
    }
}