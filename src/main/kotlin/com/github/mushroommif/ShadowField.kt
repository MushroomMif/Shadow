package com.github.mushroommif

import java.lang.reflect.Field
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Property delegate which gets (and sets) field from object via reflection
 * @param name name of filed
 * @param obj object which contains field
 * @param getFun function to get value from field
 * @param setFun function to set value to field
 */
class ShadowField<V, O: Any> (
    val name: String, val obj: O,
    val getFun: (Field, Any) -> Any = Field::get,
    val setFun: (Field, Any, V) -> Unit = Field::set
) : ReadWriteProperty<Any?, V> {

    private val field: Field by lazy {
        return@lazy try {
            obj::class.java
                .getDeclaredField(name)
                .apply {
                    isAccessible = true
                }
        } catch (_: NoSuchFieldException) {
            error("There is no properties with name $name")
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): V {
        return getFun(field, obj) as V
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: V) {
        setFun(field, obj, value)
    }

}

/**
 * Property delegate to get shadow of field with non-primitive type
 * which can be got or set afterward.
 *
 * If type of your field is primitive, you need to use different function.
 * @see shadowOfInt
 * @see shadowOfLong
 * @see shadowOfFloat
 * @see shadowOfDouble
 * @see shadowOfBoolean
 * @see shadowOfChar
 * @see shadowOfByte
 * @see shadowOfShort
 *
 * @param name name of field
 */
fun <T> Any.shadowOf(name: String): ReadWriteProperty<Any?, T> {
    return ShadowField(name, this)
}

/**
 * Property delegate to get shadow of field with Int type
 * which can be got or set afterward.
 *
 * There are different functions to get and set value of field with primitive types,
 * so you need to call appropriate function
 * @param name name of field
 */
fun Any.shadowOfInt(name: String): ReadWriteProperty<Any?, Int> {
    return ShadowField(name, this, Field::getInt, Field::setInt)
}

/**
 * Property delegate to get shadow of field with Long type
 * which can be got or set afterward.
 *
 * There are different functions to get and set value of field with primitive types,
 * so you need to call appropriate function
 * @param name name of field
 */
fun Any.shadowOfLong(name: String): ReadWriteProperty<Any?, Long> {
    return ShadowField(name, this, Field::getLong, Field::setLong)
}

/**
 * Property delegate to get shadow of field with Float type
 * which can be got or set afterward.
 *
 * There are different functions to get and set value of field with primitive types,
 * so you need to call appropriate function
 * @param name name of field
 */
fun Any.shadowOfFloat(name: String): ReadWriteProperty<Any?, Float> {
    return ShadowField(name, this, Field::getFloat, Field::setFloat)
}

/**
 * Property delegate to get shadow of field with Double type
 * which can be got or set afterward.
 *
 * There are different functions to get and set value of field with primitive types,
 * so you need to call appropriate function
 * @param name name of field
 */
fun Any.shadowOfDouble(name: String): ReadWriteProperty<Any?, Double> {
    return ShadowField(name, this, Field::getDouble, Field::setDouble)
}

/**
 * Property delegate to get shadow of field with Boolean type
 * which can be got or set afterward.
 *
 * There are different functions to get and set value of field with primitive types,
 * so you need to call appropriate function
 * @param name name of field
 */
fun Any.shadowOfBoolean(name: String): ReadWriteProperty<Any?, Boolean> {
    return ShadowField(name, this, Field::getBoolean, Field::setBoolean)
}

/**
 * Property delegate to get shadow of field with Char type
 * which can be got or set afterward.
 *
 * There are different functions to get and set value of field with primitive types,
 * so you need to call appropriate function
 * @param name name of field
 */
fun Any.shadowOfChar(name: String): ReadWriteProperty<Any?, Char> {
    return ShadowField(name, this, Field::getChar, Field::setChar)
}

/**
 * Property delegate to get shadow of field with Byte type
 * which can be got or set afterward.
 *
 * There are different functions to get and set value of field with primitive types,
 * so you need to call appropriate function
 * @param name name of field
 */
fun Any.shadowOfByte(name: String): ReadWriteProperty<Any?, Byte> {
    return ShadowField(name, this, Field::getByte, Field::setByte)
}

/**
 * Property delegate to get shadow of field with Short type
 * which can be got or set afterward.
 *
 * There are different functions to get and set value of field with primitive types,
 * so you need to call appropriate function
 * @param name name of field
 */
fun Any.shadowOfShort(name: String): ReadWriteProperty<Any?, Short> {
    return ShadowField(name, this, Field::getShort, Field::setShort)
}
