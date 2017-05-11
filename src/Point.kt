/**
 * Created by andrii.kovalchuk on 03/05/2017.
 */

import java.util.ArrayList
import java.util.Random

class Point(x: Double, y: Double) {

    var x = 0.0
    var y = 0.0
    var cluster = 0

    init {
        this.x = x
        this.y = y
    }

    override fun toString(): String {
        return "($x,$y)"
    }

    companion object {

        //Calculates the distance between two points.
        fun distance(p: Point, centroid: Point): Double {
            return Math.sqrt(Math.pow(centroid.y - p.y, 2.0) + Math.pow(centroid.x - p.x, 2.0))
        }

        //Creates random point
        fun createRandomPoint(min: Int, max: Int): Point {
            val r = Random()
            val x = min + (max - min) * r.nextDouble()
            val y = min + (max - min) * r.nextDouble()
            return Point(x, y)
        }

        fun createRandomPoints(min: Int, max: Int, number: Int): List<Point> {
            val points = ArrayList<Point>(number)
            for (i in 0..number - 1) {
                points.add(createRandomPoint(min, max))
            }
            return points
        }
    }
}