
interface Attachment {
    val type: TypeAttachment
}

enum class TypeAttachment {
    AUDIO, PHOTO, VIDEO, DOC, LINK
}

data class Audio(
    val id: Int,
    val albumId: Int,
    val title: String,
    val duration: Int
)

data class Photo(
    val id: Int,
    val albumId: Int,
    val title: String,
    val width: Int,
    val height: Int
)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int,
    val description: String? = null
)

data class Doc(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Long,
    val ext: String? = null
)

data class Link(
    val url: String,
    val title: String,
    val description: String? = null,
    val previewUrl: String? = null
)

data class AudioAttachment(
    val audio: Audio,
    val id: Int,
    val albumId: Int
) : Attachment {
    override val type: TypeAttachment = TypeAttachment.AUDIO
}

data class PhotoAttachment(
    val photo: Photo,
    val id: Int,
    val albumId: Int
) : Attachment {
    override val type: TypeAttachment = TypeAttachment.PHOTO
}

data class VideoAttachment(
    val video: Video,
    val id: Int,
    val ownerId: Int
) : Attachment {
    override val type: TypeAttachment = TypeAttachment.VIDEO
}

data class DocAttachment(
    val doc: Doc,
    val id: Int,
    val ownerId: Int
) : Attachment {
    override val type: TypeAttachment = TypeAttachment.DOC
}

data class LinkAttachment(
    val link: Link,
    val id: Int
) : Attachment {
    override val type: TypeAttachment = TypeAttachment.LINK
}

class Likes(val countLikes: Int)

public open class Comment(
    val id: Int,
    val fromId: Int,
    val text: String
)

data class Post(
    val id: Int = 1,
    val ownerId: Int,
    val date: Int,
    val fromId: Int? = null,
    val createdBy: Int? = null,
    val text: String? = null,
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = false,
    val likes: Likes = Likes(0),
    val attachments: Array<Attachment> = emptyArray()
)

class PostNotFoundException(message: String) : RuntimeException(message)

object WallService {
    private var posts = emptyArray<Post>()
    var count = 0
    private var comments = emptyArray<Comment>()

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

    fun createComment(postId: Int, comment: Comment): Comment {
        val post = posts.find { it.id == postId }
        if (post != null) {
            comments += comment
            return comments.last()
        } else {
            throw PostNotFoundException("No post with id $postId")
        }
    }
}

fun main() {
    val audio = Audio(1, 101, "Song", 180)
    val photo = Photo(2, 202, "Nature", 1920, 1080)

    val audioAttachment = AudioAttachment(audio, 1, 101)
    val photoAttachment = PhotoAttachment(photo, 2, 202)
    val videoAttachment = VideoAttachment(
        Video(3, 123, "Tutorial", 600, "How to..."),
        3, 123
    )
    val docAttachment = DocAttachment(
        Doc(4, 123, "Document.pdf", 1024, "pdf"),
        4, 123
    )
    val linkAttachment = LinkAttachment(
        Link("https://example.com", "Example", "Example site", "https://example.com/preview.jpg"),
        5
    )
    val post1 = Post(
        id = 1,
        ownerId = 123,
        text = "This is a post with some text content",
        date = 11,
        attachments = arrayOf(audioAttachment, photoAttachment)
    )
    WallService.add(post1)
    val post2 = Post(
        id = 2,
        ownerId = 456,
        friendsOnly = true,
        date = 12
    )
    WallService.add(post2)
    val post3 = Post(
        id = 1,
        ownerId = 123,
        text = "This post contains all attachment types",
        date = 1,
        attachments = arrayOf(
            audioAttachment,
            photoAttachment,
            videoAttachment,
            docAttachment,
            linkAttachment
        )
    )
    WallService.add(post3)
    val com = WallService.createComment(10, Comment(1, 2, "Комментарий к посту")).text

    println(post1)
    println(post2)
    println(post3)
    println(com)
}

