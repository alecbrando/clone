import com.snapchat.clone.clone.models.TokenResponse
import com.snapchat.clone.clone.models.User

interface UserService {
    fun createUser(user: User): TokenResponse
    fun getUser(username: String?): User
    fun addFriend(username: String?, friendUsername: String?): User
}