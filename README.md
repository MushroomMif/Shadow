# Shadow
Kotlin JVM-target library for shadowing fields and functions. You can shadow field/function with any accessibility type, even private.
After shadowing, you can call a function or get/set (even final) a field.

### Example:
```kt
data class Person(val name: String, val age: Int)

object SecretStorage {
    private val person = Person("Alex", 19)
    private val key = 123456
    
    private fun secretFunction(secret: String): Int {
        return secret.length
    }
}

fun shadow() {
    var person by SecretStorage.shadowOf<Person>("person")
    var key by SecretStorage.shadowOfInt("key")
    val secretFunction by SecretStorage.shadowOfFunction("secretFunction", typeOf<String>())
    
    println("Secret person: $person")
    println("Secret key: $key")
    
    key = 789
    println("New secret key: $key")
    
    val result = secretFunction.call("Super Secret") as Int
    println("Result of secret function: $result")
}
```

## Add to your project
At the moment Shadow is not distributed anywhere, so you need to clone it and add to maven local repository manually.
```
git clone <link to this repository>
gradlew publishToMavenLocal
```

And then add maven local repository and library to your build.gradle(.kts)
```gradle
repositories {
    mavenLocal()
}

dependencies {
    implementation("com.github.MushroomMif:shadow:LATEST_VERSION")
}
```