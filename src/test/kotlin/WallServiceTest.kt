import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class WallServiceTest {

    @Test
    fun addTest() {
        val post = Post(ownerId = 25, fromId = 36, date = 12, text = "���� � �����")
        val newId = WallService.add(post)
        assertEquals(1, newId)
    }

    @Test
    fun updateTest() {
        val service = WallService
        service.add(Post(ownerId = 25, fromId = 36, date = 12, text = "���� � �����"))
        service.add(Post(ownerId = 27, fromId = 54, date = 11, text = "���� � �����"))
        service.add(Post(ownerId = 35, fromId = 35, date = 10, text = "���� �� ������"))

        val update = Post(ownerId = 35, fromId = 35, date = 10, text = "���� �� ������")
        val result = service.update(update)

        assertTrue(result)
    }

    @Test
    fun updateTestFalse() {
        val service = WallService
        val update = Post(id = 10, ownerId = 35, fromId = 35, date = 10, text = "���� �� ������")
        val result = service.update(update)
        assertFalse(result)
    }
}