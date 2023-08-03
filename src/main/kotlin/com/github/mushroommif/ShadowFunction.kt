package com.github.mushroommif

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KCallable
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.jvm.isAccessible

/**
 * Property delegate which gets function from object via reflection
 * @param name name of function
 * @param obj object which contains function
 * @param parameters list of types of parameters which function accepts
 */
class ShadowFunction<T: Any> (val name: String, val obj: T, val parameters: List<KType>): ReadOnlyProperty<Any?, KCallable<*>> {
    private val function: KCallable<*> by lazy {
        val clazz = obj::class
        val funcs = clazz.members.filter { it is KFunction<*> }

        val func = funcs.find {
            (it as KFunction<*>).name == name && it.parameters.map { it.type }.containsAll(parameters)
        }
            ?: error("There is no methods with name \"$name\" and provided parameters in ${obj::class.simpleName}")

        return@lazy func.apply {
            (this as KFunction<*>).isAccessible = true
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): KCallable<*> {
        return function
    }
}

/**
 * Property delegate to get shadow of function which can be called afterward
 * @param name name of function
 * @param parameters types of parameters which function accepts
 * @return shadow of function
 */
fun Any.shadowOfFunction(name: String, vararg parameters: KType): ReadOnlyProperty<Any?, KCallable<*>> {
    return ShadowFunction(name, this, parameters.toList())
}
