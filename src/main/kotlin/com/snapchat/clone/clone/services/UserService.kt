import com.snapchat.clone.clone.models.User

interface UserService {
    fun createUser(user: User): User
    fun getUser(username: String?): User
    fun addFriend(username: String?, friendUsername: String?): User
}