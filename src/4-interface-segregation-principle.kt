import kotlin.math.pow

// BAD: This class violates the Interface Segregation Principle
// BadShape interface forces BadSquare to calculate a radius,
// of which it cannot & has no use for

interface BadShape {
    fun radius(): Double
    fun area(): Double
}

data class BadCircle(val diameter: Double): BadShape {
    override fun radius() = diameter / 2
    override fun area() = 3.142 * radius().pow(2)
}

data class BadSquare(val width: Int, val height: Int): BadShape {
    override fun radius() = 0.0
    override fun area() = (width * height).toDouble()
}


// GOOD: by breaking BadShape up into Shape & CircularShape,
// we no longer need to force Square to implement a radius
interface Shape {
    fun area(): Double
}

interface CircularShape {
    fun radius(): Double
}

data class Circle(val diameter: Double): Shape, CircularShape {
    override fun radius() = diameter / 2
    override fun area() = 3.142 * radius().pow(2)
}

data class Square(val width: Int, val height: Int): Shape {
    override fun area() = (width * height).toDouble()
}