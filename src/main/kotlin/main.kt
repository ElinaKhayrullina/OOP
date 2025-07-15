class Likes(val countLikes: Int)

data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val likes: Likes = Likes(0),
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false
)

object WallService {
    private var posts = emptyArray<Post>()
    var count = 0

    fun clear() {
        posts = emptyArray()
        count = 0
    }

    fun add(post: Post): Int {
        count += 1
        val newId = post.copy(id = count)
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
