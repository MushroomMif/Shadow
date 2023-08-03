import com.github.mushroommif.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.reflect.typeOf
import kotlin.test.assertEquals

class `Shadow Tester` {
    val test = TestClass()

    var testIntProperty by test.shadowOfInt("intProperty")
    var testLongProperty by test.shadowOfLong("longProperty")
    var testBooleanProperty by test.shadowOfBoolean("booleanProperty")
    var testFloatProperty by test.shadowOfFloat("floatProperty")
    var testDoubleProperty by test.shadowOfDouble("doubleProperty")
    var testByteProperty by test.shadowOfByte("byteProperty")
    var testShortProperty by test.shadowOfShort("shortProperty")
    var testCharProperty by test.shadowOfChar("charProperty")

    var testStringProperty by test.shadowOf<String>("stringProperty")
    var testObjectProperty by test.shadowOf<Person>("personProperty")

    val testMethod by test.shadowOfFunction("function", typeOf<String>())

    @Test
    fun `Shadow String property getter`() {
        assertEquals(testStringProperty, "test")
    }

    @Test
    fun `Shadow String property setter`() {
        testStringProperty = "test2"
        assertEquals(testStringProperty, "test2")
    }

    @Test
    fun `Shadow Object property getter`() {
        assertEquals(testObjectProperty, Person("Amogus", 13000))
    }

    @Test
    fun `Shadow Object property setter`() {
        testObjectProperty = Person("Aboba", 13)
        assertEquals(testObjectProperty, Person("Aboba", 13))
    }

    @Test
    fun `Shadow Int property getter`() {
        assertEquals(testIntProperty, 1)
    }

    @Test
    fun `Shadow Int property setter`() {
        testIntProperty = 2
        assertEquals(testIntProperty, 2)
    }

    @Test
    fun `Shadow Long property getter`() {
        assertEquals(testLongProperty, 1L)
    }

    @Test
    fun `Shadow Long property setter`() {
        testLongProperty = 2L
        assertEquals(testLongProperty, 2L)
    }

    @Test
    fun `Shadow Boolean property getter`() {
        assertEquals(testBooleanProperty, true)
    }

    @Test
    fun `Shadow Boolean property setter`() {
        testBooleanProperty = false
        assertEquals(testBooleanProperty, false)
    }

    @Test
    fun `Shadow Float property getter`() {
        assertEquals(testFloatProperty, 1f)
    }

    @Test
    fun `Shadow Float property setter`() {
        testFloatProperty = 2f
        assertEquals(testFloatProperty, 2f)
    }

    @Test
    fun `Shadow Double property getter`() {
        assertEquals(testDoubleProperty, 1.0)
    }

    @Test
    fun `Shadow Double property setter`() {
        testDoubleProperty = 2.0
        assertEquals(testDoubleProperty, 2.0)
    }

    @Test
    fun `Shadow Byte property getter`() {
        assertEquals(testByteProperty, 1)
    }

    @Test
    fun `Shadow Byte property setter`() {
        testByteProperty = 2
        assertEquals(testByteProperty, 2)
    }

    @Test
    fun `Shadow Short property getter`() {
        assertEquals(testShortProperty, 1)
    }

    @Test
    fun `Shadow Short property setter`() {
        testShortProperty = 2
        assertEquals(testShortProperty, 2)
    }

    @Test
    fun `Shadow Char property getter`() {
        assertEquals(testCharProperty, 'a')
    }

    @Test
    fun `Shadow Char property setter`() {
        testCharProperty = 'b'
        assertEquals(testCharProperty, 'b')
    }

    @Test
    fun `Shadow function caller`() {
        val result = testMethod.call(test, "test") as Int
        assertEquals(result, 4)
    }

    @Test
    fun `Invalid function`() {
        val invalidMethod by test.shadowOfFunction("invalid", typeOf<Person>())
        assertThrows<IllegalStateException> {
            invalidMethod.call(test, Person("Amogus", 13000))
        }
    }

    @Test
    fun `Invalid property`() {
        val invalidProperty by test.shadowOf<String>("invalid")
        assertThrows<IllegalStateException> {
            invalidProperty
        }
    }

    @Test
    fun `Invalid property type`() {
        var invalidTypeProperty by test.shadowOfInt("charProperty")
        assertThrows<IllegalArgumentException> {
            invalidTypeProperty = 1
        }
    }
}

class TestClass {
    private val intProperty = 1
    private val longProperty = 1L
    private val booleanProperty = true
    private val floatProperty = 1f
    private val doubleProperty = 1.0
    private val byteProperty = 1.toByte()
    private val shortProperty = 1.toShort()
    private val charProperty = 'a'

    private val stringProperty = "test"

    private val personProperty = Person("Amogus", 13000)

    private fun function(abob: String): Int {
        return abob.length
    }
}

data class Person(val name: String, val age: Int)