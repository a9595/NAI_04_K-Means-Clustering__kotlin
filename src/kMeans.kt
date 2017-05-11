/**
 * Created by andrii.kovalchuk on 03/05/2017.
 */

import java.util.ArrayList

class kMeans {

    //Number of Clusters. This metric should be related to the number of points
    private val NUM_CLUSTERS = 4
    //Number of Points
    private val NUM_POINTS = 60

    private var points: List<Point>? = null
    private val clusters: MutableList<Cluster>

    init {
        this.points = ArrayList<Point>()
        this.clusters = ArrayList<Cluster>()
    }

    //Initializes the process
    fun init() {
        //Create Points
        points = Point.createRandomPoints(MIN_COORDINATE, MAX_COORDINATE, NUM_POINTS)

        //Create Clusters
        //Set Random Centroids
        for (i in 0..NUM_CLUSTERS - 1) {
            val cluster = Cluster(i)
            val centroid = Point.createRandomPoint(MIN_COORDINATE, MAX_COORDINATE)
            cluster.centroid = centroid
            clusters.add(cluster)
        }

        //Print Initial state
        plotClusters()
    }

    private fun plotClusters() {
        for (i in 0..NUM_CLUSTERS - 1) {
            clusters[i].plotCluster()
        }
    }

    //The process to calculate the K Means, with iterating method.
    fun calculate() {
        var finish = false
        var iteration = 0

        // Add in new data, one at a time, recalculating centroids with each new one.
        while (!finish) {
            //Clear cluster state
            clearClusters()

            val lastCentroids = centroids

            //Assign points to the closer cluster
            assignCluster()

            //Calculate new centroids.
            calculateCentroids()

            iteration++

            val currentCentroids = centroids

            //Calculates total distance between new and old Centroids
            var distance = 0.0
            for (i in lastCentroids.indices) {
                distance += Point.distance(lastCentroids[i], currentCentroids[i])
            }

            println("#################")
            println("Iteration: " + iteration)
            println("Centroid distances: " + distance)
            plotClusters()

            if (distance == 0.0) {
                finish = true
            }
        }
    }

    private fun clearClusters() {
        for (cluster in clusters) {
            cluster.clear()
        }
    }

    private val centroids: List<Point>
        get() {
            val centroids = ArrayList<Point>(NUM_CLUSTERS)
            for (cluster in clusters) {
                val aux = cluster.centroid
                val point = Point(aux!!.x, aux.y)
                centroids.add(point)
            }
            return centroids
        }

    private fun assignCluster() {
        val max = java.lang.Double.MAX_VALUE
        var min = max
        var cluster = 0
        var distance = 0.0

        for (point in points!!) {
            min = max
            for (i in 0..NUM_CLUSTERS - 1) {
                val c = clusters[i]
                distance = Point.distance(point, c.centroid!!)
                if (distance < min) {
                    min = distance
                    cluster = i
                }
            }
            point.cluster = cluster
            clusters[cluster].addPoint(point)
        }
    }

    private fun calculateCentroids() {
        for (cluster in clusters) {
            var sumX = 0.0
            var sumY = 0.0
            val list = cluster.points
            val n_points = list.size

            for (point in list) {
                sumX += point.x
                sumY += point.y
            }

            val centroid = cluster.centroid
            if (n_points > 0) {
                val newX = sumX / n_points
                val newY = sumY / n_points
                centroid!!.x = newX
                centroid.y = newY
            }
        }
    }

    companion object {
        //Min and Max X and Y
        private val MIN_COORDINATE = 0
        private val MAX_COORDINATE = 10

        @JvmStatic fun main(args: Array<String>) {

            val kmeans = kMeans()
            kmeans.init()
            kmeans.calculate()
        }
    }
}