import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addTest() {
        val post = Post(ownerId = 17, fromId = 36, date = 12, text = "пост о жизни")
        val newId = WallService.add(post)
        assertEquals(1, newId)
    }

    @Test
    fun updateTest() {
        WallService.add(Post(ownerId = 25, fromId = 36, date = 12, text = "пост о жизни"))
        WallService.add(Post(ownerId = 27, fromId = 54, date = 11, text = "пост о любви"))
        WallService.add(Post(ownerId = 35, fromId = 35, date = 10, text = "пост об отдыхе"))

        val update = Post(ownerId = 35, fromId = 35, date = 10, text = "пост об отдыхе")
        val result = WallService.update(update)

        assertTrue(result)
    }

    @Test
    fun updateTestFalse() {
        val update = Post(id = 10, ownerId = 35, fromId = 35, date = 10, text = "пост об отдыхе")
        val result = WallService.update(update)
        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val com = WallService.createComment(10, Comment(1, 2, "Комментарий к посту")).text
    }

    @Test
    fun createComment() {
        val post1 = Post(
            id = 1,
            ownerId = 123,
            text = "This is a post with some text content",
            date = 11
        )
        val post2 = Post(
            id = 2,
            ownerId = 123,
            text = "This is a post with some text content",
            date = 11
        )
        WallService.add(post1)
        WallService.add(post2)
        val com = WallService.createComment(2, Comment(1, 2, "Комментарий к посту")).text
        assertEquals("Комментарий к посту", com)
    }
}