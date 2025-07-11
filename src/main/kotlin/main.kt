data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val likes: Int = 1,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false
)

object WallService {
    private var posts = emptyArray<Post>()

    fun add(post: Post): Int {
        val id = posts.lastIndex
        val newId = post.copy(id = id + 2)
        posts += newId
        println(newId)
        return posts.last().id
    }

    fun update(post: Post): Boolean {
        val size = posts.size
        if (size < post.id || post.id < 0) {
            return false
        } else {
            val text = post.copy(text = post.text)
            return true
        }
    }
}

fun main() {
    val post = Post(125, 25, 36, 12, "пост о жизни")
    WallService.add(post)
    val post2 = Post(2, 25, 36, 12, "пост о жизни")
    WallService.add(post2)
    val post3 = Post(10, 25, 36, 12, "пост о жизни")
    WallService.add(post3)
}
