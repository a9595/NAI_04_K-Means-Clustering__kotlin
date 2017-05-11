/**
 * Created by andrii.kovalchuk on 03/05/2017.
 */

import java.util.ArrayList

class Cluster//Creates a new Cluster
(var id: Int) {

    var points: MutableList<Point> = ArrayList()
    var centroid: Point? = null

    init {
        this.centroid = null
    }

    fun addPoint(point: Point) {
        points.add(point)
    }

    fun clear() {
        points.clear()
    }

    fun plotCluster() {
        println("[Cluster: $id]")
        println("[Centroid: $centroid]")
        println("[Points:")
        for (p in points) {
            println(p)
        }
        println("] \n\n\n")
    }

}